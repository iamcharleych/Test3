package com.chaplin.test3.core.mvp;

import android.os.Parcel;
import android.os.Parcelable;

public abstract class State implements Parcelable {

    public State() {
    }

    protected State(Parcel in) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
