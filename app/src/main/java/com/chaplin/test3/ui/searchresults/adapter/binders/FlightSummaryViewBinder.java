package com.chaplin.test3.ui.searchresults.adapter.binders;

import android.widget.TextView;
import androidx.annotation.NonNull;
import com.chaplin.test3.core.model.SearchResultModel;
import com.chaplin.test3.ui.adapters.binders.BaseViewBinder;
import com.chaplin.test3.ui.searchresults.adapter.SearchResultsViewBinderItem;

public class FlightSummaryViewBinder extends BaseViewBinder<SearchResultsViewBinderItem> {

    @NonNull
    private final TextView mSummaryView;
    private final boolean mIsOutbound;

    public FlightSummaryViewBinder(@NonNull TextView summaryView, boolean isOutbound) {
        mSummaryView = summaryView;
        mIsOutbound = isOutbound;
    }

    @Override
    public void bind(SearchResultsViewBinderItem item) {
        SearchResultModel entity = item.getEntity();
        mSummaryView.setText(mIsOutbound ? entity.getOutboundingSummary(): entity.getInboundingSummary());
    }
}
