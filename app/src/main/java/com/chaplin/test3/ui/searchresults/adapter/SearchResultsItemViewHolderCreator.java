package com.chaplin.test3.ui.searchresults.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import com.chaplin.test3.ui.adapters.binders.CompositeViewBinder;
import com.chaplin.test3.ui.searchresults.adapter.binders.*;

public final class SearchResultsItemViewHolderCreator {

    @LayoutRes
    private final int mLayoutRes;

    public SearchResultsItemViewHolderCreator(@LayoutRes int layoutRes) {
        mLayoutRes = layoutRes;
    }

    SearchResultsAdapter.SearchResultsItemViewHolder createViewHolder(@NonNull LayoutInflater inflater,
                                                                      @NonNull ViewGroup container) {
        View root = inflater.inflate(mLayoutRes, container, false);
        SearchResultsItemViewHierarchy hierarchy = new SearchResultsItemViewHierarchy(root);

        CompositeViewBinder<SearchResultsViewBinderItem> binder = new CompositeViewBinder<>(
                new PriceViewBinder(hierarchy.priceView),
                new PriceProviderViewBinder(hierarchy.priceProviderView),
                // outbound
                new FlightDurationViewBinder(hierarchy.outboundFlightDurationView, true),
                new FlightTypeViewBinder(hierarchy.outboundFlightTypeView, true),
                new FlightTimingViewBinder(hierarchy.outboundFlightTimingView, true),
                new FlightSummaryViewBinder(hierarchy.outboundFlightSummaryView, true),
                // inbound
                new FlightDurationViewBinder(hierarchy.inboundFlightDurationView, false),
                new FlightTypeViewBinder(hierarchy.inboundFlightTypeView, false),
                new FlightTimingViewBinder(hierarchy.inboundFlightTimingView, false),
                new FlightSummaryViewBinder(hierarchy.inboundFlightSummaryView, false)
        );

        return new SearchResultsAdapter.SearchResultsItemViewHolder(root, binder);
    }

}
