package com.simxdeveloper.submissionone.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.simxdeveloper.submissionone.R;
import com.simxdeveloper.submissionone.data.model.search.ResultsItem;
import com.simxdeveloper.submissionone.helper.Tools;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainPresenter {
  private MainPresenterImpl presenter;
  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_main);
    presenter = new MainPresenterImpl (this);
    if (Tools.isOnline ()){
      showOfflineMessage();
    }else {
      presenter.discoverMovie();
    }
  }

  private void showOfflineMessage () {

  }

  @Override
  public void showLoading (boolean isShow) {

  }

  @Override
  public void showError (String message) {

  }

  @Override
  public void initMovieFromDiscover (List<ResultsItem> resultsItems) {

  }

  @Override
  public void showEmpetyResult (boolean isEmpety) {

  }
}
