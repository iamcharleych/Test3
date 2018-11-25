package com.chaplin.test3.domain.usecase;

import com.chaplin.test3.domain.execution.ExecutionThread;
import com.chaplin.test3.domain.repository.SearchResultsRepository;
import com.chaplin.test3.domain.repository.SessionRepository;
import io.reactivex.Flowable;

public class SearchUseCase extends SessionBasedUseCase<Void, Integer> {

    private final SearchResultsRepository mSearchResultsRepository;

    public SearchUseCase(ExecutionThread mWorkerThread,
                         ExecutionThread mObserverThread,
                         SessionRepository sessionRepository,
                         SearchResultsRepository searchResultsRepository) {
        super(mWorkerThread, mObserverThread, sessionRepository);
        mSearchResultsRepository = searchResultsRepository;
    }

    @Override
    public Flowable<Void> createObservable(Integer pageIndex) {
        return mSearchResultsRepository.search(pageIndex, false);
    }
}
