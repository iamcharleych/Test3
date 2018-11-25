package com.chaplin.test3.ui.searchresults.viewmodel;

import android.arch.paging.PagingRequestHelper;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;
import com.chaplin.test3.core.utils.RxUtils;
import com.chaplin.test3.data.model.enitity.SearchResultEntity;
import com.chaplin.test3.data.model.enitity.Session;
import com.chaplin.test3.domain.repository.SearchResultsRepository;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.Executor;

public class SearchResultsBoundaryCallback extends PagedList.BoundaryCallback<SearchResultEntity> {

    @NonNull
    private final SearchResultsRepository mRepo;
    @NonNull
    private final PagingRequestHelper mPagingRequestHelper;
    @NonNull
    private final Session mSession;

    private int mPageIndex;

    public SearchResultsBoundaryCallback(@NonNull SearchResultsRepository repo,
                                         @NonNull Session session,
                                         @NonNull Executor workerExecutor) {
        mRepo = repo;
        mSession = session;
        mPagingRequestHelper = new PagingRequestHelper(workerExecutor);
    }

    // no items loaded from DB, let's ask server for more data
    @Override
    public void onZeroItemsLoaded() {
        mPageIndex = 0;

        if (!mSession.hasBasePollingUrl()) {
            return; // early exit
        }

        fetchDataFromServer(PagingRequestHelper.RequestType.INITIAL, mPageIndex);
    }

    @Override
    public void onItemAtFrontLoaded(@NonNull SearchResultEntity itemAtFront) {
        // no-op
    }

    @Override
    public void onItemAtEndLoaded(@NonNull SearchResultEntity itemAtEnd) {
        mPageIndex += 1;
        fetchDataFromServer(PagingRequestHelper.RequestType.AFTER, mPageIndex);
    }

    private void fetchDataFromServer(@NonNull PagingRequestHelper.RequestType type, @IntRange(from = 0) int pageIndex) {
        mPagingRequestHelper.runIfNotRunning(type, callback -> {
            final Disposable[] subscription = new Disposable[1];
            subscription[0] = mRepo.search(pageIndex, true)
                    .subscribe(
                            aVoid -> { /* no-op */ },
                            throwable -> {
                                RxUtils.safeUnsubscribe(subscription[0]);
                                mPageIndex -= 1;
                                if (mPageIndex < 0) {
                                    mPageIndex = 0;
                                }
                                callback.recordFailure(throwable);
                            }, () -> {
                                RxUtils.safeUnsubscribe(subscription[0]);
                                callback.recordSuccess();
                            });
        });
    }

    LiveData<DataLoadingViewState> createNetworkStateLiveData() {
        MutableLiveData<DataLoadingViewState> dataViewState = new MutableLiveData<>();

        mPagingRequestHelper.addListener(report -> {
            if (report.hasRunning()) {
                dataViewState.postValue(DataLoadingViewState.loading());
            } else if (report.hasError()) {
                Throwable t = null;
                for (PagingRequestHelper.RequestType type : PagingRequestHelper.RequestType.values()) {
                    t = report.getErrorFor(type);
                    if (t != null) {
                        break;
                    }
                }
                if (t != null) {
                    dataViewState.postValue(DataLoadingViewState.error(t));
                    dataViewState.postValue(DataLoadingViewState.idle());
                } else {
                    dataViewState.postValue(DataLoadingViewState.idle());
                }
            } else {
                dataViewState.postValue(DataLoadingViewState.completed());
            }
        });

        return dataViewState;
    }

    void resetPageIndex() {
        mPageIndex = 0;
    }
}
