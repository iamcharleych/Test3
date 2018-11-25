package com.chaplin.test3.common.pref;

import android.content.Context;
import android.content.SharedPreferences;

abstract class BasePref<T> {

    private static final String PREFS_FILE = "com.chaplin.test1.SharedPrefsFile";

    protected final String mKey;
    protected final T mDefaultValue;

    BasePref(String key, T defaultValue) {
        mKey = key;
        mDefaultValue = defaultValue;
    }

    public final T get() {
        return getValueFromPrefs(getSharedPreferences());
    }

    public final void set(T value) {
        setValueToPrefs(value, getSharedPreferences());
    }

    public void reset() {
        set(mDefaultValue);
    }

    public boolean isDefault() {
        return get() == mDefaultValue;
    }

    public boolean exists() {
        return getSharedPreferences().contains(mKey);
    }

    public void delete() {
        getSharedPreferences().edit().remove(mKey).apply();
    }

    protected abstract T getValueFromPrefs(SharedPreferences prefs);

    protected abstract void setValueToPrefs(T value, SharedPreferences prefs);

    private SharedPreferences getSharedPreferences() {
        return PrefsContext.get().getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
    }

}

