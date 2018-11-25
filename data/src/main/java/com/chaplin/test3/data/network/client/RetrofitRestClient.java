package com.chaplin.test3.data.network.client;

import com.chaplin.test3.data.network.client.controller.PollController;
import com.chaplin.test3.data.network.client.controller.SessionInitController;
import com.chaplin.test3.data.network.core.DataRequest;
import com.chaplin.test3.data.network.core.DataResponse;
import com.chaplin.test3.data.network.core.RestClient;
import io.reactivex.Flowable;
import retrofit2.Retrofit;

import static com.chaplin.test3.data.network.client.ApiConstants.PARAM_POLLING_URL;

public class RetrofitRestClient extends RestClient<Flowable<DataResponse>> {

    private API mApi;

    public RetrofitRestClient(Retrofit retrofit) {
        mApi = retrofit.create(API.class);
    }

    @Override
    public Flowable<DataResponse> execute(DataRequest request) {
        final String operation = request.getOperation();

        Flowable<DataResponse> resultObservable = null;
        switch (operation) {
            case Requests.OP_POST_START_SESSION:
                resultObservable = SessionInitController.startSession(mApi, request.getParams());
                break;

            case Requests.OP_GET_POLL:
                resultObservable = PollController.poll(mApi, request.getParams().get(PARAM_POLLING_URL));
                break;

            case DataRequest.NO_OPERATION:
                // fall-through
            default:
                break;
        }
        if (resultObservable != null) {
            return resultObservable.map(dataResponse -> {
                dataResponse.request = request;
                return dataResponse;
            });
        }

        return Flowable.error(new Exception("Invalid Api operation!"));
    }
}
