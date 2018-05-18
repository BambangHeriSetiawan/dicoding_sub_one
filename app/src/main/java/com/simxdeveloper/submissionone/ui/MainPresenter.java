package com.simxdeveloper.submissionone.ui;

import com.simxdeveloper.submissionone.data.model.search.ResultsItem;
import java.util.List; /**
 * User: simx Date: 18/05/18 22:02
 */
public interface MainPresenter {

  void showLoading (boolean isShow);

  void showError (String message);

  void initMovieFromDiscover (List<ResultsItem> resultsItems);

  void showEmpetyResult (boolean isEmpety);

}
