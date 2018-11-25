package com.chaplin.test3.ui.searchresults.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.chaplin.test3.R;

final class SearchResultsItemViewHierarchy {

    @NonNull
    TextView outboundFlightTimingView;
    @NonNull
    TextView outboundFlightSummaryView;
    @NonNull
    TextView outboundFlightTypeView;
    @NonNull
    TextView outboundFlightDurationView;
    @NonNull
    TextView inboundFlightTimingView;
    @NonNull
    TextView inboundFlightSummaryView;
    @NonNull
    TextView inboundFlightTypeView;
    @NonNull
    TextView inboundFlightDurationView;
    @NonNull
    TextView priceView;
    @NonNull
    TextView priceProviderView;

    SearchResultsItemViewHierarchy(View rootView) {
        View outboundFlightContainer = rootView.findViewById(R.id.outboundFlightContainer);
        outboundFlightTimingView = outboundFlightContainer.findViewById(R.id.flightTiming);
        outboundFlightSummaryView = outboundFlightContainer.findViewById(R.id.flightSummary);
        outboundFlightTypeView = outboundFlightContainer.findViewById(R.id.flightType);
        outboundFlightDurationView = outboundFlightContainer.findViewById(R.id.flightDuration);
        View inboundFlightContainer = rootView.findViewById(R.id.inboundFlightContainer);
        inboundFlightTimingView = inboundFlightContainer.findViewById(R.id.flightTiming);
        inboundFlightSummaryView = inboundFlightContainer.findViewById(R.id.flightSummary);
        inboundFlightTypeView = inboundFlightContainer.findViewById(R.id.flightType);
        inboundFlightDurationView = inboundFlightContainer.findViewById(R.id.flightDuration);
        priceView = rootView.findViewById(R.id.priceView);
        priceProviderView = rootView.findViewById(R.id.priceProviderView);
    }

}
