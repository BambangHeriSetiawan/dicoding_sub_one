package com.simxdeveloper.catalogmovie.ui.home.favorite;

import android.content.Context;
import com.simxdeveloper.catalogmovie.data.local.AppDatabases;
import com.simxdeveloper.catalogmovie.data.local.LocalMovieDataSource;

/**
 * User: simx Date: 26/05/18 18:47
 */
public class FavoritePresenterImpl {
  private FavoritePresenter presenter;
  private Context context;
  private LocalMovieDataSource localMovieDataSource;
  public FavoritePresenterImpl (
      FavoritePresenter presenter, Context context) {
    this.presenter = presenter;
    this.context = context;
    this.localMovieDataSource = new LocalMovieDataSource (AppDatabases.getINSTANCE (context).movieDAO ());
  }

  public void getFavoritMovie () {
    localMovieDataSource.getAll ().subscribe (
        movies -> presenter.initDataMovie(movies),
        throwable -> presenter.showError(throwable.getMessage ())
    );
  }
}
