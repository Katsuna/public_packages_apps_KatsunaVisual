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

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by cmitatakis on 4/18/2017.
 */

public class VisualTestResultsTable {


    public static final String TABLE_Visual_Test_Results = "VisualTestResults";

    public static final String id = "id";
    public static final String value = "value";

    private static final String DATABASE_CREATE = "create table "
            + TABLE_Visual_Test_Results
            + "("
            + id + " INTEGER PRIMARY KEY ,"
            + value + " TEXT" +")";

    public static void onCreate(SQLiteDatabase database) {

        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(VisualTestResultsTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_Visual_Test_Results);
        onCreate(database);
    }


}
