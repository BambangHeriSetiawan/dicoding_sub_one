package com.simxdeveloper.catalogmovie.ui.setting;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.simxdeveloper.catalogmovie.BuildConfig;
import com.simxdeveloper.catalogmovie.R;
import com.simxdeveloper.catalogmovie.helper.TimePickerDialogFragment;
import com.simxdeveloper.catalogmovie.preference.AlaramPreference;
import com.simxdeveloper.catalogmovie.preference.PrefKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SettingsActivity extends PreferenceActivity {
  private AppCompatDelegate mDelegate;
  private Preference pref_daily_remainder_time;
  private Preference pref_daily_update_time;
  private SwitchPreference pref_notifications_daily_update;
  private SwitchPreference pref_notifications_daily_remember;

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    getDelegate ().installViewFactory ();
    getDelegate ().onCreate (savedInstanceState);
    super.onCreate (savedInstanceState);
    setContentView (R.layout.setting_activity);
    setSupportActionBar (findViewById (R.id.toolbar));
    getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
    getSupportActionBar ().setHomeAsUpIndicator (getResources ().getDrawable (R.drawable.ic_arrow_back_white_24dp));
    addPreferencesFromResource (R.xml.pref_notification);


    /**
     * Change Langguage
     */
    Preference pref_change_language = (Preference) findPreference(getResources ().getString (R.string.pref_change_language));
    pref_change_language.setOnPreferenceClickListener(preference -> {
      changeSettingLanguage ();
      return true;
    });
    /**
     * Show version app
     */
    Preference pref_version = (Preference) findPreference(getResources ().getString (R.string.pref_version));
    pref_version.setTitle (BuildConfig.VERSION_NAME);

    /**
     * Daily Remainder
     */
    pref_notifications_daily_remember = (SwitchPreference) findPreference(getResources ().getString (R.string.pref_notifications_daily_remember));
    pref_notifications_daily_remember.setChecked (AlaramPreference.read (PrefKey.IS_ALARAM_AT_7,Boolean.class));
    pref_notifications_daily_remember.setOnPreferenceChangeListener (
        (preference, newValue) -> {
          updatePreferenceRemainder(((SwitchPreference)preference).isEnabled ());
          return true;
        });
    pref_daily_remainder_time = (Preference) findPreference(getResources ().getString (R.string.pref_notifications_daily_remember_time));
    pref_daily_remainder_time.setOnPreferenceClickListener (preference -> {
      TimePickerDialogFragment timePickerDialogFragment = new TimePickerDialogFragment (dailyRemainderListener);
      timePickerDialogFragment.show (getFragmentManager (),"remainder");
      return true;
    });
    /**
     * Daily Update Movie
     */
    pref_notifications_daily_update = (SwitchPreference) findPreference(getResources ().getString (R.string.pref_notifications_daily_update));
    pref_notifications_daily_update.setChecked (AlaramPreference.read (PrefKey.IS_ALARAM_AT_8,Boolean.class));
    pref_notifications_daily_update.setOnPreferenceChangeListener ((preference, newValue) -> {
      updateDailyUpdatePreference(((SwitchPreference)preference).isEnabled ());
      return true;
    });
    pref_daily_update_time = (Preference) findPreference(getResources ().getString (R.string.pref_notifications_daily_update_time));
    pref_daily_update_time.setOnPreferenceClickListener (preference -> {
      TimePickerDialogFragment timePickerDialogFragment = new TimePickerDialogFragment (dailyUpdateListener);
      timePickerDialogFragment.show (getFragmentManager (),"remainder");
      return false;
    });
    /**
     * Check time from preference
     */
    Log.e ("SettingsActivity", "onCreate: " + AlaramPreference.read (PrefKey.ALARAM_AT_7,String.class));
    if (AlaramPreference.read (PrefKey.ALARAM_AT_7,String.class).length ()!=0){
      pref_daily_remainder_time.setTitle (formateTime (new Long (AlaramPreference.read (PrefKey.ALARAM_AT_7,String.class))));
    }
    if (AlaramPreference.read (PrefKey.ALARAM_AT_8,String.class).length () !=0){
      pref_daily_update_time.setTitle (formateTime (new Long (AlaramPreference.read (PrefKey.ALARAM_AT_8,String.class))));
    }

  }

  private void updateDailyUpdatePreference (Boolean isChecked) {
    Log.e ("SettingsActivity", "updateDailyUpdatePreference: " + !isChecked);
    pref_notifications_daily_update.setChecked (!isChecked);
    AlaramPreference.write (PrefKey.IS_ALARAM_AT_8,!isChecked,Boolean.class);
  }

  private void updatePreferenceRemainder (Boolean isChecked) {
    Log.e ("SettingsActivity", "updatePreferenceRemainder: " + !isChecked);
    pref_notifications_daily_remember.setChecked (!isChecked);
    AlaramPreference.write (PrefKey.IS_ALARAM_AT_8,!isChecked,Boolean.class);
  }

  @Override
  protected void onPostCreate (Bundle savedInstanceState) {
    super.onPostCreate (savedInstanceState);
    getDelegate ().onPostCreate (savedInstanceState);
  }

  public ActionBar getSupportActionBar () {
    return getDelegate ().getSupportActionBar ();
  }

  public void setSupportActionBar (@Nullable Toolbar toolbar) {
    getDelegate ().setSupportActionBar (toolbar);
  }

  @Override
  public MenuInflater getMenuInflater () {
    return getDelegate ().getMenuInflater ();
  }

  @Override
  public void setContentView (@LayoutRes int layoutResID) {
    getDelegate ().setContentView (layoutResID);
  }

  @Override
  public void setContentView (View view) {
    getDelegate ().setContentView (view);
  }

  @Override
  public void setContentView (View view, ViewGroup.LayoutParams params) {
    getDelegate ().setContentView (view, params);
  }

  @Override
  public void addContentView (View view, ViewGroup.LayoutParams params) {
    getDelegate ().addContentView (view, params);
  }

  @Override
  protected void onPostResume () {
    super.onPostResume ();
    getDelegate ().onPostResume ();
  }

  @Override
  protected void onTitleChanged (CharSequence title, int color) {
    super.onTitleChanged (title, color);
    getDelegate ().setTitle (title);
  }

  @Override
  public void onConfigurationChanged (Configuration newConfig) {
    super.onConfigurationChanged (newConfig);
    getDelegate ().onConfigurationChanged (newConfig);
  }

  @Override
  protected void onStop () {
    super.onStop ();
    getDelegate ().onStop ();
  }

  @Override
  protected void onDestroy () {
    super.onDestroy ();
    getDelegate ().onDestroy ();
  }

  public void invalidateOptionsMenu () {
    getDelegate ().invalidateOptionsMenu ();
  }

  private AppCompatDelegate getDelegate () {
    if (mDelegate == null) {
      mDelegate = AppCompatDelegate.create (this, null);
    }
    return mDelegate;
  }

  @Override
  public boolean onOptionsItemSelected (MenuItem item) {
    switch (item.getItemId ()) {
      case android.R.id.home:
        onBackPressed ();
        break;
    }
    return super.onOptionsItemSelected (item);
  }

  private void changeSettingLanguage () {
    Intent mIntent = new Intent (Settings.ACTION_LOCALE_SETTINGS);
    startActivity(mIntent);
  }

  private TimePickerDialog.OnTimeSetListener dailyRemainderListener = (view, hourOfDay, minute) -> {
    long time_milis = getTimeFromMillis (hourOfDay,minute);
    AlaramPreference.write (PrefKey.ALARAM_AT_7,time_milis,Long.class);
    pref_daily_remainder_time.setTitle (formateTime (time_milis));
  };

  private TimePickerDialog.OnTimeSetListener dailyUpdateListener = (view, hourOfDay, minute) -> {
    long time_milis = getTimeFromMillis (hourOfDay,minute);
    AlaramPreference.write (PrefKey.ALARAM_AT_8,time_milis,Long.class);
    pref_daily_update_time.setTitle (formateTime (time_milis));
  };

  private String formateTime(long time){
    DateFormat sdfDate = new SimpleDateFormat("HH:mm");
    String strDate = sdfDate.format(time);
    return strDate;
  }

  private long getTimeFromMillis(int houre,  int minut){
    Calendar calendar = Calendar.getInstance ();
    calendar.set (Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_YEAR,houre,minut);
    return calendar.getTimeInMillis ();
  }
}
