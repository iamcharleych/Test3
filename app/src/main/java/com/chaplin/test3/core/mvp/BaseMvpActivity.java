package com.chaplin.test3.core.mvp;

import android.os.Bundle;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseMvpActivity<VIEW extends BaseMvpView> extends AppCompatActivity {


    @NonNull
    private final MvpProcessor<VIEW> mMvpProcessor = new MvpProcessor<>(getLifecycle());

    @CallSuper
    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initModelComponent(savedInstanceState);
        createViewPresenter(savedInstanceState);
    }

    protected abstract void initModelComponent(@Nullable Bundle savedInstanceState);

    /**
     * Use this method to create {@link MvpView} and {@link BaseMvpPresenter}.<br/>
     * Call {@link BaseMvpActivity#addMvpView(BaseMvpView, BaseMvpPresenter, Bundle)} to add view and presenter.
     */
    protected abstract void createViewPresenter(@Nullable Bundle savedInstanceState);

    public final void addMvpView(@NonNull VIEW mvpView, @NonNull BaseMvpPresenter presenter,
            @Nullable Bundle savedInstanceState) {
        mMvpProcessor.addMvpView(mvpView, presenter, savedInstanceState);
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMvpProcessor.onSaveInstanceState(outState);
    }
}