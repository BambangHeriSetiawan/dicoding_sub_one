package com.simxdeveloper.favorit.main;



import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import com.simxdeveloper.favorit.R;
import com.simxdeveloper.favorit.data.Const;
import com.simxdeveloper.favorit.data.DatabaseContract;
import com.simxdeveloper.favorit.data.Favorite;
import com.simxdeveloper.favorit.detail.DetailFavActivity;

public class FavActivity extends AppCompatActivity implements MainPresenter {

  private static final int LOADER_MOVIES = 1;
  private MainPresenterImpl presenter;
  private AdapterFavoriteMovie adapterFavoriteMovie;
  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_fav);
    initActionBar();
    presenter = new MainPresenterImpl (this);
    adapterFavoriteMovie = new AdapterFavoriteMovie (this,this);
    getSupportLoaderManager ().initLoader (LOADER_MOVIES, null, mLoaderCallbacks);
    ((RecyclerView)findViewById (R.id.rcv_movie)).setHasFixedSize (true);
    ((RecyclerView)findViewById (R.id.rcv_movie)).setItemAnimator (new DefaultItemAnimator ());
    ((RecyclerView)findViewById (R.id.rcv_movie)).setLayoutManager (new LinearLayoutManager (this, LinearLayoutManager.VERTICAL, false));
    ((RecyclerView)findViewById (R.id.rcv_movie)).setAdapter (adapterFavoriteMovie);

  }

  private void initActionBar () {
    setSupportActionBar (findViewById (R.id.toolbar_fav));
    getSupportActionBar ().setDisplayShowTitleEnabled (true);
    getSupportActionBar ().setTitle (getString (R.string.favorit));
    getSupportActionBar ().setDisplayShowTitleEnabled (true);
    getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
  }

  @Override
  public void onMovieClicked (Favorite favorite) {
    Uri uri = Uri.parse(DatabaseContract.CONTENT_URI+"/"+favorite.getId());
    DetailFavActivity.start ( this,uri);
  }

  @Override
  public void onMovieShare (Favorite favorite) {
    Intent intent = new Intent (Intent.ACTION_SEND);
    intent.setType ("text/plain");
    intent.putExtra (Intent.EXTRA_TEXT, Const.IMAGE_PATH + favorite.getPosterPath ());
    intent.putExtra (Intent.EXTRA_SUBJECT, "Check out this movie!");
    startActivity (Intent.createChooser (intent, "Share"));
  }

  private LoaderCallbacks<Cursor> mLoaderCallbacks = new LoaderCallbacks<Cursor> () {
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader (int id, @Nullable Bundle args) {
      Log.e ("FavActivity", "onCreateLoader: " + id);
      switch (id) {
        case LOADER_MOVIES:
          return new CursorLoader (
              getApplicationContext (),
              DatabaseContract.CONTENT_URI,
              null,
              null, null, null
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
          adapterFavoriteMovie.updateAdapter (data);
          break;
      }
    }

    @Override
    public void onLoaderReset (@NonNull Loader<Cursor> loader) {
      switch (loader.getId ()) {
        case LOADER_MOVIES:
          adapterFavoriteMovie.updateAdapter (null);
          break;
      }
    }
    };

}
