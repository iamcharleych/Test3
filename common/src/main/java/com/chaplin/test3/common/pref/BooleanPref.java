package com.chaplin.test3.common.pref;

import android.content.SharedPreferences;

public class BooleanPref extends BasePref<Boolean> {
    public BooleanPref(String key, Boolean defaultValue) {
        super(key, defaultValue);
    }

    @Override
    protected Boolean getValueFromPrefs(SharedPreferences prefs) {
        return prefs.getBoolean(mKey, mDefaultValue);
    }

    @Override
    protected void setValueToPrefs(Boolean value, SharedPreferences prefs) {
        prefs.edit().putBoolean(mKey, value).apply();
    }
}
