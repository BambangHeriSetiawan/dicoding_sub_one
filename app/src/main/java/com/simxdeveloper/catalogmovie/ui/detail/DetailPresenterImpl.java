package com.simxdeveloper.catalogmovie.ui.detail;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.simxdeveloper.catalogmovie.data.local.Movies;

/**
 * User: simx Date: 20/05/18 14:41
 */
public class DetailPresenterImpl {
  private DetailPresenter presenter;
  private Context context;
  public DetailPresenterImpl (DetailPresenter presenter, Context context) {
    this.presenter = presenter;
    this.context = context;
  }


  public void cursorCheck (Uri uri) {
    if (uri != null){
      Cursor cursor = context.getContentResolver ().query (uri,null,null,null,null);
      if (cursor!=null && cursor.moveToFirst ()){
        Movies movies = new Movies (cursor);
        presenter.initFromCursor(movies);
        cursor.close ();
      }
    }
  }
}
