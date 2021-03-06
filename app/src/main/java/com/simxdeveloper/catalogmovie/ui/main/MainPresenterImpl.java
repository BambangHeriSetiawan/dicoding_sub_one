package com.simxdeveloper.catalogmovie.ui.main;

import com.simxdeveloper.catalogmovie.data.repo.ObservableHelper;
import com.simxdeveloper.catalogmovie.helper.Const;

/**
 * User: simx Date: 18/05/18 22:03
 */
public class MainPresenterImpl {
  private MainPresenter presenter;

  public MainPresenterImpl (MainPresenter presenter) {
    this.presenter = presenter;
  }

  /**
   * Get data movie from path "discover/movie"
   * Default sort is popularity.desc
   */
  public void discoverMovie () {
    presenter.showLoading (true);
    ObservableHelper.discoverMoviesObservable (Const.PATH_SORT_BY_POP_DESC).subscribe (
        responseDiscoverMovies -> {
          if (responseDiscoverMovies.getResults ()!=null)presenter.initMovieFromDiscover(responseDiscoverMovies.getResults ());
            else presenter.showEmpetyResult (true);
        }
        ,throwable -> presenter.showError(throwable.getMessage ())
        ,() -> presenter.showLoading(false)
    );
  }

  /**
   * Get Data movie from path "search/movie" base on param
   * @param query
   */
  public void searchMovie(String query){
    presenter.showLoading (true);
    ObservableHelper.searchMovieObservable (query).subscribe (
        responseSearchMovie -> {
          if (responseSearchMovie.getResults ()!=null)presenter.initMovieFromSearch (responseSearchMovie.getResults ());
            else presenter.showEmpetyResult(true);
        }
        ,throwable -> presenter.showError (throwable.getMessage ())
        ,() -> presenter.showLoading (false)
    );
  }
}
