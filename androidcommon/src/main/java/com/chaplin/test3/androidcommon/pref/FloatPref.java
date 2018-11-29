package com.chaplin.test3.androidcommon.pref;

import android.content.SharedPreferences;

public class FloatPref extends BasePref<Float> {
    public FloatPref(String key, Float defaultValue) {
        super(key, defaultValue);
    }

    @Override
    protected Float getValueFromPrefs(SharedPreferences prefs) {
        return prefs.getFloat(mKey, mDefaultValue);
    }

    @Override
    protected void setValueToPrefs(Float value, SharedPreferences prefs) {
        prefs.edit().putFloat(mKey, value).apply();
    }
}
