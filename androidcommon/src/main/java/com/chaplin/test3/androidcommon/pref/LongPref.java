package com.chaplin.test3.androidcommon.pref;

import android.content.SharedPreferences;

public class LongPref extends BasePref<Long> {
    public LongPref(String key, Long defaultValue) {
        super(key, defaultValue);
    }

    @Override
    protected Long getValueFromPrefs(SharedPreferences prefs) {
        return prefs.getLong(mKey, mDefaultValue);
    }

    @Override
    protected void setValueToPrefs(Long value, SharedPreferences prefs) {
        prefs.edit().putLong(mKey, value).apply();
    }
}
