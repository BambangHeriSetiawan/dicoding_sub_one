package com.simxdeveloper.catalogmovie.ui.home.up_coming;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simxdeveloper.catalogmovie.R;
import com.simxdeveloper.catalogmovie.data.model.global.ResultsItem;
import com.simxdeveloper.catalogmovie.ui.detail.DetailActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragment extends Fragment implements UpcomingFragmentPresenter {


  public UpcomingFragment () {}

  public static UpcomingFragment newInstance () {
    Bundle args = new Bundle ();
    UpcomingFragment fragment = new UpcomingFragment ();
    fragment.setArguments (args);
    return fragment;
  }

  @Override
  public View onCreateView (LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate (R.layout.fragment_upcoming, container, false);
  }

  @Override
  public void onMovieClicked (ResultsItem resultsItem) {
    DetailActivity.start (getContext (),resultsItem);
  }
}
