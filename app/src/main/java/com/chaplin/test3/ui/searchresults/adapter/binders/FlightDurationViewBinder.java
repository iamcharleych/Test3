package com.chaplin.test3.ui.searchresults.adapter.binders;

import android.widget.TextView;
import androidx.annotation.NonNull;
import com.chaplin.test3.core.model.SearchResultModel;
import com.chaplin.test3.ui.adapters.binders.BaseViewBinder;
import com.chaplin.test3.ui.searchresults.adapter.SearchResultsViewBinderItem;

public class FlightDurationViewBinder extends BaseViewBinder<SearchResultsViewBinderItem> {

    @NonNull
    private final TextView mDurationView;
    private final boolean mIsOutbound;

    public FlightDurationViewBinder(@NonNull TextView durationView, boolean isOutbound) {
        mDurationView = durationView;
        mIsOutbound = isOutbound;
    }

    @Override
    public void bind(SearchResultsViewBinderItem item) {
        SearchResultModel entity = item.getEntity();
        mDurationView.setText(mIsOutbound ? entity.getOutboundingDuration() : entity.getInboundingDuration());
    }
}
