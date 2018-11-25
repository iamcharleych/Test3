package com.chaplin.test3.data.network.client;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;

public class RequestException extends RuntimeException {
    public static RequestException httpError(String url, Response response, Retrofit retrofit) {
        String message = response.code() + " " + response.message();
        return new RequestException(message, url, response, HTTP, null, retrofit);
    }

    public static RequestException networkError(IOException exception) {
        return new RequestException(exception.getMessage(), null, null, NETWORK, exception, null);
    }

    public static RequestException unexpectedError(Throwable exception) {
        return new RequestException(exception.getMessage(), null, null, UNEXPECTED, exception, null);
    }

    private static final int STATUS_UNAUTHORIZED        = 401;
    private static final int STATUS_NOT_FOUND           = 404;
    private static final int STATUS_BAD_REQUEST         = 400;
    private static final int STATUS_FORBIDDEN           = 403;
    private static final int STATUS_SERVER_ERROR        = 500;
    private static final int STATUS_NOT_IMPLEMENTED     = 501;
    private static final int STATUS_BAD_GATEWAY         = 502;
    private static final int STATUS_SERVICE_UNAVAILABLE = 503;

    /** Identifies the event kind which triggered a {@link RequestException}. */

    /** An {@link IOException} occurred while communicating to the server. */
    public static final int NETWORK = 1;
    /** A non-200 HTTP status code was received from the server. */
    public static final int HTTP = 2;
    /**
     * An internal error occurred while attempting to execute a request. It is best practice to
     * re-throw this exception so your application crashes.
     */
    public static final int UNEXPECTED = 3;

    private final String url;
    private final Response response;
    private final int kind;
    private final Retrofit retrofit;

    RequestException(String message, String url, Response response, int kind, Throwable exception, Retrofit retrofit) {
        super(message, exception);
        this.url = url;
        this.response = response;
        this.kind = kind;
        this.retrofit = retrofit;
    }

    /** The request URL which produced the error. */
    public String getUrl() {
        return url;
    }

    /** Response object containing status code, headers, body, etc. */
    public Response getResponse() {
        return response;
    }

    /** The event kind which triggered this error. */
    public int getKind() {
        return kind;
    }

    /** The Retrofit this request was executed on */
    public Retrofit getRetrofit() {
        return retrofit;
    }

    public boolean isTimeout() {
        if (response != null) {
            int code = response.code();
            return code == STATUS_BAD_GATEWAY || code == STATUS_SERVICE_UNAVAILABLE;
        }
        return false;
    }

    /**
     * HTTP response body converted to specified {@code type}. {@code null} if there is no
     * response.
     *
     * @throws IOException if unable to convert the body to the specified {@code type}.
     */
    public <T> T getErrorBodyAs(Class<T> type) throws IOException {
        if (response == null || response.errorBody() == null) {
            return null;
        }
        Converter<ResponseBody, T> converter = retrofit.responseBodyConverter(type, new Annotation[0]);
        return converter.convert(response.errorBody());
    }
}
