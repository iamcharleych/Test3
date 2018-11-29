package com.chaplin.test3.domain.usecase;

import com.chaplin.test3.domain.execution.ExecutionThread;
import com.chaplin.test3.domain.model.SearchResult;
import com.chaplin.test3.domain.repository.SearchResultsRepository;
import com.chaplin.test3.domain.repository.SessionRepository;
import io.reactivex.Flowable;

import java.util.List;

public class SearchUseCase extends SessionBasedUseCase<List<SearchResult>, Integer> {

    private final SearchResultsRepository mSearchResultsRepository;

    public SearchUseCase(ExecutionThread mWorkerThread,
                         ExecutionThread mObserverThread,
                         SessionRepository sessionRepository,
                         SearchResultsRepository searchResultsRepository) {
        super(mWorkerThread, mObserverThread, sessionRepository);
        mSearchResultsRepository = searchResultsRepository;
    }

    @Override
    public Flowable<List<SearchResult>> createObservable(Integer pageIndex) {
        return mSearchResultsRepository.search(pageIndex, false);
    }
}
