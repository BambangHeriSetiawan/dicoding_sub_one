package com.simxdeveloper.catalogmovie.data.local;

import android.net.Uri;

/**
 * User: simx Date: 26/05/18 18:19
 */
public class TableConst {

  public static final String DATABASE_NAME = "movie_db";
  public static final int DATABASE_VERSION = 1;
  public static final String TABLE_NAME_FAV = "movie_favorit";

  // Collum
  public static final String COLUMN_OVERVIEW = "overview";
  public static final String COLUMN_ORIGINAL_LANGUAGE = "original_language";
  public static final String COLUMN_ORIGINAL_TITLE = "original_title";
  public static final String COLUMN_VIDEO = "video";
  public static final String COLUMN_TITLE = "title";
  public static final String COLUMN_POSTER_PATH = "poster_path";
  public static final String COLUMN_BACKDROP_PATH = "backdrop_path";
  public static final String COLUMN_RELEASE_DATE = "release_date";
  public static final String COLUMN_VOTE_AVARAGE = "vote_average";
  public static final String COLUMN_POPULARITY = "popularity";
  public static final String COLUMN_ADULTH = "adult";
  public static final String COLUMN_VOTE_COUNT = "vote_count";
  public static final String COLUMN_ID = "id";

  public static final String AUTHORITY = "com.simxdeveloper.catalogmovie";

  // Base content yang digunakan untuk akses content provider
  public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
      .authority(AUTHORITY)
      .appendPath(TABLE_NAME_FAV)
      .build();

}
