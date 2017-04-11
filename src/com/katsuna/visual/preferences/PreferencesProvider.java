package com.katsuna.visual.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by cmitatakis on 4/11/2017.
 */

public class PreferencesProvider {

    private final static String KATSUNA_VISUAL_PREFS_NAME = "KatsunaVisualPrefs";


    public static Boolean FinishContrastTest(Activity context) {
        return getPreferences(context).getBoolean(SharedPreferencesConstants.FinishContrastTest, false);

    }

    public static void SetFinishContrastTest(Activity context, Boolean first) {
        getPreferences(context).edit().putBoolean(SharedPreferencesConstants.FinishContrastTest, first).commit();
    }

    public static Boolean FinishAcuityTest(Activity context) {
        return getPreferences(context).getBoolean(SharedPreferencesConstants.FinishAcuityTest, false);

    }

    public static void SetFinishAcuityTest(Activity context, Boolean first) {
        getPreferences(context).edit().putBoolean(SharedPreferencesConstants.FinishAcuityTest, first).commit();
    }


    private static SharedPreferences getPreferences(Activity context) {
        return context.getSharedPreferences(KATSUNA_VISUAL_PREFS_NAME, Context.MODE_PRIVATE);
    }
}
