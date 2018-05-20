package com.simxdeveloper.catalogmovie.ui.main;

import com.simxdeveloper.catalogmovie.data.model.global.ResultsItem;
import java.util.List; /**
 * User: simx Date: 18/05/18 22:02
 */
public interface MainPresenter {

  void showLoading (boolean isShow);

  void showError (String message);

  void initMovieFromDiscover (List<ResultsItem> resultsItems);

  void showEmpetyResult (boolean isEmpety);

  void initMovieFromSearch (List<ResultsItem> resultsItems);

  void onMovieClicked (ResultsItem resultsItem);
}
