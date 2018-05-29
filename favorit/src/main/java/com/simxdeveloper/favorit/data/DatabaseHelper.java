package com.simxdeveloper.favorit.data;

import static com.simxdeveloper.favorit.data.Const.COLUMN_BACKDROP_PATH;
import static com.simxdeveloper.favorit.data.Const.COLUMN_ID;
import static com.simxdeveloper.favorit.data.Const.COLUMN_ORIGINAL_LANGUAGE;
import static com.simxdeveloper.favorit.data.Const.COLUMN_ORIGINAL_TITLE;
import static com.simxdeveloper.favorit.data.Const.COLUMN_OVERVIEW;
import static com.simxdeveloper.favorit.data.Const.COLUMN_POPULARITY;
import static com.simxdeveloper.favorit.data.Const.COLUMN_POSTER_PATH;
import static com.simxdeveloper.favorit.data.Const.COLUMN_RELEASE_DATE;
import static com.simxdeveloper.favorit.data.Const.COLUMN_TITLE;
import static com.simxdeveloper.favorit.data.Const.COLUMN_VOTE_AVARAGE;
import static com.simxdeveloper.favorit.data.Const.COLUMN_VOTE_COUNT;
import static com.simxdeveloper.favorit.data.Const.TABLE_NAME_FAV;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * User: simx Date: 29/05/18 19:13
 */
public class DatabaseHelper extends SQLiteOpenHelper {

  public DatabaseHelper (Context context) {
    super (context, Const.DATABASE_NAME, null, Const.DATABASE_VERSION);
  }
  private static final String SQL_CREATE_TABLE_NOTE = String.format("CREATE TABLE %s"
          + " (%s INTEGER PRIMARY KEY ," +
          " %s TEXT NOT NULL," +
          " %s TEXT NOT NULL," +
          " %s TEXT NOT NULL," +
          " %s TEXT NOT NULL," +
          " %s TEXT NOT NULL," +
          " %s TEXT NOT NULL," +
          " %s TEXT NOT NULL," +
          " %s DOUBLE NOT NULL," +
          " %s DOUBLE NOT NULL," +
          " %s INTEGER NOT NULL)",
      TABLE_NAME_FAV,
      COLUMN_ID,
      COLUMN_OVERVIEW,
      COLUMN_ORIGINAL_LANGUAGE,
      COLUMN_ORIGINAL_TITLE,
      COLUMN_TITLE,
      COLUMN_POSTER_PATH,
      COLUMN_BACKDROP_PATH,
      COLUMN_RELEASE_DATE,
      COLUMN_VOTE_AVARAGE,//double
      COLUMN_POPULARITY,//double
      COLUMN_VOTE_COUNT // int
  );

  @Override
  public void onCreate (SQLiteDatabase db) {
    db.execSQL (SQL_CREATE_TABLE_NOTE);
  }

  @Override
  public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_FAV);
    onCreate(db);
  }
}
