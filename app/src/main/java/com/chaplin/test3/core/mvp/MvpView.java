package com.chaplin.test3.core.mvp;

public interface MvpView {

    default void onStart() {
        // no-op
    }

    default void onResume() {
        // no-op
    }

    default void onPause() {
        // no-op
    }

    default void onStop() {
        // no-op
    }

    default void onDestroy() {
        // no-op
    }
}
