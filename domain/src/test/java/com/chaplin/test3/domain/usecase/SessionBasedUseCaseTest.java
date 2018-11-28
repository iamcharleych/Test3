package com.chaplin.test3.domain.usecase;

import com.chaplin.test3.domain.exception.SessionExpiredException;
import com.chaplin.test3.domain.execution.ExecutionThread;
import com.chaplin.test3.domain.repository.SessionRepository;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SessionBasedUseCaseTest {

    private static final String RESULT = "result string";
    private static final String SESSION = "session string";

    @Mock
    private ExecutionThread mSubscribeThread;
    @Mock
    private ExecutionThread mObserveThread;
    @Mock
    private SessionRepository mSessionRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(mSubscribeThread.getScheduler()).thenReturn(Schedulers.trampoline());
        when(mObserveThread.getScheduler()).thenReturn(Schedulers.trampoline());
        when(mSessionRepository.setupSession()).thenReturn(Flowable.just(SESSION));
    }

    @Test
    public void execute_sessionSetupTriggered() {
        // given session based use case
        SessionBasedUseCase<String, String> useCase = new SessionBasedUseCase<String, String>(mSubscribeThread,
                mObserveThread, mSessionRepository) {
            @Override
            public Flowable<String> createObservable(String s) {
                return Flowable.just(RESULT);
            }
        };

        // when executing
        useCase.execute("", new TestSubscriber<>());

        // then session repo is triggered
        verify(mSessionRepository).setupSession();
    }

    @Test
    public void execute_sessionExpired_repeatThreeTimesBeforeCompleted() {
        // given use case & subscriber
        final Throwable exception = new SessionExpiredException();
        final TestSubscriber<String> subscriber = new TestSubscriber<>();
        SessionBasedUseCase<String, String> useCase = new SessionBasedUseCase<String, String>(mSubscribeThread,
                mObserveThread, mSessionRepository) {
            @Override
            public Flowable<String> createObservable(String s) {
                return Flowable.create(emitter -> {
                    emitter.onNext(RESULT);
                    emitter.onError(exception);
                }, BackpressureStrategy.BUFFER);
            }

            @Override
            protected Flowable repeatTimer(Flowable<Throwable> throwableFlowable) {
                return throwableFlowable
                        .takeWhile(throwable -> throwable instanceof SessionExpiredException)
                        .zipWith(Flowable.range(1, 3), (err, attempt) -> attempt);
            }
        };

        // when executed
        useCase.execute("", subscriber);

        // then onError called
        subscriber.assertResult(RESULT, RESULT, RESULT);
        subscriber.assertComplete();
    }

}