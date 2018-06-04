package com.simxdeveloper.catalogmovie.ui.home.up_coming;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.simxdeveloper.catalogmovie.R;
import com.simxdeveloper.catalogmovie.data.repo.model.global.ResultsItem;
import com.simxdeveloper.catalogmovie.helper.Const;
import com.simxdeveloper.catalogmovie.ui.detail.DetailActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragment extends Fragment implements UpcomingFragmentPresenter {
  private static final String FRAGMENT_STATE = "FRAGMENT_STATE_1";
  @BindView(R.id.rcv_movies)
  RecyclerView rcvMovies;
  private UpcomingFragmentPresenterImpl presenter;
  private AdapterMovieUpcoming adapterMovieUpcoming;
  public UpcomingFragment () {
  }
  private List<ResultsItem> resultsItemList = new ArrayList<> ();
  public static UpcomingFragment newInstance () {
    Bundle args = new Bundle ();
    UpcomingFragment fragment = new UpcomingFragment ();
    fragment.setArguments (args);
    return fragment;
  }

  @Override
  public void onCreate (@Nullable Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    if (savedInstanceState != null){
      resultsItemList = savedInstanceState.getParcelableArrayList (FRAGMENT_STATE);
      Log.e ("UpcomingFragment", "onCreate: " + resultsItemList.size ());
    }
  }

  @Override
  public View onCreateView (LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate (R.layout.fragment_upcoming, container, false);
    ButterKnife.bind (this, view);
    presenter = new UpcomingFragmentPresenterImpl (this);
    adapterMovieUpcoming = new AdapterMovieUpcoming (this,new ArrayList<> ());
    return view;
  }

  @Override
  public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated (view, savedInstanceState);
    rcvMovies.setHasFixedSize (true);
    rcvMovies.setItemAnimator (new DefaultItemAnimator ());
    rcvMovies.setLayoutManager (new LinearLayoutManager (getContext (),LinearLayoutManager.VERTICAL,false));
    rcvMovies.setAdapter (adapterMovieUpcoming);
    if (resultsItemList.size () == 0)presenter.getUpcomingMovie ();

  }

  @Override
  public void initMovie (List<ResultsItem> results) {
    adapterMovieUpcoming.updateAdapter (results);
    resultsItemList  = results;
  }

  @Override
  public void showError (String message) {
    Toast.makeText (getContext (),message,Toast.LENGTH_SHORT).show ();
  }

  @Override
  public void onMovieClicked (ResultsItem resultsItem) {
    DetailActivity.start (getContext (), resultsItem,false);
  }

  @Override
  public void onMovieShare (ResultsItem resultsItem) {
    Intent intent = new Intent (Intent.ACTION_SEND);
    intent.setType ("text/plain");
    intent.putExtra (Intent.EXTRA_TEXT, Const.IMAGE_PATH + resultsItem.getPosterPath ());
    intent.putExtra (Intent.EXTRA_SUBJECT, "Check out this movie!");
    startActivity (Intent.createChooser (intent, "Share"));
  }

  @Override
  public void onViewStateRestored (@Nullable Bundle savedInstanceState) {
    super.onViewStateRestored (savedInstanceState);
    adapterMovieUpcoming.updateAdapter (resultsItemList);
  }

  @Override
  public void onSaveInstanceState (@NonNull Bundle outState) {
    outState.putParcelableArrayList (FRAGMENT_STATE,
        (ArrayList<? extends Parcelable>) resultsItemList);
    super.onSaveInstanceState (outState);
  }
}
