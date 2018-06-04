package com.simxdeveloper.catalogmovie.ui.home.favorite;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
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
import com.simxdeveloper.catalogmovie.R;
import com.simxdeveloper.catalogmovie.data.local.Movies;
import com.simxdeveloper.catalogmovie.data.local.TableConst;
import com.simxdeveloper.catalogmovie.data.provider.MovieContentProvider;
import com.simxdeveloper.catalogmovie.data.repo.model.global.ResultsItem;
import com.simxdeveloper.catalogmovie.helper.Const;
import com.simxdeveloper.catalogmovie.ui.detail.DetailActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements FavoritePresenter {

  private static final int LOADER_MOVIES = 1;
  @BindView(R.id.rcv_movies)
  RecyclerView rcvMovies;
  public FavoriteFragment () {
  }

  private FavoritePresenterImpl presenter;
  private AdapterMovieFavorite adapterMovieFavorite;
  private AdapterCursorFavorite adapterCursorFavorite;
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
    adapterCursorFavorite = new AdapterCursorFavorite (this);
    getLoaderManager ().initLoader (LOADER_MOVIES,null,mLoaderCallbacks);
    return view;
  }

  @Override
  public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated (view, savedInstanceState);
    rcvMovies.setHasFixedSize (true);
    rcvMovies.setItemAnimator (new DefaultItemAnimator ());
    rcvMovies.setLayoutManager (new LinearLayoutManager (getContext (),LinearLayoutManager.VERTICAL,false));
    rcvMovies.setAdapter (adapterCursorFavorite);
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
    Uri uri = Uri.parse(TableConst.CONTENT_URI+"/"+movies.getId());
    DetailActivity.start (getContext (),uri,true);
  }

  @Override
  public void onMovieShare (Movies movies) {
    Intent intent = new Intent (Intent.ACTION_SEND);
    intent.setType ("text/plain");
    intent.putExtra (Intent.EXTRA_TEXT, Const.IMAGE_PATH + movies.getPosterPath ());
    intent.putExtra (Intent.EXTRA_SUBJECT, "Check out this movie!");
    startActivity (Intent.createChooser (intent, "Share"));
  }

  private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks = new LoaderCallbacks<Cursor> () {
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader (int id, @Nullable Bundle args) {
      Log.e ("FavoriteFragment", "onCreateLoader: " + id);
      switch (id) {
        case LOADER_MOVIES :
          return new CursorLoader (
              getActivity (),
              TableConst.CONTENT_URI,
              new String[] {TableConst.COLUMN_TITLE},
              null,null,null
          );
          default:
            try {
              throw new IllegalAccessException ();
            } catch (IllegalAccessException e) {
              e.printStackTrace ();
              Log.e ("FavoriteFragment", "onCreateLoader: " + e.getMessage ());
            }
      }
      return null;
    }

    @Override
    public void onLoadFinished (@NonNull Loader<Cursor> loader, Cursor data) {
      switch (loader.getId ()) {
        case LOADER_MOVIES:
          adapterCursorFavorite.updateAdapter (data);
          break;
      }
    }

    @Override
    public void onLoaderReset (@NonNull Loader<Cursor> loader) {
      switch (loader.getId ()) {
        case LOADER_MOVIES:
          adapterCursorFavorite.updateAdapter (null);
          break;
      }
    }
  };

}
