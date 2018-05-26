package com.simxdeveloper.catalogmovie.ui.home.now_play;

import com.simxdeveloper.catalogmovie.data.repo.model.global.ResultsItem;
import java.util.List; /**
 * User: simx Date: 21/05/18 1:08
 */
public interface NowPlayingPresenter {

  void onMovieClicked (ResultsItem resultsItem);

  void iniMovie (List<ResultsItem> results);

  void showError (String message);

  void onMovieShare (ResultsItem resultsItem);
}
