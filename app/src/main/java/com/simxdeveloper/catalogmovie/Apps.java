package com.simxdeveloper.catalogmovie;

import android.app.Application;
import android.content.Context;

/**
 * User: simx Date: 18/05/18 22:01
 */
public class Apps  extends Application{
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

