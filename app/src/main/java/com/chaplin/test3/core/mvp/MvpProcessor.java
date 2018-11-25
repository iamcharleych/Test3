package com.chaplin.test3.core.mvp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.lifecycle.Lifecycle;

/*package*/ class MvpProcessor<VIEW extends BaseMvpView> {

    @Nullable
    private BaseMvpPresenter mPresenter;
    @NonNull
    private StateKeeper mStateKeeper = new StateKeeper();

    @NonNull
    private final Lifecycle mLifecycle;

    /*package*/ MvpProcessor(@NonNull Lifecycle lifecycle) {
        mLifecycle = lifecycle;
    }

    @SuppressWarnings("unchecked")
    void addMvpView(@NonNull VIEW mvpView, @NonNull BaseMvpPresenter presenter,
            @Nullable Bundle savedInstanceState) {
        mPresenter = presenter;
        presenter.attachView(mvpView, mLifecycle, mStateKeeper.restoreState(presenter, savedInstanceState));
    }

    @UiThread
    void onSaveInstanceState(Bundle outState) {
        if (mPresenter != null) {
            mStateKeeper.saveState(mPresenter, outState);
        }
    }
}
