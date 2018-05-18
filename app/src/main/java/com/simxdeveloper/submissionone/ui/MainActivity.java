package com.simxdeveloper.submissionone.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.simxdeveloper.submissionone.R;
import com.simxdeveloper.submissionone.helper.Tools;

public class MainActivity extends AppCompatActivity implements MainPresenter {
  private MainPresenterImpl presenter;
  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_main);
    presenter = new MainPresenterImpl (this);
    if (Tools.isOnline ()){

    }else {

    }

  }
}
