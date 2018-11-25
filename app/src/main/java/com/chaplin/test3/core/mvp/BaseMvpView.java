package com.chaplin.test3.core.mvp;

import android.view.View;
import androidx.annotation.NonNull;

public abstract class BaseMvpView<PRESENTER extends BaseMvpPresenter> implements MvpView {
    @NonNull
    protected View mRootView;
    @NonNull
    protected final PRESENTER mPresenter;

    protected BaseMvpView(@NonNull PRESENTER presenter, @NonNull View rootView) {
        mRootView = rootView;
        mPresenter = presenter;
    }
}
