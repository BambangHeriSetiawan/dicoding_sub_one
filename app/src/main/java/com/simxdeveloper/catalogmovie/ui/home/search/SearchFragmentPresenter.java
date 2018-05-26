package com.simxdeveloper.catalogmovie.ui.home.search;

import com.simxdeveloper.catalogmovie.data.repo.model.global.ResultsItem;
import java.util.List;

/**
 * User: simx Date: 21/05/18 2:21
 */
public interface SearchFragmentPresenter {

  void onMovieClicked (ResultsItem resultsItem);

  void initMovie (List<ResultsItem> resultsItems);

  void showError (String message);
}
