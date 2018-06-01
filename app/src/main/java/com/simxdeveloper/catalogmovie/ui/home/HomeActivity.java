package com.simxdeveloper.catalogmovie.ui.home;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.simxdeveloper.catalogmovie.R;
import com.simxdeveloper.catalogmovie.broadcast.ReciverScheduleSeven;
import com.simxdeveloper.catalogmovie.preference.GlobalPreference;
import com.simxdeveloper.catalogmovie.preference.PrefKey;
import com.simxdeveloper.catalogmovie.ui.home.adapter.HomePageAdapter;

import com.simxdeveloper.catalogmovie.ui.setting.SettingsActivity;
import java.util.Calendar;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity
    implements OnNavigationItemSelectedListener {

  @BindView(R.id.toolbar)
  Toolbar toolbar;
  @BindView(R.id.nav_view)
  NavigationView nav;
  @BindView(R.id.drawer_layout)
  DrawerLayout drawer;
  ActionBarDrawerToggle toggle;
  @BindView(R.id.home_view)
  ViewPager homeView;
  @BindView(R.id.tabs)
  TabLayout tabs;

  private HomePageAdapter pageAdapter;

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_home);
    ButterKnife.bind (this);
    setSupportActionBar (toolbar);
    pageAdapter = new HomePageAdapter (getSupportFragmentManager ());
    homeView.setAdapter (pageAdapter);
    tabs.setupWithViewPager (homeView);
    toggle = new ActionBarDrawerToggle (
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener (toggle);
    toggle.syncState ();
    nav.setNavigationItemSelectedListener (this);

  }



  @Override
  public void onBackPressed () {
    if (drawer.isDrawerOpen (GravityCompat.START)) {
      drawer.closeDrawer (GravityCompat.START);
    } else {
      super.onBackPressed ();
    }
  }

  @Override
  public boolean onCreateOptionsMenu (Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater ().inflate (R.menu.home, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected (MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId ();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      startActivity (new Intent (this, SettingsActivity.class));
      return true;
    }

    return super.onOptionsItemSelected (item);
  }


  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected (MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId ();
    switch (id) {
      case R.id.nav_setting:
        startActivity (new Intent (this, SettingsActivity.class));
        break;
      case R.id.nav_now_playing:
        homeView.setCurrentItem (0);
        break;
      case R.id.nav_upcoming:
        homeView.setCurrentItem (1);
        break;
      case R.id.nav_search:
        homeView.setCurrentItem (2);
        break;
      case R.id.nav_show_fav:
        homeView.setCurrentItem (3);
        break;

    }
    drawer.closeDrawer (GravityCompat.START);
    return false;
  }

  @Override
  protected void onResume () {
    super.onResume ();
    String region = Locale.getDefault ().getCountry ();
    String lang = Locale.getDefault ().getLanguage ();

    GlobalPreference.write (PrefKey.LANGUAGE,lang+"-"+region,String.class);
    GlobalPreference.write (PrefKey.REGION,region,String.class);
  }
  private void setTimeSevent () {
    Calendar calendar = Calendar.getInstance ();
    calendar.set(Calendar.HOUR_OF_DAY, 21);
    calendar.set(Calendar.MINUTE, 19);
    calendar.set(Calendar.SECOND, 0);
    Log.e ("HomeActivity", "setTimeSevent: " + calendar.getTime ());
    Intent notificationIntent = new Intent(this, ReciverScheduleSeven.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
    alarmManager.setRepeating (AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis (),AlarmManager.INTERVAL_DAY,pendingIntent);
  }
}
