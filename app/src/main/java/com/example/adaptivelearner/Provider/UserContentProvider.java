package com.example.adaptivelearner.Provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import com.example.adaptivelearner.Provider.ContractClass.UserTable;

import java.util.Objects;

import androidx.annotation.NonNull;

public class UserContentProvider extends ContentProvider {

    private static final String TAG = UserContentProvider.class.getSimpleName() + "_DEBUG";

    private static final int URI_USER = 0;
    private static final int URI_USER_ID = 1;

    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        matcher.addURI(ContractClass.AUTHORITY, UserTable.TABLE_NAME, URI_USER);
        matcher.addURI(ContractClass.AUTHORITY, UserTable.TABLE_NAME + "/#", URI_USER_ID);
    }

    public UserContentProvider() {

    }

    private UserDB dbHandler;


    @Override
    public boolean onCreate() {
        dbHandler = UserDB.getInstance(getContext());
        return true;
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        int uriType = matcher.match(uri);
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        db.beginTransaction();

        int id = 0;
        try {
            if (uriType == URI_USER) {
                id = (int) db.insertOrThrow(UserTable.TABLE_NAME, null, values);
                db.setTransactionSuccessful();
            } else {
                throw new IllegalArgumentException("Unknown URI: " + uri);
            }
        } catch (Exception e){
            Log.d(TAG, "Error inserting workout: " + e.getMessage());
        } finally {
            db.endTransaction();
            db.close();
        }

        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        return Uri.parse(UserTable.TABLE_NAME + "/" + id);
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int uriType = matcher.match(uri);
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(UserTable.TABLE_NAME);

        switch (uriType) {
            case URI_USER:
                break;
            case URI_USER_ID:
                queryBuilder.appendWhere(UserTable.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        Cursor cursor = queryBuilder.query(
                dbHandler.getReadableDatabase(),
                projection,
                selection,
                selectionArgs, null, null,
                sortOrder
        );

        cursor.setNotificationUri(Objects.requireNonNull(getContext()).getContentResolver(), uri);

        return cursor;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        int uriType = matcher.match(uri);
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        db.beginTransaction();

        int rowsDeleted = 0;
        try {
            switch (uriType) {
                case URI_USER:
                    rowsDeleted = db.delete(UserTable.TABLE_NAME, selection, selectionArgs);
                    db.setTransactionSuccessful();
                    break;
                case URI_USER_ID:
                    String id = uri.getLastPathSegment();
                    if (selection == null || selection.length() == 0)
                        rowsDeleted = db.delete(
                                UserTable.TABLE_NAME,
                                UserTable.COLUMN_ID + "=" + id,
                                null
                        );
                    else
                        rowsDeleted = db.delete(
                                UserTable.TABLE_NAME,
                                UserTable.COLUMN_ID + "=" + id + " and " + selection,
                                selectionArgs
                        );
                    db.setTransactionSuccessful();
                    break;
                default:
                    throw new IllegalArgumentException("Unknown URI: " + uri);
            }
        } catch (Exception e) {
            Log.d(TAG, "Error deleting workout: " + e.getMessage());
        } finally {
            db.endTransaction();
            db.close();
        }

        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int uriType = matcher.match(uri);
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        db.beginTransaction();

        int rowsUpdated = 0;
        try {
            switch (uriType) {
                case URI_USER:
                    db.update(UserTable.TABLE_NAME, values, selection, selectionArgs);
                    db.setTransactionSuccessful();
                    break;
                case URI_USER_ID:
                    String id = uri.getLastPathSegment();
                    if (selection == null || selection.length() == 0)
                        db.update(UserTable.TABLE_NAME, values,
                                UserTable.COLUMN_ID + "=" + id,
                                null);
                    else
                        db.update(UserTable.TABLE_NAME, values,
                                UserTable.COLUMN_ID + "=" + id + " and " + selection,
                                selectionArgs);
                    db.setTransactionSuccessful();
                    break;
                default:
                    throw new IllegalArgumentException("Unknown URI: " + uri);
            }
        } catch (Exception e) {
            Log.d(TAG, "Error updating workout: " + e.getMessage());
        } finally {
            db.endTransaction();
            db.close();
        }

        return rowsUpdated;
    }

    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}