package com.chaplin.test3.core.mvp;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseMvpFragment<VIEW extends BaseMvpView> extends Fragment {

    @Nullable
    private MvpProcessor<VIEW> mMvpProcessor;

    @CallSuper
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View rootView = getView();

        if (rootView == null) {
            return; // early exit
        }

        mMvpProcessor = new MvpProcessor<>(getViewLifecycleOwner().getLifecycle());
        initModelComponent(savedInstanceState);
        createViewPresenter(rootView, savedInstanceState);
    }

    protected abstract void initModelComponent(@Nullable Bundle savedInstanceState);

    /**
     * Use this method to create {@link MvpView} and {@link BaseMvpPresenter}.<br/>
     * Call {@link BaseMvpFragment#addMvpView(BaseMvpView, BaseMvpPresenter, Bundle)} to add view and presenter.
     */
    protected abstract void createViewPresenter(@NonNull View rootView, @Nullable Bundle savedInstanceState);

    public final void addMvpView(@NonNull VIEW mvpView, @NonNull BaseMvpPresenter presenter,
            @Nullable Bundle savedInstanceState) {
        if (mMvpProcessor != null) {
            mMvpProcessor.addMvpView(mvpView, presenter, savedInstanceState);
        }
    }

    @CallSuper
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mMvpProcessor != null) {
            mMvpProcessor.onSaveInstanceState(outState);
        }
    }
}
