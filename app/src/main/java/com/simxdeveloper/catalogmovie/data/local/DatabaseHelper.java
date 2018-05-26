package com.simxdeveloper.catalogmovie.data.local;

import static android.provider.BaseColumns._ID;
import static com.simxdeveloper.catalogmovie.data.local.TableConst.COLUMN_ADULTH;
import static com.simxdeveloper.catalogmovie.data.local.TableConst.COLUMN_BACKDROP_PATH;
import static com.simxdeveloper.catalogmovie.data.local.TableConst.COLUMN_ID;
import static com.simxdeveloper.catalogmovie.data.local.TableConst.COLUMN_ORIGINAL_LANGUAGE;
import static com.simxdeveloper.catalogmovie.data.local.TableConst.COLUMN_ORIGINAL_TITLE;
import static com.simxdeveloper.catalogmovie.data.local.TableConst.COLUMN_OVERVIEW;
import static com.simxdeveloper.catalogmovie.data.local.TableConst.COLUMN_POPULARITY;
import static com.simxdeveloper.catalogmovie.data.local.TableConst.COLUMN_POSTER_PATH;
import static com.simxdeveloper.catalogmovie.data.local.TableConst.COLUMN_RELEASE_DATE;
import static com.simxdeveloper.catalogmovie.data.local.TableConst.COLUMN_TITLE;
import static com.simxdeveloper.catalogmovie.data.local.TableConst.COLUMN_VOTE_AVARAGE;
import static com.simxdeveloper.catalogmovie.data.local.TableConst.COLUMN_VOTE_COUNT;
import static com.simxdeveloper.catalogmovie.data.local.TableConst.TABLE_NAME_FAV;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * User: simx Date: 27/05/18 3:22
 */
public class DatabaseHelper extends SQLiteOpenHelper {

  public DatabaseHelper (Context context) {
    super (context, TableConst.DATABASE_NAME, null, 1);
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
