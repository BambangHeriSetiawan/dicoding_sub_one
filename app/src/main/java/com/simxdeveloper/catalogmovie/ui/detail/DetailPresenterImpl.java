package com.simxdeveloper.catalogmovie.ui.detail;

import android.content.Context;
import android.util.Log;
import com.simxdeveloper.catalogmovie.data.local.AppDatabases;
import com.simxdeveloper.catalogmovie.data.local.LocalMovieDataSource;

/**
 * User: simx Date: 20/05/18 14:41
 */
public class DetailPresenterImpl {
  private DetailPresenter presenter;
  private Context context;
  private LocalMovieDataSource localMovieDataSource;
  public DetailPresenterImpl (DetailPresenter presenter, Context context) {
    this.presenter = presenter;
    this.context = context;
    this.localMovieDataSource = new LocalMovieDataSource (AppDatabases.getINSTANCE (context).movieDAO ());
  }

  public void chekFav (int id) {
    localMovieDataSource.getMovieById (id).subscribe (
        movies -> presenter.setIsFav(true),
        throwable -> Log.e ("DetailPresenterImpl", "chekFav: "+ throwable.getMessage ())
    );
  }
}
