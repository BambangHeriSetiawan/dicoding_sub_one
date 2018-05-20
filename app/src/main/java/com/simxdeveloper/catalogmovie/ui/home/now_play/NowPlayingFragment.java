package com.simxdeveloper.catalogmovie.ui.home.now_play;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simxdeveloper.catalogmovie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment {
  private static String KEY_PAGE = "page";
  public NowPlayingFragment () {}
  public static NowPlayingFragment newInstance () {
    Bundle args = new Bundle ();
    NowPlayingFragment fragment = new NowPlayingFragment ();
    fragment.setArguments (args);
    return fragment;
  }

  @Override
  public View onCreateView (LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate (R.layout.fragment_now_playing, container, false);
    return view;
  }

  @Override
  public void onViewCreated (View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated (view, savedInstanceState);
  }

  @Override
  public void onResume () {
    super.onResume ();
    Log.e ("NowPlayingFragment", "onResume: ");
  }

  @Override
  public void onDestroy () {
    super.onDestroy ();
    Log.e ("NowPlayingFragment", "onDestroy: " );
  }
}
