package com.simxdeveloper.catalogmovie.widget;

import android.appwidget.AppWidgetManager;
import android.arch.persistence.room.ColumnInfo;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.simxdeveloper.catalogmovie.R;
import com.simxdeveloper.catalogmovie.data.local.DatabaseContract;
import com.simxdeveloper.catalogmovie.data.local.Movies;
import com.simxdeveloper.catalogmovie.data.local.TableConst;
import com.simxdeveloper.catalogmovie.helper.Const;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * User: simx Date: 01/06/18 0:20
 */
class StackRemoteViewsFactory implements RemoteViewsFactory {
  private static final int LOADER_MOVIES = 1;
  private List<Bitmap> mWidgetItems = new ArrayList<> ();
  private Context mContext;
  private int mAppWidgetId;
  Movies movies;
  Uri uri;
  Cursor cursor;
  public StackRemoteViewsFactory (Context mContext, Intent intent) {
    this.mContext = mContext;
    this.uri = DatabaseContract.CONTENT_URI;
    mAppWidgetId = intent.getIntExtra (AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
  }


  @Override
  public void onCreate () {
    Log.e ("StackRemoteViewsFactory", "onCreate: " );
  }

  @Override
  public void onDataSetChanged () {
    Log.e ("StackRemoteViewsFactory", "onDataSetChanged: " );
    final long identityToken = Binder.clearCallingIdentity();
    if (uri != null) {
      cursor = mContext.getContentResolver().query(uri, null, null, null, null);
      if (cursor != null){
        //if(cursor.moveToFirst()) movies = new Movies(cursor);
        for (int i = 0; i < cursor.getCount (); i++) {
          movies = getItem (i);
          Bitmap bitmap = createBitmap (movies.getPosterPath ());
          mWidgetItems.add (bitmap);
        }
        cursor.close();
      }
      /*for (int i = 0; i < cursor.getCount (); i++) {
        movies = getItem (i);
        Log.e ("StackRemoteViewsFactory", "onDataSetChanged: " + movies.toString ());
      }*/
    }
    Binder.restoreCallingIdentity(identityToken);
  }

  @Override
  public void onDestroy () {
    Log.e ("StackRemoteViewsFactory", "onDestroy: " );
  }

  @Override
  public int getCount () {
    return mWidgetItems.size ();
  }

  @Override
  public RemoteViews getViewAt (int position) {
    Log.e ("StackRemoteViewsFactory", "getViewAt: " );
    RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_favorite_item);
    rv.setImageViewBitmap(R.id.imageView, mWidgetItems.get (position));
    Bundle extras = new Bundle();
    extras.putInt(MovieFavoriteWidget.EXTRA_ITEM, position);
    Intent fillInIntent = new Intent();
    fillInIntent.putExtras(extras);

    rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
    return rv;
  }

  @Override
  public RemoteViews getLoadingView () {
    return null;
  }

  @Override
  public int getViewTypeCount () {
    return 1;
  }

  @Override
  public long getItemId (int position) {
    return 0;
  }

  private Movies getItem (int pos) {
    if (!cursor.moveToPosition (pos)){
      throw new IllegalStateException ("Possition Invalid");
    }
    return new Movies (cursor);
  }

  @Override
  public boolean hasStableIds () {
    return false;
  }

  private Bitmap createBitmap(String path){
    Bitmap bmp = null;
    try {
      bmp = Glide.with(mContext)
          .asBitmap ()
          .load(Const.IMAGE_PATH + path)
          .submit (Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();

    }catch (InterruptedException | ExecutionException e){
      Log.d("Widget Load Error","error");
    }
    return bmp;
  }
  public void updateAdapter (Cursor cursor) {
    this.cursor = cursor;
  }
}
