package com.simxdeveloper.catalogmovie.data.local;

import android.database.Cursor;
import android.net.Uri;

/**
 * User: simx Date: 27/05/18 3:10
 */
public class DatabaseContract {
  // Authority yang digunakan
  public static final String AUTHORITY = "com.simxdeveloper.catalogmovie";
  // Base content yang digunakan untuk akses content provider
  public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
      .authority(AUTHORITY)
      .appendPath(TableConst.TABLE_NAME_FAV)
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
