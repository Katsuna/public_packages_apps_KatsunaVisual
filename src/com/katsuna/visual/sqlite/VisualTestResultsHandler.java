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
package com.katsuna.visual.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cmitatakis on 4/18/2017.
 */

public class VisualTestResultsHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "ResultsDB";
    private static VisualTestResultsHandler handler = null;


    public static synchronized VisualTestResultsHandler getLocationProvider(Context context)
    {
        if (handler == null)
            handler = new VisualTestResultsHandler(context);
        return handler;
    }

    private VisualTestResultsHandler(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        VisualTestResultsTable.onCreate(db);

    }


    // Upgrading database


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        VisualTestResultsTable.onUpgrade(db, oldVersion, newVersion);

    }
}
