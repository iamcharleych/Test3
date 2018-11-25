package com.chaplin.test3.data.repository;

import androidx.annotation.NonNull;
import com.chaplin.test3.data.exception.NoContentException;
import com.chaplin.test3.data.model.enitity.Session;
import com.chaplin.test3.data.network.client.Requests;
import com.chaplin.test3.data.network.core.DataResponse;
import com.chaplin.test3.data.network.core.RestClient;
import com.chaplin.test3.domain.repository.SessionRepository;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class SessionRepositoryImpl implements SessionRepository {

    private static final long THREE_SEC = 3_000;

    @NonNull
    private final RestClient<Flowable<DataResponse>> mRestClient;
    @NonNull
    private final Session mSession;

    public SessionRepositoryImpl(@NonNull RestClient<Flowable<DataResponse>> restClient, @NonNull Session session) {
        mRestClient = restClient;
        mSession = session;
    }

    @Override
    public Flowable<String> setupSession() {
        Flowable<String> netBasedFlowable = mRestClient.execute(Requests.startSession())
                .retryWhen(error -> error
                        .takeWhile(throwable -> throwable instanceof NoContentException)
                        .zipWith(Flowable.range(1, 3), (err, attempt) -> attempt)
                        .flatMap(attemptNumber -> Flowable.timer(attemptNumber * THREE_SEC, TimeUnit.SECONDS)))
                .map(dataResponse -> (String) dataResponse.responseObject);

        return Flowable.fromCallable(() -> mSession.getBasePollingUrl() == null || mSession.getBasePollingUrl().isEmpty())
                .flatMap(startNewSession ->
                        (startNewSession || mSession.getBasePollingUrl() == null) ?
                                netBasedFlowable : Flowable.just(mSession.getBasePollingUrl()));
    }
}
