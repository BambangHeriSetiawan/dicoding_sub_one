package com.simxdeveloper.favorit;

import android.app.Application;
import android.content.Context;

/**
 * User: simx Date: 29/05/18 18:40
 */
public class FavoriteApps extends Application {
  private static Context context;
  @Override
  public void onCreate () {
    super.onCreate ();
    context = getApplicationContext ();
  }

  public static Context getContext () {
    return context;
  }
}
