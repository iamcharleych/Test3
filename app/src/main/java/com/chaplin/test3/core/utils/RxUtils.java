package com.chaplin.test3.core.utils;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import java.util.HashMap;

public class RxUtils {

    private static final HashMap<Object, CompositeDisposable> sDiposables = new HashMap<>();

    public static void unsubscribe(Object tag) {
        CompositeDisposable disposables = sDiposables.get(tag);
        if (disposables != null) {
            disposables.dispose();
            sDiposables.remove(tag);
        }
    }

    public static void manage(Object tag, Disposable subscription) {
        CompositeDisposable subscriptions = sDiposables.get(tag);
        if (subscriptions == null) {
            subscriptions = new CompositeDisposable();
            sDiposables.put(tag, subscriptions);
        }

        subscriptions.add(subscription);
    }

    public static void safeUnsubscribe(Disposable d) {
        if (d != null && !d.isDisposed()) {
            d.dispose();
        }
    }

    public static boolean isActiveSubscription(Disposable d) {
        return d != null && !d.isDisposed();
    }

}
