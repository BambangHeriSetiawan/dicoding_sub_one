package com.simxdeveloper.catalogmovie.ui.home.now_play;


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
public class NowPlayingFragment extends Fragment implements NowPlayingPresenter {

  private static String KEY_PAGE = "page";
  private static final String FRAGMENT_STATE = "FRAGMENT_STATE";
  @BindView(R.id.rcv_movies)
  RecyclerView rcvMovies;
  private NowPlayingPresenterImpl presenter;
  private AdapterMovieNowPlaying adapterMovieNowPlaying;
  public NowPlayingFragment () { }
  private List<ResultsItem> resultsItemList = new ArrayList<> ();
  public static NowPlayingFragment newInstance () {
    Bundle args = new Bundle ();
    NowPlayingFragment fragment = new NowPlayingFragment ();
    fragment.setArguments (args);
    return fragment;
  }


  @Override
  public void onCreate (@Nullable Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    if (savedInstanceState != null){
      resultsItemList = savedInstanceState.getParcelableArrayList (FRAGMENT_STATE);
    }
  }

  @Override
  public View onCreateView (LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate (R.layout.fragment_now_playing, container, false);
    ButterKnife.bind (this, view);
    presenter = new NowPlayingPresenterImpl (this);
    adapterMovieNowPlaying = new AdapterMovieNowPlaying (this, new ArrayList<> ());
    return view;
  }

  @Override
  public void onViewStateRestored (@Nullable Bundle savedInstanceState) {
    super.onViewStateRestored (savedInstanceState);
    adapterMovieNowPlaying.updateAdapter (resultsItemList);
  }

  @Override
  public void onViewCreated (View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated (view, savedInstanceState);
    rcvMovies.setHasFixedSize (true);
    rcvMovies.setItemAnimator (new DefaultItemAnimator ());
    rcvMovies.setLayoutManager (new LinearLayoutManager (getContext (),LinearLayoutManager.VERTICAL,false));
    rcvMovies.setAdapter (adapterMovieNowPlaying);
    if (resultsItemList.size () == 0)presenter.getNowPlaying ();
  }

  @Override
  public void iniMovie (List<ResultsItem> results) {
    adapterMovieNowPlaying.updateAdapter (results);
    resultsItemList = results;
  }

  @Override
  public void showError (String message) {
    Toast.makeText (getContext (),message,Toast.LENGTH_SHORT).show ();
  }

  @Override
  public void onMovieClicked (ResultsItem resultsItem) {
    DetailActivity.start (getContext (),resultsItem,false);

  }

  @Override
  public void onMovieShare (ResultsItem resultsItem) {
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.setType("text/plain");
    intent.putExtra(Intent.EXTRA_TEXT, Const.IMAGE_PATH + resultsItem.getPosterPath ());
    intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out this movie!");
    startActivity(Intent.createChooser(intent, "Share"));

  }

  @Override
  public void onSaveInstanceState (@NonNull Bundle outState) {
    outState.putParcelableArrayList (FRAGMENT_STATE,
        (ArrayList<? extends Parcelable>) resultsItemList);
    super.onSaveInstanceState (outState);
  }
}
