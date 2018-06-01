package com.simxdeveloper.catalogmovie.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;
import com.simxdeveloper.catalogmovie.Apps;
import com.simxdeveloper.catalogmovie.data.local.MovieDatabaseHelper;

/**
 * User: simx Date: 01/06/18 0:27
 */
public class MoviesStackWidgetService extends RemoteViewsService{

  @Override
  public RemoteViewsFactory onGetViewFactory (Intent intent) {
    return new StackRemoteViewsFactory (getApplicationContext (),intent);
  }
}
