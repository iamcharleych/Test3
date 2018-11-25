package com.chaplin.test3.data.repository;

import androidx.annotation.NonNull;
import com.chaplin.test3.data.db.AppDatabase;
import com.chaplin.test3.data.db.dao.*;
import com.chaplin.test3.data.exception.PollValidationException;
import com.chaplin.test3.data.model.enitity.*;
import com.chaplin.test3.data.network.client.ApiConstants;
import com.chaplin.test3.data.network.client.Requests;
import com.chaplin.test3.data.network.client.controller.PollController;
import com.chaplin.test3.data.network.core.DataResponse;
import com.chaplin.test3.data.network.core.RestClient;
import com.chaplin.test3.data.pref.Pref;
import com.chaplin.test3.domain.exception.SessionExpiredException;
import com.chaplin.test3.domain.repository.SearchResultsRepository;
import io.reactivex.Flowable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class SearchResultsRepositoryImpl implements SearchResultsRepository {

    private static final long THREE_SEC = 3_000;

    @NonNull
    private final RestClient<Flowable<DataResponse>> mRestClient;
    @NonNull
    private final AppDatabase mDatabase;
    @NonNull
    private final Session mSession;

    public SearchResultsRepositoryImpl(@NonNull RestClient<Flowable<DataResponse>> restClient,
                                       @NonNull AppDatabase database, @NonNull Session session) {
        mRestClient = restClient;
        mDatabase = database;
        mSession = session;
    }

    @Override
    public Flowable<Void> search(int pageIndex, boolean appendResults) {
        return Flowable.fromCallable(() -> {
            if (!mSession.hasBasePollingUrl()) {
                throw new SessionExpiredException();
            }
            return mSession.getPollingUrl(pageIndex);
        })
                .flatMap(pollingUrl -> mRestClient.execute(Requests.poll(pollingUrl)))
                .repeatWhen(flowable -> flowable
                        .takeWhile(throwable -> throwable instanceof SessionExpiredException)
                        .zipWith(Flowable.range(1, 3), (err, attempt) -> attempt)
                        .flatMap(attemptNumber -> Flowable.timer(attemptNumber * THREE_SEC, TimeUnit.SECONDS)))
                .takeUntil(dataResponse -> !shouldContinuePolling((PollController.Parser) dataResponse.responseObject))
                .filter(dataResponse -> !shouldContinuePolling((PollController.Parser) dataResponse.responseObject))
                .map(dataResponse -> (PollController.Parser) dataResponse.responseObject)
                .doOnNext(parser -> {
                    if (!validateResponse(parser)) {
                        throw new PollValidationException();
                    }

                    CurrencyEntity currencyModel = parser.parseCurrency();
                    if (currencyModel != null) { // compiler warning fix, should never occur as it's checked while validation
                        Pref.Currency.init(currencyModel);
                    }

                    savePollResultToDatabase(parser, !appendResults);
                }).flatMap(parser -> Flowable.empty());
    }

    private boolean shouldContinuePolling(PollController.Parser parser) {
        return !ApiConstants.UPDATES_COMPLETED.equals(parser.parseStatus());
    }

    private boolean validateResponse(PollController.Parser parser) {
        return parser.parseCurrency() != null;
    }

    // region Save helpers

    private void savePollResultToDatabase(PollController.Parser parser, boolean clearAllBeforeSave) {
        try {
            mDatabase.beginTransaction();

            if (clearAllBeforeSave) {
                mDatabase.getItineraryDao().deleteAllPricingOptions();
                mDatabase.getItineraryDao().deleteAll();
                mDatabase.getLegDao().deleteAll();
                mDatabase.getSegmentDao().deleteAll();
                mDatabase.getAgentDao().deleteAll();
                mDatabase.getCarrierDao().deleteAll();
                mDatabase.getPlaceDao().deleteAll();
            }

            saveAgents(mDatabase.getAgentDao(), parser.parseAgents());
            saveCarriers(mDatabase.getCarrierDao(), parser.parseCarriers());
            savePlaces(mDatabase.getPlaceDao(), parser.parsePlaces());
            saveSegments(mDatabase.getSegmentDao(), parser.parseSegments());
            saveLegs(mDatabase.getLegDao(), parser.parseLegs());
            saveItineraries(mDatabase.getItineraryDao(), parser.parseItineraries());

            mDatabase.setTransactionSuccessful();
        } finally {
            mDatabase.endTransaction();
        }
    }

    private void saveAgents(AgentDao dao, List<AgentEntity> agents) {
        dao.insert(agents);
    }

    private void saveCarriers(CarrierDao dao, List<CarrierEntity> carriers) {
        dao.insert(carriers);
    }

    private void savePlaces(PlaceDao dao, List<PlaceEntity> places) {
        dao.insert(places);
    }

    private void saveSegments(SegmentDao dao, List<SegmentEntity> segments) {
        dao.insert(segments);
    }

    private void saveLegs(LegDao dao, List<LegEntity> legs) {
        ArrayList<FlightNumberEntity> flightNumbers = new ArrayList<>();
        ArrayList<Leg2SegmentEntity> legSegmentPairs = new ArrayList<>();
        for (LegEntity leg : legs) {
            final long carrierId = leg.getCarriersIds().get(0);
            final long operatingCarrierId = leg.getOperatingCarriersIds().get(0);
            final String id = leg.getId();

            leg.setCarrierId(carrierId);
            leg.setOperatingCarrierId(operatingCarrierId);

            List<FlightNumberEntity> fNumbers = leg.getFlightNumbers();
            for (FlightNumberEntity flightNumber : fNumbers) {
                flightNumber.setCarrierId(carrierId);
                flightNumber.setLegId(id);
            }
            flightNumbers.addAll(fNumbers);

            List<Long> segmentsIds = leg.getSegmentIds();
            for (Long segmentId : segmentsIds) {
                Leg2SegmentEntity legSegmentPair = new Leg2SegmentEntity();
                legSegmentPair.setLegId(id);
                legSegmentPair.setSegmentId(segmentId);
                legSegmentPairs.add(legSegmentPair);
            }
        }
        dao.insert(legs);
        dao.insertFlightNumbers(flightNumbers);
        dao.insertLeg2Segments(legSegmentPairs);
    }

    private void saveItineraries(ItineraryDao dao, List<ItineraryEntity> itineraries) {
        ArrayList<PricingOptionEntity> pricingOptions = new ArrayList<>();
        for (ItineraryEntity itinerary : itineraries) {
            final String outLegId = itinerary.getOutboundLegId();
            final String inLegId = itinerary.getInboundLegId();

            List<PricingOptionEntity> pOptions = itinerary.getPricingOptions();
            for (PricingOptionEntity pricingOption : pOptions) {
                pricingOption.setItineraryOutboundLegId(outLegId);
                pricingOption.setItineraryInboundLegId(inLegId);
                pricingOption.setAgentId(pricingOption.getAgents().get(0));
            }
            pricingOptions.addAll(pOptions);
        }
        dao.insert(itineraries);
        dao.insertPricingOptions(pricingOptions);
    }

    // endregion
}
