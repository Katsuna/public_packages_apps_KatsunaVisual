package com.katsuna.visual.sqlite;

import android.content.ContentValues;
import android.content.Context;

import java.util.Date;

/**
 * Created by cmitatakis on 4/18/2017.
 */

public class VisualTestResultsDAO  {
    Context mContext;

    public VisualTestResultsDAO(Context context) {
        mContext = context;
    }

    public void insert(int id , String value){

        ContentValues values = new ContentValues();
        values.put(VisualTestResultsTable.id, id);
        values.put(VisualTestResultsTable.value, value);
        mContext.getContentResolver().insert(VisualTestResultsContentProvider.RESULTS_URI,values);
    }

    public void update(int id , String value){

        String mSelectionClause = VisualTestResultsTable.id +  " LIKE ?";


        ContentValues values = new ContentValues();
        values.put(VisualTestResultsTable.id, id);
        values.put(VisualTestResultsTable.value, value);

        String[] mSelectionArgs = {String.valueOf(id)};


        int mRowsUpdated = 0;

        mRowsUpdated = mContext.getContentResolver().update(
                VisualTestResultsContentProvider.RESULTS_URI,   // the user dictionary content URI
                values,                       // the columns to update
                mSelectionClause,                    // the column to select on
                mSelectionArgs                      // the value to compare to
        );
    }

}
