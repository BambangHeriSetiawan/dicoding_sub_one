package com.simxdeveloper.catalogmovie.ui.home.search;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simxdeveloper.catalogmovie.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


  public SearchFragment () { }

  public static SearchFragment newInstance () {
    Bundle args = new Bundle ();
    SearchFragment fragment = new SearchFragment ();
    fragment.setArguments (args);
    return fragment;
  }
  @Override
  public View onCreateView (LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate (R.layout.fragment_search, container, false);
    return view;
  }

}
