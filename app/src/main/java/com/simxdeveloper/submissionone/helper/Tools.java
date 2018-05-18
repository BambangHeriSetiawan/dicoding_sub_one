package com.simxdeveloper.submissionone.helper;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.simxdeveloper.submissionone.Apps;

/**
 * User: simx Date: 18/05/18 22:44
 */
public class Tools {
  public static  boolean isOnline (){
    ConnectivityManager cm = (ConnectivityManager) Apps.getContext ().getSystemService (Apps.getContext ().CONNECTIVITY_SERVICE);
    NetworkInfo netInfo = cm.getActiveNetworkInfo ();
    return netInfo !=null && netInfo.isConnectedOrConnecting ();
  }
  public static boolean isValidInputSearch(CharSequence target){
    return !TextUtils.isEmpty (target) ? true:false;
  }

}
