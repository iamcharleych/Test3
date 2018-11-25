package com.chaplin.test3.core.mvp;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.chaplin.test3.core.utils.ReflectionUtils;

public abstract class BaseMvpPresenter<MVP_VIEW extends MvpView, STATE extends State>
        implements GenericLifecycleObserver {

    @NonNull
    protected MVP_VIEW mView = createStubView();

    @VisibleForTesting(otherwise = VisibleForTesting.PACKAGE_PRIVATE)
    public final void attachView(MVP_VIEW view, @NonNull Lifecycle lifecycle, @Nullable STATE restoreState) {
        mView = view;

        onViewAttached(restoreState);
        lifecycle.addObserver(this);
    }

    @CallSuper
    protected void onViewAttached(@Nullable STATE restoreState) {
    }

    @Nullable
    protected STATE getSaveState() {
        return null;
    }

    @CallSuper
    @Override
    public void onStateChanged(LifecycleOwner owner, Lifecycle.Event event) {
        switch (event) {
            case ON_START:
                mView.onStart();
                break;
            case ON_RESUME:
                mView.onResume();
                break;
            case ON_PAUSE:
                mView.onPause();
                break;
            case ON_STOP:
                mView.onStop();
                break;
            case ON_DESTROY:
                owner.getLifecycle().removeObserver(this);
                mView.onDestroy();
                mView = createStubView();
                break;
        }
    }

    @SuppressWarnings("unchecked")
    private MVP_VIEW createStubView() {
        return (MVP_VIEW) ReflectionUtils.createProxyStubFromGenericTypeClassImpl(getClass());
    }
}
