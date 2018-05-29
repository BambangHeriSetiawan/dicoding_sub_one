package com.simxdeveloper.favorit.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * User: simx Date: 29/05/18 19:11
 */
public class DatabaseContract {
  // Authority yang digunakan
  public static final String AUTHORITY = "com.simxdeveloper.favorit";
  public static final String AUTHORITY_ = "com.simxdeveloper.catalogmovie";
  // Base content yang digunakan untuk akses content provider
  public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
      .authority(AUTHORITY_)
      .appendPath(Const.TABLE_NAME_FAV)
      .build();
  public static String getColumnString(Cursor cursor, String columnName) {
    return cursor.getString( cursor.getColumnIndex(columnName) );
  }
  public static int getColumnInt(Cursor cursor, String columnName) {
    return cursor.getInt( cursor.getColumnIndex(columnName) );
  }

  public static long getColumnLong(Cursor cursor, String columnName) {
    return cursor.getLong( cursor.getColumnIndex(columnName) );
  }
}
