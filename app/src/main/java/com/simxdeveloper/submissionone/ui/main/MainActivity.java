package com.simxdeveloper.submissionone.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
  private MainPresenterImpl presenter;
  private AdapterMovie adapterMovie;

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_main);
    ButterKnife.bind (this);
    presenter = new MainPresenterImpl (this);
    adapterMovie = new AdapterMovie (this, new ArrayList<> ());
    if (!Tools.isOnline ()) {
      showOfflineMessage ();
    } else {
      presenter.discoverMovie ();
      initRcv ();
    }
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
    if (isShow){
      progressView.setVisibility (View.VISIBLE);
    }else {
      progressView.setVisibility (View.GONE);
    }
  }

  @Override
  public void showError (String message) {
    Toast.makeText (this,message,Toast.LENGTH_SHORT).show ();
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
    if (isEmpety){
      lytEmpety.setVisibility (View.VISIBLE);
    }else {
      lytEmpety.setVisibility (View.GONE);
    }
  }

  @OnClick(R.id.tv_try_again)
  public void onViewClicked () {
    presenter.discoverMovie ();
  }
}
