package com.chaplin.test3.ui.searchresults.adapter.binders;

import android.widget.TextView;
import androidx.annotation.NonNull;
import com.chaplin.test3.ui.adapters.binders.BaseViewBinder;
import com.chaplin.test3.ui.searchresults.adapter.SearchResultsViewBinderItem;

public class PriceProviderViewBinder extends BaseViewBinder<SearchResultsViewBinderItem> {

    @NonNull
    private final TextView mPriceProviderView;

    public PriceProviderViewBinder(@NonNull TextView priceProviderView) {
        mPriceProviderView = priceProviderView;
    }

    @Override
    public void bind(SearchResultsViewBinderItem item) {
        mPriceProviderView.setText(item.getEntity().getAgentName());
    }
}
