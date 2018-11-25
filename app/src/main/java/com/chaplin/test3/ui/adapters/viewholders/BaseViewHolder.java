package com.chaplin.test3.ui.adapters.viewholders;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.chaplin.test3.ui.adapters.binders.BaseViewBinder;
import com.chaplin.test3.ui.adapters.binders.BaseViewBinderItem;

public abstract class BaseViewHolder<T extends BaseViewBinderItem, B extends BaseViewBinder<T>> extends RecyclerView.ViewHolder {

    @NonNull
    private final B mViewBinder;

    public BaseViewHolder(@NonNull View itemView, @NonNull B viewBinder) {
        super(itemView);
        mViewBinder = viewBinder;
    }

    public final void bind(T item) {
        mViewBinder.bind(item);
    }
}
