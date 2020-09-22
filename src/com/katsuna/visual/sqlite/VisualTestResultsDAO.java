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
