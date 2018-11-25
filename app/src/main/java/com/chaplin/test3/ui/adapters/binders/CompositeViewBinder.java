package com.chaplin.test3.ui.adapters.binders;

import androidx.annotation.NonNull;

public class CompositeViewBinder<T extends BaseViewBinderItem> extends BaseViewBinder<T> {

    @NonNull
    private final BaseViewBinder<T>[] mBinders;

    @SafeVarargs
    public CompositeViewBinder(@NonNull BaseViewBinder<T>... binders) {
        mBinders = binders;
    }

    @Override
    public void bind(T item) {
        for (BaseViewBinder<T> mBinder : mBinders) {
            mBinder.bind(item);
        }
    }
}
