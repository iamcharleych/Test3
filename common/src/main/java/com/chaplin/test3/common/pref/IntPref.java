package com.chaplin.test3.common.pref;

import android.content.SharedPreferences;

public class IntPref extends BasePref<Integer> {
    public IntPref(String key, Integer defaultValue) {
        super(key, defaultValue);
    }

    @Override
    protected Integer getValueFromPrefs(SharedPreferences prefs) {
        return prefs.getInt(mKey, mDefaultValue);
    }

    @Override
    protected void setValueToPrefs(Integer value, SharedPreferences prefs) {
        prefs.edit().putInt(mKey, value).apply();
    }
}
