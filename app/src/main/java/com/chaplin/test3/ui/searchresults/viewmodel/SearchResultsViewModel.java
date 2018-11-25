package com.chaplin.test3.ui.searchresults.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.chaplin.test3.data.model.enitity.SearchResultEntity;
import com.chaplin.test3.data.model.enitity.Session;
import com.chaplin.test3.domain.usecase.SearchUseCase;
import com.chaplin.test3.ui.searchresults.viewmodel.action.Action;
import io.reactivex.functions.Consumer;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class SearchResultsViewModel extends ViewModel {

    private static final int INITIAL_PAGE_SIZE = 50;
    private static final int DEFAULT_PAGE_SIZE = 50;
    private static final int PREFETCH_DISTANCE = 15;
    private static final int INITIAL_LOAD_KEY = 0;

    @NonNull
    private final Data mData;
    @NonNull
    private final SearchUseCase mSearchUseCase;
    @NonNull
    private final SearchResultsBoundaryCallback mBoundaryCallback;

    public SearchResultsViewModel(@NonNull DataSource.Factory<Integer, SearchResultEntity> dataSourceFactory,
                                  @NonNull SearchUseCase searchUseCase,
                                  @NonNull SearchResultsBoundaryCallback boundaryCallback) {
        mSearchUseCase = searchUseCase;
        mBoundaryCallback = boundaryCallback;
        mData = createData(dataSourceFactory);
    }

    @NonNull
    public Data getData() {
        return mData;
    }

    public Consumer<Action> getActionConsumer() {
        return this::acceptAction;
    }

    private void refresh() {
        mBoundaryCallback.resetPageIndex();

        mSearchUseCase.execute(0, new SearchSubscriber(mData.mDataLoadingViewState));
    }

    private Data createData(DataSource.Factory<Integer, SearchResultEntity> dataSourceFactory) {
        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(INITIAL_PAGE_SIZE)
                .setPageSize(DEFAULT_PAGE_SIZE)
                .setPrefetchDistance(PREFETCH_DISTANCE)
                .build();

        LiveData<PagedList<SearchResultEntity>> pagedList = new LivePagedListBuilder<>(dataSourceFactory, config)
                .setBoundaryCallback(mBoundaryCallback)
                .setInitialLoadKey(INITIAL_LOAD_KEY)
                .build();

        return new Data(pagedList, (MutableLiveData<DataLoadingViewState>) mBoundaryCallback.createNetworkStateLiveData());
    }

    private void acceptAction(Action action) {
        if (action instanceof Action.RefreshSearchResults) {
            refresh();
        }
    }

    private class SearchSubscriber implements Subscriber<Void> {

        @NonNull
        private final MutableLiveData<DataLoadingViewState> mDataViewState;

        private SearchSubscriber(@NonNull MutableLiveData<DataLoadingViewState> dataViewState) {
            mDataViewState = dataViewState;
        }

        @Override
        public void onSubscribe(Subscription s) {
            mDataViewState.setValue(DataLoadingViewState.loading());
        }

        @Override
        public void onNext(Void aVoid) {
            // no-op
        }

        @Override
        public void onError(Throwable t) {
            mDataViewState.setValue(DataLoadingViewState.error(t));
            mDataViewState.setValue(DataLoadingViewState.idle());
        }

        @Override
        public void onComplete() {
            mDataViewState.setValue(DataLoadingViewState.completed());
        }
    }

    public static class Data {
        @NonNull
        private final LiveData<PagedList<SearchResultEntity>> mPagedList;
        @NonNull
        private final MutableLiveData<DataLoadingViewState> mDataLoadingViewState;

        private Data(@NonNull LiveData<PagedList<SearchResultEntity>> mPagedList,
                     @NonNull MutableLiveData<DataLoadingViewState> mDataViewState) {
            this.mPagedList = mPagedList;
            this.mDataLoadingViewState = mDataViewState;
        }

        @NonNull
        public LiveData<PagedList<SearchResultEntity>> getPagedList() {
            return mPagedList;
        }

        @NonNull
        public LiveData<DataLoadingViewState> getDataLoadingViewState() {
            return mDataLoadingViewState;
        }
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final SearchUseCase mSearchUseCase;
        @NonNull
        private SearchResultsBoundaryCallback mBoundaryCallback;
        @NonNull
        DataSource.Factory<Integer, SearchResultEntity> mDataSourceFactory;

        public Factory(@NonNull SearchUseCase searchUseCase,
                       @NonNull DataSource.Factory<Integer, SearchResultEntity> dataSourceFactory,
                       @NonNull SearchResultsBoundaryCallback boundaryCallback) {
            mSearchUseCase = searchUseCase;
            mBoundaryCallback = boundaryCallback;
            mDataSourceFactory = dataSourceFactory;
        }

        @SuppressWarnings("unchecked")
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new SearchResultsViewModel(mDataSourceFactory, mSearchUseCase, mBoundaryCallback);
        }
    }
}
