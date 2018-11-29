package com.chaplin.test3.data.network.client;

import com.chaplin.test3.domain.exception.NoContentException;
import com.chaplin.test3.domain.exception.SessionExpiredException;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import retrofit2.*;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {

    private final RxJava2CallAdapterFactory original;

    private RxErrorHandlingCallAdapterFactory() {
        original = RxJava2CallAdapterFactory.create();
    }

    public static CallAdapter.Factory create() {
        return new RxErrorHandlingCallAdapterFactory();
    }

    @Override
    public CallAdapter<?,?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxCallAdapterWrapper(retrofit, original.get(returnType, annotations, retrofit));
    }

    private static class RxCallAdapterWrapper implements CallAdapter {
        private final Retrofit retrofit;
        private final CallAdapter<?,?> wrapped;

        RxCallAdapterWrapper(Retrofit retrofit, CallAdapter<?,?> wrapped) {
            this.retrofit = retrofit;
            this.wrapped = wrapped;
        }

        @Override
        public Type responseType() {
            return wrapped.responseType();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Flowable<?> adapt(Call call) {
            return ((Flowable) wrapped.adapt(call)).doOnNext(o -> {
                if (o instanceof Response) {
                    Response response = (Response) o;

                    final int responseCode = response.code();
                    if (200 > responseCode || responseCode >= 300) {
                        if (ApiResponseCode.GONE == responseCode) {
                            throw new SessionExpiredException();
                        }
                        throw new HttpException(response);
                    } else if (ApiResponseCode.NO_CONTENT == responseCode) {
                        throw new NoContentException();
                    }
                }
            }).onErrorResumeNext((Function<Throwable, Flowable>) throwable -> Flowable.error(asRetrofitException(throwable)));
        }

        private RequestException asRetrofitException(Throwable throwable) {
            // We had non-200 http error
            if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                Response response = httpException.response();
                return RequestException.httpError(response.raw().request().url().toString(), response, retrofit);
            }
            // A network error happened
            if (throwable instanceof IOException) {
                return RequestException.networkError((IOException) throwable);
            }

            // We don't know what happened. We need to simply convert to an unknown error

            return RequestException.unexpectedError(throwable);
        }
    }
}
