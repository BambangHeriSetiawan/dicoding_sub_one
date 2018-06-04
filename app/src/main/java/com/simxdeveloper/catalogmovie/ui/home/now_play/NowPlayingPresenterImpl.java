package com.simxdeveloper.catalogmovie.ui.home.now_play;

import com.simxdeveloper.catalogmovie.data.repo.ObservableHelper;

/**
 * User: simx Date: 21/05/18 1:08
 */
public class NowPlayingPresenterImpl {
  private NowPlayingPresenter presenter;

  public NowPlayingPresenterImpl (
      NowPlayingPresenter presenter) {
    this.presenter = presenter;
  }

  public void getNowPlaying () {
    ObservableHelper.nowPlayingObservable ().subscribe (
        responseNowPlaying -> presenter.iniMovie(responseNowPlaying.getResults ()),
        throwable -> presenter.showError(throwable.getMessage ())
    );
  }
}
