package com.simxdeveloper.catalogmovie.ui.home.favorite;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.simxdeveloper.catalogmovie.R;
import com.simxdeveloper.catalogmovie.data.local.Movies;
import com.simxdeveloper.catalogmovie.data.repo.model.global.ResultsItem;
import com.simxdeveloper.catalogmovie.helper.Const;
import com.simxdeveloper.catalogmovie.ui.detail.DetailActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements FavoritePresenter {


  @BindView(R.id.rcv_movies)
  RecyclerView rcvMovies;
  public FavoriteFragment () {
  }

  private FavoritePresenterImpl presenter;
  private AdapterMovieFavorite adapterMovieFavorite;

  public static FavoriteFragment newInstance () {
    Bundle args = new Bundle ();
    FavoriteFragment fragment = new FavoriteFragment ();
    fragment.setArguments (args);
    return fragment;
  }

  @Override
  public View onCreateView (LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate (R.layout.fragment_favorite, container, false);
    ButterKnife.bind (this, view);
    presenter = new FavoritePresenterImpl (this, getContext ());
    adapterMovieFavorite = new AdapterMovieFavorite (this, new ArrayList<> ());
    presenter.getFavoritMovie();
    return view;
  }

  @Override
  public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated (view, savedInstanceState);
    rcvMovies.setHasFixedSize (true);
    rcvMovies.setItemAnimator (new DefaultItemAnimator ());
    rcvMovies.setLayoutManager (new LinearLayoutManager (getContext (),LinearLayoutManager.VERTICAL,false));
    rcvMovies.setAdapter (adapterMovieFavorite);
  }

  @Override
  public void initDataMovie (List<Movies> movies) {
    adapterMovieFavorite.updateAdapter (movies);
  }

  @Override
  public void showError (String message) {
    Toast.makeText (getContext (),message,Toast.LENGTH_SHORT).show ();
  }

  @Override
  public void onMovieClicked (Movies movies) {
    ResultsItem resultsItem = new ResultsItem ();
    resultsItem.setAdult (movies.isAdult ());
    resultsItem.setBackdropPath (movies.getBackdropPath ());
    resultsItem.setId (movies.getId ());
    resultsItem.setTitle (movies.getTitle ());
    resultsItem.setOriginalTitle (movies.getOriginalTitle ());
    resultsItem.setVoteAverage (movies.getVoteAverage ());
    resultsItem.setVoteCount (movies.getVoteCount ());
    resultsItem.setOriginalLanguage (movies.getOriginalLanguage ());
    resultsItem.setOverview (movies.getOverview ());
    resultsItem.setPopularity (movies.getPopularity ());
    resultsItem.setPosterPath (movies.getPosterPath ());
    resultsItem.setReleaseDate (movies.getReleaseDate ());
    resultsItem.setOriginalLanguage (movies.getOriginalLanguage ());
    DetailActivity.start (getContext (), resultsItem,true);
  }

  @Override
  public void onMovieShare (Movies movies) {
    Intent intent = new Intent (Intent.ACTION_SEND);
    intent.setType ("text/plain");
    intent.putExtra (Intent.EXTRA_TEXT, Const.IMAGE_PATH + movies.getPosterPath ());
    intent.putExtra (Intent.EXTRA_SUBJECT, "Check out this movie!");
    startActivity (Intent.createChooser (intent, "Share"));
  }

}
