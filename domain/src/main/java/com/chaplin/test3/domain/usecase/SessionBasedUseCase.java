package com.chaplin.test3.domain.usecase;

import com.chaplin.test3.domain.exception.SessionExpiredException;
import com.chaplin.test3.domain.execution.ExecutionThread;
import com.chaplin.test3.domain.repository.SessionRepository;
import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

public abstract class SessionBasedUseCase<RESULT, PARAMS> extends UseCase<RESULT, PARAMS> {

    private static final long THREE_SEC = 3_000;

    private final SessionRepository mSessionRepository;

    public SessionBasedUseCase(ExecutionThread mWorkerThread, ExecutionThread mObserverThread, SessionRepository sessionRepository) {
        super(mWorkerThread, mObserverThread);
        mSessionRepository = sessionRepository;
    }

    @Override
    protected final Flowable<RESULT> appendOperators(Flowable<RESULT> flowable) {
        return flowable.retryWhen(error -> error
                .takeWhile(throwable -> throwable instanceof SessionExpiredException)
                .zipWith(Flowable.range(1, 3), (err, attempt) -> attempt)
                .flatMap(attemptNumber -> Flowable.timer(attemptNumber * THREE_SEC, TimeUnit.SECONDS)));
    }

    @Override
    protected final Flowable<?> createPreSourceObservable() {
        return mSessionRepository.setupSession();
    }
}
