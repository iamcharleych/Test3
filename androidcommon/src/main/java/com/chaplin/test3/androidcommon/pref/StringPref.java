package com.chaplin.test3.androidcommon.pref;

import android.content.SharedPreferences;

public class StringPref extends BasePref<String> {

    public StringPref(String key, String defaultValue) {
        super(key, defaultValue);
    }

    @Override
    protected String getValueFromPrefs(SharedPreferences prefs) {
        return prefs.getString(mKey, mDefaultValue);
    }

    @Override
    protected void setValueToPrefs(String value, SharedPreferences prefs) {
        prefs.edit().putString(mKey, value).apply();
    }
}
