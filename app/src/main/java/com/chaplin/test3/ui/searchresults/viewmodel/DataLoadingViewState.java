package com.chaplin.test3.ui.searchresults.viewmodel;

public class DataLoadingViewState {

    public static DataLoadingViewState idle() {
        return new DataLoadingViewState(true, false, false, null);
    }

    public static DataLoadingViewState loading() {
        return new DataLoadingViewState(false, true, false, null);
    }

    public static DataLoadingViewState completed() {
        return new DataLoadingViewState(false, false, true, null);
    }

    public static DataLoadingViewState error(Throwable t) {
        return new DataLoadingViewState(false, false, false, t);
    }

    private final boolean mIsIdle;
    private final boolean mIsLoading;
    private final boolean mIsCompleted;
    private final Throwable mThrowable;

    private DataLoadingViewState(boolean isIdle, boolean isLoading, boolean isCompleted, Throwable throwable) {
        mIsIdle = isIdle;
        mIsLoading = isLoading;
        mIsCompleted = isCompleted;
        mThrowable = throwable;
    }

    public boolean isIdle() {
        return mIsIdle;
    }

    public boolean isLoading() {
        return mIsLoading;
    }

    public boolean isCompleted() {
        return mIsCompleted;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }
}