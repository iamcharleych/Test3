package com.chaplin.test3.ui.searchresults.adapter.binders;

import android.widget.TextView;
import androidx.annotation.NonNull;
import com.chaplin.test3.ui.adapters.binders.BaseViewBinder;
import com.chaplin.test3.ui.searchresults.adapter.SearchResultsViewBinderItem;

public class PriceViewBinder extends BaseViewBinder<SearchResultsViewBinderItem> {

    @NonNull
    private final TextView mPriceView;

    public PriceViewBinder(@NonNull TextView priceView) {
        mPriceView = priceView;
    }

    @Override
    public void bind(SearchResultsViewBinderItem item) {
        mPriceView.setText(item.getEntity().getPrice());
    }
}
