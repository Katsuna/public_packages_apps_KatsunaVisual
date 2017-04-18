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
