package com.chaplin.test3.ui.searchresults.adapter.binders;

import android.widget.TextView;
import androidx.annotation.NonNull;
import com.chaplin.test3.core.model.SearchResultModel;
import com.chaplin.test3.ui.adapters.binders.BaseViewBinder;
import com.chaplin.test3.ui.searchresults.adapter.SearchResultsViewBinderItem;

public class FlightTimingViewBinder extends BaseViewBinder<SearchResultsViewBinderItem> {

    @NonNull
    private final TextView mTimingView;
    private final boolean mIsOutbound;

    public FlightTimingViewBinder(@NonNull TextView timingView, boolean isOutbound) {
        mTimingView = timingView;
        mIsOutbound = isOutbound;
    }

    @Override
    public void bind(SearchResultsViewBinderItem item) {
        SearchResultModel entity = item.getEntity();
        mTimingView.setText(mIsOutbound ? entity.getOutboundingTiming() : entity.getInboundingTiming());
    }
}
