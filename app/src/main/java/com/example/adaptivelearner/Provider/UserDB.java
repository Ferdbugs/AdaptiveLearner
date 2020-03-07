package com.example.adaptivelearner.Provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.example.adaptivelearner.Learner;
import com.example.adaptivelearner.Provider.ContractClass.UserTable;

import java.util.ArrayList;
import java.util.List;

public class UserDB extends SQLiteOpenHelper {

    public static final String TAG = UserDB.class.getSimpleName() + "_DEBUG";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "user.db";

    private static UserDB db;


    public static UserDB getInstance(Context context) {
        if (db == null) db = new UserDB(context);
        return db;
    }

    private UserDB(@NonNull Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }







    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_SQL = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s TEXT," +
                        "%s FLOAT)",
                UserTable.TABLE_NAME,
                UserTable.COLUMN_ID,
                UserTable.COLUMN_TOPIC,
                UserTable.COLUMN_DIFFICULTY,
                UserTable.COLUMN_PERFORMANCE,
                UserTable.COLUMN_LEARNER_STATE,
                UserTable.COLUMN_DATE

        );

        db.execSQL(CREATE_TABLE_SQL);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserTable.TABLE_NAME);
        onCreate(db);
    }

    public void insertLearner(Learner learner) {
        ContentValues values = new ContentValues();

        values.put(UserTable.COLUMN_TOPIC, learner.getCurrentTopic());
        values.put(UserTable.COLUMN_DIFFICULTY, learner.getCurrentDifficulty());
        values.put(UserTable.COLUMN_PERFORMANCE, learner.getPerformance());
        values.put(UserTable.COLUMN_LEARNER_STATE, learner.getLearnerState());
        values.put(UserTable.COLUMN_DATE,learner.getDate());

        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        db.insert(UserTable.TABLE_NAME, null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public void updateLearner(Learner learner) {
        ContentValues values = new ContentValues();

        values.put(UserTable.COLUMN_TOPIC, learner.getCurrentTopic());
        values.put(UserTable.COLUMN_DIFFICULTY, learner.getCurrentDifficulty());
        values.put(UserTable.COLUMN_PERFORMANCE, learner.getPerformance());
        values.put(UserTable.COLUMN_LEARNER_STATE, learner.getLearnerState());
        values.put(UserTable.COLUMN_DATE,learner.getDate());

        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        db.update(
                UserTable.TABLE_NAME,
                values,
                UserTable.COLUMN_ID + " = ?",
                new String[] { String.valueOf(0) }
        );
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public Learner getLatest() {
        SQLiteDatabase db = getReadableDatabase();
        Learner learner = new Learner();

        try (
                Cursor cursor = db.query(
                        UserTable.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        UserTable.COLUMN_DATE + " DESC"
                )
        ) {

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    if(cursor.moveToLast()) {
                        learner.setId(cursor.getInt(cursor.getColumnIndex(UserTable.COLUMN_ID)));
                        learner.setCurrentTopic(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_TOPIC)));
                        learner.setCurrentDifficulty(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_DIFFICULTY)));
                        learner.setPerformance(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_PERFORMANCE)));
                        learner.setLearnerState(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_LEARNER_STATE)));
                        learner.setDate(cursor.getLong(cursor.getColumnIndex(UserTable.COLUMN_DATE)));
                    }
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return learner;
    }

    public Learner getLearner(int id) {

        SQLiteDatabase db = getReadableDatabase();
        Learner learner = null;

        try (
                Cursor cursor = db.query(
                        UserTable.TABLE_NAME,
                        null,
                        UserTable.COLUMN_ID + " = ?",
                        new String[]{String.valueOf(id)},
                        null,
                        null,
                        UserTable.COLUMN_DATE+ " DESC"
                )
        ) {

            if (cursor != null && cursor.moveToFirst()) {
                learner = new Learner();

                learner.setId(cursor.getInt(cursor.getColumnIndex(UserTable.COLUMN_ID)));
                learner.setCurrentTopic(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_TOPIC)));
                learner.setCurrentDifficulty(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_DIFFICULTY)));
                learner.setPerformance(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_PERFORMANCE)));
                learner.setLearnerState(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_LEARNER_STATE)));
                learner.setDate(cursor.getLong(cursor.getColumnIndex(UserTable.COLUMN_DATE)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return learner;
    }

    public Learner getLatestByTopic(String topic) {

        SQLiteDatabase db = getReadableDatabase();
        Learner learner = new Learner();

        try (
                Cursor cursor = db.query(
                        UserTable.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        UserTable.COLUMN_DATE + " DESC"
                )
        ) {

            if (cursor != null && cursor.moveToFirst()) {
                do {
                        learner.setId(cursor.getInt(cursor.getColumnIndex(UserTable.COLUMN_ID)));
                        if(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_TOPIC)).equals(topic)){
                            learner.setCurrentTopic(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_TOPIC)));
                            learner.setCurrentDifficulty(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_DIFFICULTY)));
                            learner.setPerformance(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_PERFORMANCE)));
                            learner.setLearnerState(cursor.getString(cursor.getColumnIndex(UserTable.COLUMN_LEARNER_STATE)));
                        }
                        learner.setDate(cursor.getLong(cursor.getColumnIndex(UserTable.COLUMN_DATE)));
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return learner;
    }

}
