package com.simxdeveloper.catalogmovie.ui.home.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.simxdeveloper.catalogmovie.Apps;
import com.simxdeveloper.catalogmovie.R;
import com.simxdeveloper.catalogmovie.helper.Const;
import com.simxdeveloper.catalogmovie.ui.home.now_play.NowPlayingFragment;
import com.simxdeveloper.catalogmovie.ui.home.search.SearchFragment;
import com.simxdeveloper.catalogmovie.ui.home.up_coming.UpcomingFragment;

/**
 * User: simx Date: 21/05/18 0:27
 */
public class HomePageAdapter extends FragmentPagerAdapter {

  public HomePageAdapter (FragmentManager fm) {
    super (fm);
  }

  @Override
  public Fragment getItem (int position) {
    switch (position) {
      case 0 : return NowPlayingFragment.newInstance ();
      case 1 : return UpcomingFragment.newInstance ();
      case 2 : return SearchFragment.newInstance ();
    }
    return null;
  }

  @Override
  public int getCount () {
    return 3;
  }

  @Nullable
  @Override
  public CharSequence getPageTitle (int position) {
    switch (position) {
      case 0: return Apps.getContext ().getResources ().getString (R.string.now_playing);
      case 1: return Apps.getContext ().getResources ().getString (R.string.upcoming);
      case 2: return Apps.getContext ().getResources ().getString (R.string.search);
    }
    return null;
  }
}
