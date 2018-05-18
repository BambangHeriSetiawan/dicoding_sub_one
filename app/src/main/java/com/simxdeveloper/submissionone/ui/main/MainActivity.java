package com.simxdeveloper.submissionone.ui.main;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.simxdeveloper.submissionone.R;
import com.simxdeveloper.submissionone.data.model.search.ResultsItem;
import com.simxdeveloper.submissionone.helper.Tools;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainPresenter {

  @BindView(R.id.toolbar)
  Toolbar toolbar;
  @BindView(R.id.progress_view)
  ProgressBar progressView;
  @BindView(R.id.lyt_empety)
  RelativeLayout lytEmpety;
  @BindView(R.id.rcv_movies)
  RecyclerView rcvMovies;
  @BindView(R.id.lyt_no_connection)
  RelativeLayout lytNoConnection;
  @BindView(R.id.et_search)
  TextInputEditText etSearch;
  @BindView(R.id.lyt_main)
  CoordinatorLayout lytMain;
  private MainPresenterImpl presenter;
  private AdapterMovie adapterMovie;
  private InputMethodManager imm;

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_main);
    ButterKnife.bind (this);
    initActionBar();
    presenter = new MainPresenterImpl (this);
    imm = (InputMethodManager) getSystemService (INPUT_METHOD_SERVICE);
    adapterMovie = new AdapterMovie (this, new ArrayList<> ());
    if (!Tools.isOnline ()) {
      showOfflineMessage ();
    } else {
      presenter.discoverMovie ();
      initRcv ();
    }
  }

  private void initActionBar () {
    Log.e ("MainActivity", "initActionBar: " + getSupportActionBar ());
    setSupportActionBar (toolbar);
    getSupportActionBar ().setDisplayShowTitleEnabled (true);
    getSupportActionBar ().setTitle ("Dicoding");
  }

  private void initRcv () {
    rcvMovies.setHasFixedSize (true);
    rcvMovies.setItemAnimator (new DefaultItemAnimator ());
    rcvMovies
        .setLayoutManager (new LinearLayoutManager (this, LinearLayoutManager.VERTICAL, false));
  }

  private void showOfflineMessage () {
    lytNoConnection.setVisibility (View.VISIBLE);
  }

  @Override
  public void showLoading (boolean isShow) {
    if (isShow) {
      progressView.setVisibility (View.VISIBLE);
    } else {
      progressView.setVisibility (View.GONE);
    }
  }

  @Override
  public void showError (String message) {
    Toast.makeText (this, message, Toast.LENGTH_SHORT).show ();
  }

  @Override
  public void initMovieFromDiscover (List<ResultsItem> resultsItems) {
    adapterMovie.updateAdapter (resultsItems);
    rcvMovies.setAdapter (adapterMovie);
  }

  @Override
  public void initMovieFromSearch (List<ResultsItem> resultsItems) {
    adapterMovie.updateAdapter (resultsItems);
    rcvMovies.setAdapter (adapterMovie);
  }

  @Override
  public void showEmpetyResult (boolean isEmpety) {
    if (isEmpety) {
      lytEmpety.setVisibility (View.VISIBLE);
    } else {
      lytEmpety.setVisibility (View.GONE);
    }
  }

  @OnClick(R.id.tv_try_again)
  public void onTryAgainClicked () {
    if (Tools.isValidInputSearch (etSearch.getText ().toString ())) {
      presenter.searchMovie (etSearch.getText ().toString ());
    } else {
      presenter.discoverMovie ();
    }

  }

  @OnClick(R.id.btn_search)
  public void onBtnSearchClicked () {
    hiddenKeyboard (lytEmpety);
    if (Tools.isValidInputSearch (etSearch.getText ().toString ())) {
      presenter.searchMovie (etSearch.getText ().toString ());
    }
  }

  private void hiddenKeyboard (View view) {
    imm.hideSoftInputFromWindow (view.getWindowToken (), 0);
  }
}
