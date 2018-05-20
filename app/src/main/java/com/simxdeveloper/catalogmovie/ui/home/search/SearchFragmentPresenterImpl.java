package com.simxdeveloper.catalogmovie.ui.home.search;

import com.simxdeveloper.catalogmovie.data.ObservableHelper;

/**
 * User: simx Date: 21/05/18 2:21
 */
public class SearchFragmentPresenterImpl {
  private SearchFragmentPresenter presenter;

  public SearchFragmentPresenterImpl (
      SearchFragmentPresenter presenter) {
    this.presenter = presenter;
  }

  public void getMovie (String s) {
    ObservableHelper.searchMovieObservable (s).subscribe (
        responseSearchMovie -> presenter.initMovie(responseSearchMovie.getResults ()),
        throwable -> presenter.showError(throwable.getMessage ())
    );
  }
}
