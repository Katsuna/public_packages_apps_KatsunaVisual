/**
* Copyright (C) 2020 Manos Saratsis
*
* This file is part of Katsuna.
*
* Katsuna is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Katsuna is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Katsuna.  If not, see <https://www.gnu.org/licenses/>.
*/
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
