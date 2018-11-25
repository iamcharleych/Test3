package com.chaplin.test3.core.mvp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/*package*/ class StateKeeper {

    void saveState(@NonNull BaseMvpPresenter presenter, @NonNull Bundle outState) {
        State state = presenter.getSaveState();
        if (state != null) {
            outState.putParcelable(getPresenterKey(presenter), state);
        }
    }

    @Nullable
    State restoreState(@NonNull BaseMvpPresenter presenter, @Nullable Bundle savedInstanceState) {
        return savedInstanceState == null ? null : savedInstanceState.getParcelable(getPresenterKey(presenter));
    }

    private String getPresenterKey(@NonNull BaseMvpPresenter presenter) {
        return "state_key_" + presenter.getClass().getName();
    }
}
