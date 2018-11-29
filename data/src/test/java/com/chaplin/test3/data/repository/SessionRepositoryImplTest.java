package com.chaplin.test3.data.repository;

import com.chaplin.test3.common.utils.Pair;
import com.chaplin.test3.domain.exception.NoContentException;
import com.chaplin.test3.data.model.enitity.Session;
import com.chaplin.test3.data.network.core.DataRequest;
import com.chaplin.test3.data.network.core.DataResponse;
import com.chaplin.test3.data.network.core.RestClient;
import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SessionRepositoryImplTest {

    private static final String SESSION = "session string";

    @Mock
    private Session mSession;
    @Mock
    private RestClient<Flowable<DataResponse>> mRestClient;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(mSession.hasBasePollingUrl()).thenReturn(false);
        when(mSession.getBasePollingUrl()).thenReturn(SESSION);
    }

    @Test
    public void setupSession_validSessionExists_currentSessionValue() {
        // given
        TestSubscriber<String> subscriber = new TestSubscriber<>();
        when(mSession.hasBasePollingUrl()).thenReturn(true);
        when(mRestClient.execute(any(DataRequest.class))).thenReturn(Flowable.empty());

        // when
        SessionRepositoryImpl repo = new SessionRepositoryImpl(mRestClient, mSession);
        repo.setupSession().subscribe(subscriber);

        // then
        subscriber.assertResult(SESSION);
    }

    @Test
    public void setupSession_noValidSessionExists_newSessionValue() {
        // given
        TestSubscriber<String> subscriber = new TestSubscriber<>();
        DataResponse<String> dataResponse = new DataResponse<>();
        dataResponse.responseObject = SESSION;
        when(mRestClient.execute(any(DataRequest.class))).thenReturn(Flowable.just(dataResponse));

        // when
        SessionRepositoryImpl repo = new SessionRepositoryImpl(mRestClient, mSession);
        repo.setupSession().subscribe(subscriber);

        // then
        subscriber.assertResult(SESSION);
    }

    @Test
    public void setupSession_noValidSessionExists_subscriberGotError() {
        // given
        TestSubscriber<String> subscriber = new TestSubscriber<>();
        Throwable exception = new Throwable();
        when(mRestClient.execute(any(DataRequest.class))).thenReturn(Flowable.error(exception));

        // when
        SessionRepositoryImpl repo = new SessionRepositoryImpl(mRestClient, mSession);
        repo.setupSession().subscribe(subscriber);

        // then
        subscriber.assertError(exception);
    }

    @Test
    public void setupSession_noSessionContent_noContentError() {
        // given
        TestSubscriber<String> subscriber = new TestSubscriber<>();
        Throwable exception = new NoContentException();
        DataResponse<String> dataResponse = new DataResponse<>();
        dataResponse.responseObject = SESSION;
        when(mRestClient.execute(any(DataRequest.class))).thenReturn(Flowable.error(exception));

        // when
        SessionRepositoryImpl repo = new SessionRepositoryImpl(mRestClient, mSession) {
            @Override
            protected Flowable<Long> repeatTimer(Pair<Throwable, Integer> pair) {
                return Flowable.just(1L);
            }
        };

        repo.setupSession().subscribe(subscriber);

        // then source publisher is re-subsribed 3 times on NoContextException and completes with error on the 4th attempt
        // as test emitter emits response each time before emitting the exception - we expect 4 results
        subscriber.assertError(throwable -> throwable instanceof NoContentException);
    }

    @Test
    public void setupSession_succesAfter3Attempts_newSessionValue() {
        // given
        TestSubscriber<String> subscriber = new TestSubscriber<>();
        Throwable exception = new NoContentException();
        DataResponse<String> dataResponse = new DataResponse<>();
        dataResponse.responseObject = SESSION;

        class Counter {
            private int attempt = 0;

            private int get() {
                attempt += 1;
                return attempt;
            }
        }
        Counter c = new Counter();

        when(mRestClient.execute(any(DataRequest.class))).thenReturn(
                Flowable.just(exception)
                .flatMap(pair -> {
                    if (c.get() == 4) {
                        return Flowable.just(dataResponse);
                    }
                    return Flowable.error(exception);
                })
        );

        // when
        SessionRepositoryImpl repo = new SessionRepositoryImpl(mRestClient, mSession) {
            @Override
            protected Flowable<Long> repeatTimer(Pair<Throwable, Integer> pair) {
                return Flowable.just(1L);
            }
        };

        repo.setupSession().subscribe(subscriber);

        // then
        subscriber.assertResult(SESSION);
    }

}