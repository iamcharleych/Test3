package com.chaplin.test3.data.network.client;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef({
    ApiResponseCode.SUCCESS,
        ApiResponseCode.NO_CONTENT,
        ApiResponseCode.NOT_MODIFIED,
        ApiResponseCode.BAD_REQUEST,
        ApiResponseCode.FORBIDDEN,
        ApiResponseCode.GONE,
        ApiResponseCode.TOO_MANY_REQUESTS,
        ApiResponseCode.SERVER_ERROR
})
@Retention(RetentionPolicy.SOURCE)
public @interface ApiResponseCode {
    /**
     * Ok
     */
    int SUCCESS = 200;
    /**
     * The session is still being created (wait and try again).
     */
    int NO_CONTENT = 204;
    /**
     * The results have not been modified since the last search.
     */
    int NOT_MODIFIED = 304;
    /**
     * Input validation failed.
     */
    int BAD_REQUEST = 400;
    /**
     * The API Key was not supplied, or it was invalid, or it is not authorized to access the service.
     */
    int FORBIDDEN = 403;
    /**
     * The session has expired. A new session must be created.
     */
    int GONE = 410;
    /**
     * There have been too many requests in the last minute.
     */
    int TOO_MANY_REQUESTS = 429;
    /**
     * An internal server error has occurred which has been logged.
     */
    int SERVER_ERROR = 500;
}
