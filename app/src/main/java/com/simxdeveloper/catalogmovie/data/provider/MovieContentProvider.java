package com.simxdeveloper.catalogmovie.data.provider;

import static com.simxdeveloper.catalogmovie.data.local.TableConst.CONTENT_URI;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.simxdeveloper.catalogmovie.data.local.DatabaseContract;
import com.simxdeveloper.catalogmovie.data.local.MovieDatabaseHelper;
import com.simxdeveloper.catalogmovie.data.local.TableConst;

/**
 * User: simx Date: 27/05/18 1:33
 */
public class MovieContentProvider extends ContentProvider {

  private static final int MOVIE = 1;
  private static final int MOVIE_ID = 2;

  private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


  static {
    sUriMatcher.addURI(DatabaseContract.AUTHORITY, TableConst.TABLE_NAME_FAV, MOVIE);
    sUriMatcher.addURI(DatabaseContract.AUTHORITY, TableConst.TABLE_NAME_FAV + "/#", MOVIE_ID);
  }
  private MovieDatabaseHelper movieDatabaseHelper;
  @Override
  public boolean onCreate () {
    movieDatabaseHelper = new MovieDatabaseHelper (getContext ());
    movieDatabaseHelper.open ();
    return true;
  }

  @Nullable
  @Override
  public Cursor query (@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
      @Nullable String[] selectionArgs, @Nullable String sortOrder) {
    Cursor cursor;
    switch(sUriMatcher.match(uri)){
      case MOVIE:
        cursor = movieDatabaseHelper.queryProvider();
        break;
      case MOVIE_ID:
        cursor = movieDatabaseHelper.queryByIdProvider(uri.getLastPathSegment());
        break;
      default:
        cursor = null;
        break;
    }

    if (cursor != null){
      cursor.setNotificationUri(getContext().getContentResolver(),uri);
    }

    return cursor;
  }

  @Nullable
  @Override
  public String getType (@NonNull Uri uri) {
    return null;
  }

  @Nullable
  @Override
  public Uri insert (@NonNull Uri uri, @Nullable ContentValues values) {
    long added ;
    switch (sUriMatcher.match(uri)){
      case MOVIE:
        added = movieDatabaseHelper.insertProvider(values);
        break;
      default:
        added = 0;
        break;
    }

    if (added > 0) {
      getContext().getContentResolver().notifyChange(uri, null);
    }
    return Uri.parse(CONTENT_URI + "/" + added);
  }

  @Override
  public int delete (@NonNull Uri uri, @Nullable String selection,
      @Nullable String[] selectionArgs) {
    int deleted;
    switch (sUriMatcher.match(uri)) {
      case MOVIE_ID:
        deleted =  movieDatabaseHelper.deleteProvider(uri.getLastPathSegment());
        break;
      default:
        deleted = 0;
        break;
    }

    if (deleted > 0) {
      getContext().getContentResolver().notifyChange(uri, null);
    }

    return deleted;
  }

  @Override
  public int update (@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection,
      @Nullable String[] selectionArgs) {
    int updated ;
    switch (sUriMatcher.match(uri)) {
      case MOVIE_ID:
        updated =  movieDatabaseHelper.updateProvider(uri.getLastPathSegment(),values);
        break;
      default:
        updated = 0;
        break;
    }

    if (updated > 0) {
      getContext().getContentResolver().notifyChange(uri, null);
    }
    return updated;
  }
}
