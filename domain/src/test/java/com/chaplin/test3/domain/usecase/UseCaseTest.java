package com.chaplin.test3.domain.usecase;

import com.chaplin.test3.domain.execution.ExecutionThread;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class UseCaseTest {

    private static final String RESULT = "result string";
    private static final String PARAM = "param string";

    @Mock
    private ExecutionThread mSubscribeThread;
    @Mock
    private ExecutionThread mObserveThread;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(mSubscribeThread.getScheduler()).thenReturn(Schedulers.trampoline());
        when(mObserveThread.getScheduler()).thenReturn(Schedulers.trampoline());
    }

    @Test
    public void execute_validSource_successfulResult() {
        // given subscriber
        TestSubscriber<String> subscriber = new TestSubscriber<>();
        UseCase<String, String> useCase = new UseCase<String, String>(mSubscribeThread, mObserveThread) {
            @Override
            public Flowable<String> createObservable(String s) {
                return Flowable.just(RESULT);
            }
        };

        // when executed
        useCase.execute(PARAM, subscriber);

        // then onSubscribe called
        subscriber.assertSubscribed();
        subscriber.assertNoErrors();
        subscriber.assertResult(RESULT);
        subscriber.assertComplete();
    }

    @Test
    public void execute_fireException_onErrorExecuted() {
        // given use case & subscriber
        final Throwable exception = new Throwable();
        TestSubscriber<String> subscriber = new TestSubscriber<>();
        UseCase<String, String> useCase = new UseCase<String, String>(mSubscribeThread, mObserveThread) {
            @Override
            public Flowable<String> createObservable(String s) {
                return Flowable.error(exception);
            }
        };

        // when executed
        useCase.execute(PARAM, subscriber);

        // then onError called
        subscriber.assertError(exception);
    }
}