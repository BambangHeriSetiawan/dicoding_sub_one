package com.simxdeveloper.catalogmovie;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.simxdeveloper.catalogmovie.preference.AlaramPreference;
import com.simxdeveloper.catalogmovie.preference.GlobalPreference;
import com.simxdeveloper.catalogmovie.preference.PrefKey;
import java.util.Calendar;
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
    Log.e ("Apps", "onCreate: " + AlaramPreference.read (PrefKey.ALARAM_AT_7,Long.class));
    Log.e ("Apps", "onCreate: " + AlaramPreference.read (PrefKey.ALARAM_AT_8,Long.class));
    if (AlaramPreference.read (PrefKey.ALARAM_AT_7,Long.class)==null){
      setDefaultTimeRemainder();
    }
    if (AlaramPreference.read (PrefKey.ALARAM_AT_8,Long.class)==null){
      setDefaultTimeUpdate();
    }

  }

  private void setDefaultTimeUpdate () {
    Log.e ("Apps", "setDefaultTimeUpdate: " );
    Calendar calendar = Calendar.getInstance ();
    calendar.setTimeInMillis (System.currentTimeMillis ());
    calendar.set (Calendar.HOUR_OF_DAY,8);
    calendar.set (Calendar.MINUTE,0);
    calendar.set (Calendar.SECOND,0);
    AlaramPreference.write (PrefKey.IS_ALARAM_AT_8,true,Boolean.class);
    AlaramPreference.write (PrefKey.ALARAM_AT_8,String.valueOf (calendar.getTimeInMillis ()),String.class);
  }

  private void setDefaultTimeRemainder () {
    Log.e ("Apps", "setDefaultTimeRemainder: " );
    Calendar calendar = Calendar.getInstance ();
    calendar.setTimeInMillis (System.currentTimeMillis ());
    calendar.set (Calendar.HOUR_OF_DAY,7);
    calendar.set (Calendar.MINUTE,0);
    calendar.set (Calendar.SECOND,0);
    AlaramPreference.write (PrefKey.ALARAM_AT_7,String.valueOf (calendar.getTimeInMillis ()),String.class);
    AlaramPreference.write (PrefKey.IS_ALARAM_AT_7,true,Boolean.class);
  }

  public static Context getContext () {
    return context;
  }
}

