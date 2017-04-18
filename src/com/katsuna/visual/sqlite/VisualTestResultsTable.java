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
