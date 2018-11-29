package com.chaplin.test3.data.repository;

import androidx.annotation.NonNull;
import com.chaplin.test3.androidcommon.utils.Pair;
import com.chaplin.test3.domain.exception.NoContentException;
import com.chaplin.test3.data.model.enitity.Session;
import com.chaplin.test3.data.network.client.Requests;
import com.chaplin.test3.data.network.core.DataResponse;
import com.chaplin.test3.data.network.core.RestClient;
import com.chaplin.test3.domain.repository.SessionRepository;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public class SessionRepositoryImpl implements SessionRepository {

    private static final long THREE_SEC = 3;
    private static final long MAX_RETRY_ATTEMPTS = 3;

    @NonNull
    private final RestClient<Flowable<DataResponse>> mRestClient;
    @NonNull
    private final Session mSession;

    public SessionRepositoryImpl(@NonNull RestClient<Flowable<DataResponse>> restClient, @NonNull Session session) {
        mRestClient = restClient;
        mSession = session;
    }

    @Override
    public final Flowable<String> setupSession() {
        Flowable<String> netBasedFlowable = mRestClient.execute(Requests.startSession())
                .retryWhen(throwableFlowable -> throwableFlowable
                        .zipWith(Flowable.range(0, Integer.MAX_VALUE), Pair::new)
                        .flatMap(pair -> {
                            assert pair.second != null;
                            assert pair.first != null;

                            if (pair.first instanceof NoContentException && pair.second < MAX_RETRY_ATTEMPTS) {
                                return repeatTimer(pair);
                            }
                            return Flowable.error(pair.first);
                        })
                )
                .map(dataResponse -> (String) dataResponse.responseObject)
                .doOnNext(mSession::updateBasePollingUrl);


        return Flowable.just(mSession).flatMap(startNewSession -> !mSession.hasBasePollingUrl() ?
                                netBasedFlowable : Flowable.just(mSession.getBasePollingUrl()));
    }



    protected Flowable<Long> repeatTimer(Pair<Throwable, Integer> pair) {
        assert pair.second != null;
        final int attempt = pair.second;
        return Flowable.timer(attempt * THREE_SEC, TimeUnit.SECONDS);
    }
}
