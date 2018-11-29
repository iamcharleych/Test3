package com.chaplin.test3.ui.searchresults;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.chaplin.test3.R;
import com.chaplin.test3.domain.exception.NoContentException;
import com.chaplin.test3.domain.exception.PollValidationException;
import com.chaplin.test3.data.model.enitity.SearchResultEntity;
import com.chaplin.test3.data.network.client.ApiResponseCode;
import com.chaplin.test3.data.network.client.RequestException;
import com.chaplin.test3.ui.searchresults.adapter.SearchResultsAdapter;
import com.chaplin.test3.ui.searchresults.viewmodel.DataLoadingViewState;
import com.chaplin.test3.ui.searchresults.viewmodel.action.Action;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

public class SearchResultsView implements SwipeRefreshLayout.OnRefreshListener, GenericLifecycleObserver {

    private static final int NO_RES_ID = -1;

    @NonNull
    private final View mRootView;
    @NonNull
    private final LiveData<PagedList<SearchResultEntity>> mPagedList;

    private final TextView mStatusView;
    private final ProgressBar mProgressBar;
    private final SwipeRefreshLayout mRefreshView;

    @NonNull
    private final PublishSubject<Action> mActionStream = PublishSubject.create();
    @NonNull
    private final CompositeDisposable mCompositeSubscription = new CompositeDisposable();

    @ColorInt
    private final int mErrorTextColor;
    @ColorInt
    private final int mRegularTextColor;

    public SearchResultsView(@NonNull View rootView,
                             @NonNull SearchResultsAdapter adapter,
                             @NonNull LifecycleOwner lifecycle,
                             @NonNull LiveData<DataLoadingViewState> dataViewState,
                             @NonNull LiveData<PagedList<SearchResultEntity>> pagedList) {
        mRootView = rootView;
        mStatusView = rootView.findViewById(R.id.statusView);
        mProgressBar = rootView.findViewById(R.id.progressView);
        mRefreshView = rootView.findViewById(R.id.refreshLayout);

        mErrorTextColor = ContextCompat.getColor(mStatusView.getContext(), R.color.textError);
        mRegularTextColor = ContextCompat.getColor(mStatusView.getContext(), R.color.textTitle);

        mRefreshView.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        mRefreshView.setOnRefreshListener(this);

        ((RecyclerView) rootView.findViewById(R.id.resultsRecyclerView)).setAdapter(adapter);

        lifecycle.getLifecycle().addObserver(this);
        mPagedList = pagedList;
        pagedList.observe(lifecycle, newPagedList -> {
            adapter.submitList(newPagedList);
            DataLoadingViewState dataState = dataViewState.getValue();
            if (newPagedList != null
                    && dataState != null
                    && !dataState.isLoading()
                    && (dataState.getThrowable() == null)) {
                renderCompletedViewState();
            }
        });
        dataViewState.observe(lifecycle, this::renderDataViewState);
    }

    private void destroy() {
        if (!mCompositeSubscription.isDisposed()) {
            mCompositeSubscription.dispose();
        }
    }

    public void registerActionsObserver(@NonNull Consumer<? super Action> consumer) {
        mCompositeSubscription.add(mActionStream.subscribe(consumer));

        if (mPagedList.getValue() == null) {
            // fire action to start refreshing search results immediately
            mActionStream.onNext(new Action.RefreshSearchResults());
        }
    }

    @Override
    public void onRefresh() {
        if (!mRefreshView.isRefreshing()) {
            mRefreshView.setRefreshing(true);
        }
        mActionStream.onNext(new Action.RefreshSearchResults());
    }

    // region Render

    private void renderDataViewState(DataLoadingViewState viewState) {
        if (viewState == null) {
            return; // early exit
        }

        if (viewState.isIdle()) {
            renderIdleViewState();
        } else if (viewState.isLoading()) {
            renderLoadingViewState();
        } else if (viewState.isCompleted()) {
            renderCompletedViewState();
        } else if (viewState.getThrowable() != null) {
            renderErrorViewState(viewState.getThrowable());
        }
    }

    private void renderIdleViewState() {
        mProgressBar.setVisibility(View.GONE);
        mRefreshView.setEnabled(true);
    }

    private void renderLoadingViewState() {
        mStatusView.setTextColor(mRegularTextColor);
        mStatusView.setText(R.string.status_loading_results);
        mProgressBar.setVisibility(View.VISIBLE);
        if (!mRefreshView.isRefreshing()) {
            mRefreshView.setEnabled(false);
        }
    }

    private void renderCompletedViewState() {
        String statusString = null;
        PagedList list = mPagedList.getValue();
        final int count = list != null ? list.size() : 0;
        if (count > 0) {
            statusString = mRootView.getContext().getString(R.string.demo_results_count, count, count);
        }

        mStatusView.setTextColor(mRegularTextColor);
        mStatusView.setText(statusString);
        mProgressBar.setVisibility(View.GONE);
        if (mRefreshView.isRefreshing()) {
            mRefreshView.setRefreshing(false);
        }
        mRefreshView.setEnabled(true);
    }

    private void renderErrorViewState(Throwable throwable) {
        @StringRes int textResId = getErrorMessage(throwable);

        if (textResId == NO_RES_ID) {
            // fallback to completed state message
            renderCompletedViewState();
            return;
        }

        mStatusView.setTextColor(mErrorTextColor);
        mStatusView.setText(textResId);
        mProgressBar.setVisibility(View.GONE);
        if (mRefreshView.isRefreshing()) {
            mRefreshView.setRefreshing(false);
        }
        mRefreshView.setEnabled(true);
    }

    @StringRes
    private int getErrorMessage(Throwable throwable) {
        @StringRes int textResId = R.string.error_general; // fallback case

        if (throwable instanceof RequestException) {
            RequestException ex = (RequestException) throwable;
            switch (ex.getKind()) {
                case RequestException.HTTP:
                    switch (ex.getResponse().code()) {
                        case ApiResponseCode.FORBIDDEN:
                            textResId = R.string.error_forbidden;
                            break;
                        case ApiResponseCode.GONE:
                            textResId = R.string.error_session_expired;
                            break;
                        case ApiResponseCode.TOO_MANY_REQUESTS:
                            textResId = R.string.error_too_many_requests;
                            break;
                        case ApiResponseCode.SERVER_ERROR:
                            textResId = R.string.error_server;
                            break;
                        case ApiResponseCode.NOT_MODIFIED:
                            textResId = NO_RES_ID;
                            break;
                    }
                    break;
                case RequestException.NETWORK:
                    textResId = R.string.error_server_unavailable;
                    break;
                case RequestException.UNEXPECTED:
                    textResId = R.string.error_unexpected;
                    break;
            }
        } else if (throwable instanceof PollValidationException) {
            textResId = R.string.error_validation_failed;
        } else if (throwable instanceof NoContentException) {
            textResId = NO_RES_ID;
        }

        return textResId;
    }

    // endregion

    @Override
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            destroy();
        }
    }
}
