package com.katsuna.visual.sqlite;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by cmitatakis on 4/18/2017.
 */

public class VisualTestResultsContentProvider extends ContentProvider {

    private static VisualTestResultsHandler VisualTestResultsDb;

    // used for the UriMacher
    private static final int RESULTS = 40;


    private static final String AUTHORITY = "com.katsuna.services.sqlite.ResultsDB.VisualTestResultsContentProvider";

    private static final String RESULTS_PATH = "VisualTestResults";

    public static final Uri RESULTS_URI = Uri.parse("content://" + AUTHORITY
            + "/" + RESULTS_PATH);


    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/todos";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/todo";

    private static final UriMatcher sURIMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, RESULTS_PATH, RESULTS);

    }

    @Override
    public boolean onCreate() {
        VisualTestResultsDb = VisualTestResultsHandler.getLocationProvider(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Uisng SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // check if the caller has requested a column which does not exists
        checkColumns(projection);


        queryBuilder.setTables(VisualTestResultsTable.TABLE_Visual_Test_Results);
        //      }

        SQLiteDatabase db = VisualTestResultsDb.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);
        // make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = VisualTestResultsDb.getWritableDatabase();
        long id = 0;
        switch (uriType) {
            case RESULTS:
                id = sqlDB.insert(VisualTestResultsTable.TABLE_Visual_Test_Results, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(RESULTS_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = VisualTestResultsDb.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case RESULTS:
                rowsDeleted = sqlDB.delete(VisualTestResultsTable.TABLE_Visual_Test_Results, selection,
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = VisualTestResultsDb.getWritableDatabase();
        int rowsUpdated = 0;
        switch (uriType) {
            case RESULTS:
                rowsUpdated = sqlDB.update(VisualTestResultsTable.TABLE_Visual_Test_Results,
                        values,
                        selection,
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

    private void checkColumns(String[] projection) {
        String[] available = {VisualTestResultsTable.id,
                VisualTestResultsTable.value};
        if (projection != null) {
            HashSet<String> requestedColumns = new HashSet<String>(
                    Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<String>(
                    Arrays.asList(available));
            // check if all columns which are requested are available
            if (!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException(
                        "Unknown columns in projection");
            }
        }
    }


}
