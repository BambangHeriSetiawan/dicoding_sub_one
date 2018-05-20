package com.simxdeveloper.catalogmovie;

import android.app.Application;
import android.content.Context;
import com.simxdeveloper.catalogmovie.preference.GlobalPreference;
import com.simxdeveloper.catalogmovie.preference.PrefKey;
import java.util.Locale;

/**
 * User: simx Date: 18/05/18 22:01
 */
public class Apps  extends Application{
  private static Context context;

  @Override
  public void onCreate () {
    super.onCreate ();
    context = getApplicationContext ();
    String region = Locale.getDefault ().getCountry ();
    String lang = Locale.getDefault ().getLanguage ();

    GlobalPreference.write (PrefKey.LANGUAGE,lang+"-"+region,String.class);
    GlobalPreference.write (PrefKey.REGION,region,String.class);
  }

  public static Context getContext () {
    return context;
  }
}

