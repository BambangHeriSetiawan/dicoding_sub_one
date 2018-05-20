package com.simxdeveloper.catalogmovie.helper;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.simxdeveloper.catalogmovie.Apps;

/**
 * User: simx Date: 18/05/18 22:44
 */
public class Tools {

  /**
   * Chek aviable network
   * @return
   */
  public static  boolean isOnline (){
    ConnectivityManager cm = (ConnectivityManager) Apps.getContext ().getSystemService (Apps.getContext ().CONNECTIVITY_SERVICE);
    NetworkInfo netInfo = cm.getActiveNetworkInfo ();
    return netInfo !=null && netInfo.isConnectedOrConnecting ();
  }

  /**
   * Validator for input search
   * return true if param is not empety
   * return false if param is empety
   * @param target
   * @return
   */
  public static boolean isValidInputSearch(CharSequence target){
    return !TextUtils.isEmpty (target) ? true:false;
  }

}
