package com.sipox11.android_dagger2_demo.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sipox11.android_dagger2_demo.config.qualifiers.AppContext;
import com.sipox11.android_dagger2_demo.config.qualifiers.DatabaseInfo;
import com.sipox11.android_dagger2_demo.config.scopes.AppScope;
import com.sipox11.android_dagger2_demo.data.models.User;

import javax.inject.Inject;

@AppScope
public class DBHelper extends SQLiteOpenHelper {

    //region User table
    public static final String USER_TABLE_NAME = "users";
    public static final String USER_COLUMN_USER_ID = "id";
    public static final String USER_COLUMN_USER_NAME = "usr_name";
    public static final String USER_COLUMN_USER_ADDRESS = "usr_add";
    public static final String USER_COLUMN_USER_CREATED_AT = "created_at";
    public static final String USER_COLUMN_USER_UPDATED_AT = "updated_at";

    //endregion

    @Inject
    public DBHelper(@AppContext Context context,
                    @DatabaseInfo String dbName,
                    @DatabaseInfo int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        tableCreateStatements(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(db);
    }

    private void tableCreateStatements(SQLiteDatabase db) {
        try {
            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS "
                            + USER_TABLE_NAME + "("
                            + USER_COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                            + USER_COLUMN_USER_NAME + " VARCHAR(20), "
                            + USER_COLUMN_USER_ADDRESS + " VARCHAR(50), "
                            + USER_COLUMN_USER_CREATED_AT + " VARCHAR(10) DEFAULT " + getCurrentTimeStamp() + ", "
                            + USER_COLUMN_USER_UPDATED_AT + " VARCHAR(10) DEFAULT " + getCurrentTimeStamp() + ")"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected User getUser(Long userId) throws Resources.NotFoundException, NullPointerException {
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(
                    "SELECT * FROM "
                            + USER_TABLE_NAME
                            + " WHERE "
                            + USER_COLUMN_USER_ID
                            + " = ? ",
                    new String[]{userId + ""});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                User user = new User();
                user.setId(cursor.getLong(cursor.getColumnIndex(USER_COLUMN_USER_ID)));
                user.setName(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_NAME)));
                user.setAddress(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_ADDRESS)));
                user.setCreatedAt(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_CREATED_AT)));
                user.setUpdatedAt(cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_UPDATED_AT)));
                return user;
            } else {
                throw new Resources.NotFoundException("User with id " + userId + " does not exists");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (cursor != null)
                cursor.close();
        }
    }

    protected Long insertUser(User user) throws Exception {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(USER_COLUMN_USER_NAME, user.getName());
            contentValues.put(USER_COLUMN_USER_ADDRESS, user.getAddress());
            return db.insert(USER_TABLE_NAME, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String getCurrentTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
}
