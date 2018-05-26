package com.simxdeveloper.catalogmovie.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.simxdeveloper.catalogmovie.R;
import com.simxdeveloper.catalogmovie.data.repo.model.global.ResultsItem;
import com.simxdeveloper.catalogmovie.helper.Const;



public class DetailActivity extends AppCompatActivity implements DetailPresenter {

  public static String KEY_DETAIL = "detail";
  @BindView(R.id.toolbar)
  Toolbar toolbar;
  @BindView(R.id.img_poster)
  ImageView imgPoster;
  @BindView(R.id.rating)
  RatingBar rating;
  @BindView(R.id.tv_title)
  TextView tvTitle;
  @BindView(R.id.tv_release_date)
  TextView tvReleaseDate;
  @BindView(R.id.tv_titile_ori)
  TextView tvTitileOri;
  @BindView(R.id.tv_overview)
  TextView tvOverview;
  private ResultsItem detailMovie;

  public static void start (Context context, ResultsItem resultsItem) {
    Intent starter = new Intent (context, DetailActivity.class);
    starter.putExtra (KEY_DETAIL, resultsItem);
    context.startActivity (starter);
  }

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_detail);
    ButterKnife.bind (this);
    if (getIntent ().getExtras () != null) {
      detailMovie = getIntent ().getExtras ().getParcelable (KEY_DETAIL);
      initActionbar (detailMovie.getTitle ());
      initUi (detailMovie);
    } else {
      initActionbar ("Not Found");
    }
  }

  private void initUi (ResultsItem detailMovie) {
    Glide.with (this).load (Const.IMAGE_PATH + detailMovie.getPosterPath ()).into (imgPoster);
    tvTitle.setText (detailMovie.getTitle ());
    tvTitileOri.setText (detailMovie.getOriginalTitle ());
    tvReleaseDate.setText (detailMovie.getReleaseDate ());
    tvOverview.setText (detailMovie.getOverview ());
    rating.setRating ((float) detailMovie.getVoteAverage ());
  }

  private void initActionbar (String title) {
    setSupportActionBar (toolbar);
    getSupportActionBar ().setDisplayShowTitleEnabled (true);
    getSupportActionBar ().setTitle (title);
    getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
    getSupportActionBar ().setHomeAsUpIndicator (
        getResources ().getDrawable (R.drawable.ic_arrow_back_white_24dp));
  }

  @Override
  public boolean onOptionsItemSelected (MenuItem item) {
    switch (item.getItemId ()) {
      case android.R.id.home:
        onBackPressed ();
        break;
    }
    return super.onOptionsItemSelected (item);
  }
}
