package com.chaplin.test3.domain.usecase;

import com.chaplin.test3.domain.execution.ExecutionThread;
import com.chaplin.test3.domain.model.SearchResult;
import com.chaplin.test3.domain.repository.SearchResultsRepository;
import com.chaplin.test3.domain.repository.SessionRepository;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class SearchUseCaseTest {

    private static final String RESULT = "result string";
    private static final String SESSION = "session string";

    @Mock
    private ExecutionThread mSubscribeThread;
    @Mock
    private ExecutionThread mObserveThread;
    @Mock
    private SessionRepository mSessionRepository;
    @Mock
    private SearchResultsRepository mSearchRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(mSubscribeThread.getScheduler()).thenReturn(Schedulers.trampoline());
        when(mObserveThread.getScheduler()).thenReturn(Schedulers.trampoline());
        when(mSessionRepository.setupSession()).thenReturn(Flowable.just(SESSION));
        when(mSearchRepository.search(anyInt(), anyBoolean())).thenReturn(Flowable.empty());
    }

    @Test
    public void execute_validSource_successfulResult() {
        // given subscriber
        TestSubscriber<List<SearchResult>> subscriber = new TestSubscriber<>();
        SearchUseCase useCase = new SearchUseCase(mSubscribeThread, mObserveThread, mSessionRepository, mSearchRepository);

        // when executed
        useCase.execute(0, subscriber);

        // then onSubscribe called
        subscriber.assertSubscribed();
        subscriber.assertNoErrors();
        subscriber.assertResult();
        subscriber.assertComplete();
    }
}