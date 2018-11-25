package com.chaplin.test3.ui.searchresults.adapter.binders;

import android.widget.TextView;
import androidx.annotation.NonNull;
import com.chaplin.test3.core.model.SearchResultModel;
import com.chaplin.test3.ui.adapters.binders.BaseViewBinder;
import com.chaplin.test3.ui.searchresults.adapter.SearchResultsViewBinderItem;

public class FlightTypeViewBinder extends BaseViewBinder<SearchResultsViewBinderItem> {

    @NonNull
    private final TextView mTypeView;
    private final boolean mIsOutbound;

    public FlightTypeViewBinder(@NonNull TextView typeView, boolean isOutbound) {
        mTypeView = typeView;
        mIsOutbound = isOutbound;
    }

    @Override
    public void bind(SearchResultsViewBinderItem item) {
        SearchResultModel entity = item.getEntity();
        mTypeView.setText(mIsOutbound ? entity.getOutboundingFlightType() : entity.getInboundingFlightType());
    }
}
