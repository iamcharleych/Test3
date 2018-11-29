package com.chaplin.test3.domain.usecase;

import com.chaplin.test3.domain.execution.ExecutionThread;
import io.reactivex.annotations.NonNull;
import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class UseCase<RESULT, PARAMS> {

    private ExecutionThread mWorkerThread;
    private ExecutionThread mObserverThread;
    private CompositeDisposable mSubscriptions;

    public UseCase(ExecutionThread mWorkerThread, ExecutionThread mObserverThread) {
        this.mWorkerThread = mWorkerThread;
        this.mObserverThread = mObserverThread;
        this.mSubscriptions = new CompositeDisposable();
    }

    protected abstract Flowable<RESULT> createObservable(PARAMS params);

    protected Flowable<RESULT> appendOperators(Flowable<RESULT> flowable) {
        return flowable;
    }

    protected Flowable<?> createPreSourceObservable() {
        return null;
    }

    @SuppressWarnings("unchecked")
    public final void execute(PARAMS params, Subscriber<RESULT> subscriber) {
        if (subscriber == null) {
            return; // early exit
        }

        Flowable<RESULT> flowable = createObservable(params);

        Flowable preSource = createPreSourceObservable();
        if (preSource != null) {
            final Flowable<RESULT> finalFlowable = flowable;
            flowable = preSource.flatMap(t -> finalFlowable);
        }
        flowable = appendOperators(flowable);
        subscribeInternally(flowable, subscriber);
    }

    private void subscribeInternally(Flowable<RESULT> flowable, @NonNull Subscriber<RESULT> subscriber) {
        Disposable subscription = flowable.subscribeOn(mWorkerThread.getScheduler())
                .observeOn(mObserverThread.getScheduler())
                .subscribe(
                        subscriber::onNext,
                        subscriber::onError,
                        subscriber::onComplete,
                        subscriber::onSubscribe
                );
        addSubscription(subscription);
    }

    public void dispose() {
        if (!mSubscriptions.isDisposed()) {
            mSubscriptions.dispose();
        }
    }

    private void addSubscription(Disposable subscription) {
        if (subscription == null) {
            return; // early exit
        }

        mSubscriptions.add(subscription);
    }
}
