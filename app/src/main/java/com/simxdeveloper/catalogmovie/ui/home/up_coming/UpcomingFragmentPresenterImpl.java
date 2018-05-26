package com.simxdeveloper.catalogmovie.ui.home.up_coming;

import com.simxdeveloper.catalogmovie.data.repo.ObservableHelper;

/**
 * User: simx Date: 21/05/18 2:23
 */
public class UpcomingFragmentPresenterImpl {
  private UpcomingFragmentPresenter presenter;

  public UpcomingFragmentPresenterImpl (
      UpcomingFragmentPresenter presenter) {
    this.presenter = presenter;
  }

  public void getUpcomingMovie () {
    ObservableHelper.upcomingObservable ().subscribe (
        responseUpcoming -> presenter.initMovie(responseUpcoming.getResults ()),
        throwable -> presenter.showError(throwable.getMessage ())
    );
  }
}
