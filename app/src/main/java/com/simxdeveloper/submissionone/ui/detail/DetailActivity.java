package com.simxdeveloper.submissionone.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.simxdeveloper.submissionone.R;
import com.simxdeveloper.submissionone.data.model.search.ResultsItem;

public class DetailActivity extends AppCompatActivity implements DetailPresenter{
  public static String KEY_DETAIL ="detail";
  private ResultsItem detailMovie;
  public static void start(Context context, ResultsItem resultsItem) {
      Intent starter = new Intent(context, DetailActivity.class);
      starter.putExtra (KEY_DETAIL,resultsItem);
      context.startActivity(starter);
  }

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_detail);
    if (getIntent ().getExtras ()!=null){
      detailMovie = getIntent ().getExtras ().getParcelable (KEY_DETAIL);
      Log.e ("DetailActivity", "onCreate: " + detailMovie.toString ());
    }
  }
}
