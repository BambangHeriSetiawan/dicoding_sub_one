package com.simxdeveloper.catalogmovie.ui.home.up_coming;

import com.simxdeveloper.catalogmovie.data.repo.model.global.ResultsItem;
import java.util.List; /**
 * User: simx Date: 21/05/18 2:23
 */
public interface UpcomingFragmentPresenter {

  void onMovieClicked (ResultsItem resultsItem);

  void onMovieShare (ResultsItem resultsItem);

  void initMovie (List<ResultsItem> results);

  void showError (String message);
}
