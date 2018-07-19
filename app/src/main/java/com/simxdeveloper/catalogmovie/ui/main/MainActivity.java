package com.simxdeveloper.catalogmovie.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
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
import com.simxdeveloper.catalogmovie.R;
import com.simxdeveloper.catalogmovie.data.repo.model.global.ResultsItem;
import com.simxdeveloper.catalogmovie.databinding.ActivityMainBinding;
import com.simxdeveloper.catalogmovie.helper.Tools;
import com.simxdeveloper.catalogmovie.ui.detail.DetailActivity;
import com.simxdeveloper.catalogmovie.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
  ActivityMainBinding activityMainBinding;
  private AdapterMovie adapterMovie;
  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
    MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

    /*setContentView (R.layout.activity_main);
    ButterKnife.bind (this);
    initActionBar();
    presenter = new MainPresenterImpl (this);
    imm = (InputMethodManager) getSystemService (INPUT_METHOD_SERVICE);
    adapterMovie = new AdapterMovie (this, new ArrayList<> ());
    if (!Tools.isOnline ()) {
      showOfflineMessage (true);
    } else {
      presenter.discoverMovie ();

    }*/
  }


  /**
   * Config Recyleview Moive
   */
  private void initRcv () {

    activityMainBinding.rcvMovies.setHasFixedSize (true);
    activityMainBinding.rcvMovies.setItemAnimator (new DefaultItemAnimator ());
    activityMainBinding.rcvMovies
        .setLayoutManager (new LinearLayoutManager (this, LinearLayoutManager.VERTICAL, false));
    activityMainBinding.rcvMovies.setAdapter(adapterMovie);
  }

}
