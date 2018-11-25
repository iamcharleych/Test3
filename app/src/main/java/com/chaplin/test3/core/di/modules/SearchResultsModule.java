package com.chaplin.test3.core.di.modules;

import androidx.paging.DataSource;
import com.chaplin.test3.core.model.mapper.SearchResultModelMapper;
import com.chaplin.test3.data.db.AppDatabase;
import com.chaplin.test3.data.model.enitity.SearchResultEntity;
import com.chaplin.test3.data.model.enitity.Session;
import com.chaplin.test3.domain.execution.ExecutionThread;
import com.chaplin.test3.domain.repository.SearchResultsRepository;
import com.chaplin.test3.domain.repository.SessionRepository;
import com.chaplin.test3.domain.usecase.SearchUseCase;
import com.chaplin.test3.ui.searchresults.viewmodel.SearchResultsBoundaryCallback;
import com.chaplin.test3.ui.searchresults.viewmodel.SearchResultsViewModel;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;

import java.util.concurrent.Executors;

import static com.chaplin.test3.core.di.modules.ExecutionModule.ANDROID;
import static com.chaplin.test3.core.di.modules.ExecutionModule.IO;


@Module
public abstract class SearchResultsModule {

    @Provides
    static SearchUseCase provideSearchUseCase(
            @Named(IO) ExecutionThread workerThread,
            @Named(ANDROID) ExecutionThread observerThread,
            SessionRepository sessionRepo,
            SearchResultsRepository searchResultsRepo
    ) {
        return new SearchUseCase(workerThread, observerThread, sessionRepo, searchResultsRepo);
    }

    @Provides
    static DataSource.Factory<Integer, SearchResultEntity> provideSearchResultDataSource(AppDatabase database) {
        return database.getSearchResultsDao().loadSearchResults();
    }

    @Provides
    static SearchResultsBoundaryCallback provideSearchResultsBoundaryCallback(SearchResultsRepository repo, Session session) {
        return new SearchResultsBoundaryCallback(repo, session, Executors.newSingleThreadExecutor());
    }

    @Provides
    static SearchResultsViewModel.Factory provideSearchResultsViewModelFactory(
            SearchUseCase useCase,
            DataSource.Factory<Integer, SearchResultEntity> dataSource,
            SearchResultsBoundaryCallback callback) {
        return new SearchResultsViewModel.Factory(useCase, dataSource, callback);
    }

    @Provides
    static SearchResultModelMapper provideSearchResultModelMapper() {
        return new SearchResultModelMapper();
    }
}
