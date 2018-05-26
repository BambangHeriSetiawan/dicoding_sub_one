package com.simxdeveloper.catalogmovie.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.simxdeveloper.catalogmovie.R;
import com.simxdeveloper.catalogmovie.data.local.AppDatabases;
import com.simxdeveloper.catalogmovie.data.local.LocalMovieDataSource;
import com.simxdeveloper.catalogmovie.data.local.Movies;
import com.simxdeveloper.catalogmovie.data.repo.model.global.ResultsItem;
import com.simxdeveloper.catalogmovie.helper.Const;


public class DetailActivity extends AppCompatActivity implements DetailPresenter {

  public static String KEY_DETAIL = "detail";
  public static String KEY_FAV = "fav";
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

  private DetailPresenterImpl presenter;
  private ResultsItem detailMovie;
  private LocalMovieDataSource localMovieDataSource;
  private boolean isFav;
  public static void start (Context context, ResultsItem resultsItem, Boolean fav) {
    Intent starter = new Intent (context, DetailActivity.class);
    starter.putExtra (KEY_DETAIL, resultsItem);
    starter.putExtra (KEY_FAV,fav);
    context.startActivity (starter);
  }



  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_detail);
    ButterKnife.bind (this);
    localMovieDataSource = new LocalMovieDataSource (AppDatabases.getINSTANCE (this).movieDAO ());
    presenter = new DetailPresenterImpl (this,this);
    if (getIntent ().getExtras () != null) {
      detailMovie = getIntent ().getExtras ().getParcelable (KEY_DETAIL);
      presenter.chekFav(detailMovie.getId ());
      isFav = getIntent ().getExtras ().getBoolean (KEY_FAV);
      initActionbar (detailMovie.getTitle ());
      initUi (detailMovie);

    } else {
      initActionbar ("Not Found");
    }
    Log.e ("DetailActivity", "onCreate: " + isFav);
  }

  private void initUi (ResultsItem detailMovie) {
    Log.e ("DetailActivity", "initUi: " + detailMovie.toString ());
    Glide.with (this).load (Const.IMAGE_PATH + detailMovie.getPosterPath ()).into (imgPoster);
    tvTitle.setText (detailMovie.getTitle ());
    tvTitileOri.setText (detailMovie.getOriginalTitle ());
    tvReleaseDate.setText (detailMovie.getReleaseDate ());
    tvOverview.setText (detailMovie.getOverview ());
    rating.setRating ((float) detailMovie.getVoteAverage ());
  }

  @Override
  public void setIsFav (boolean isFav) {
    this.isFav = isFav;
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
  public boolean onCreateOptionsMenu (Menu menu) {
    Log.e ("DetailActivity", "onCreateOptionsMenu: " + isFav);
    if (isFav){
      getMenuInflater ().inflate (R.menu.detail_menu_is_fav,menu);
    }else {
      getMenuInflater ().inflate (R.menu.detail_menu_no_fav,menu);
    }

    return super.onCreateOptionsMenu (menu);
  }

  @Override
  public boolean onOptionsItemSelected (MenuItem item) {
    switch (item.getItemId ()) {
      case android.R.id.home:
        onBackPressed ();
        break;
      case R.id.nav_is_fav:
        addTofavorite();
        break;
      case R.id.nav_no_fav:
        removeFromfavorite();
        break;
    }
    return super.onOptionsItemSelected (item);
  }

  private void removeFromfavorite () {
    Movies movies = new Movies ();
    movies.setAdult (detailMovie.isAdult ());
    movies.setBackdropPath (detailMovie.getBackdropPath ());
    movies.setId (detailMovie.getId ());
    movies.setVoteCount (detailMovie.getVoteCount ());
    movies.setVoteAverage (detailMovie.getVoteAverage ());
    movies.setOriginalLanguage (detailMovie.getOriginalLanguage ());
    movies.setOriginalTitle (detailMovie.getOriginalTitle ());
    movies.setPopularity (detailMovie.getPopularity ());
    movies.setOverview (detailMovie.getOverview ());
    movies.setTitle (detailMovie.getTitle ());
    movies.setPosterPath (detailMovie.getPosterPath ());
    movies.setReleaseDate (detailMovie.getReleaseDate ());
    localMovieDataSource.deleteAll (movies);
    showAddMessage ("Delete from Favorite");
  }

  private void addTofavorite () {
    Movies movies = new Movies ();
    movies.setAdult (detailMovie.isAdult ());
    movies.setBackdropPath (detailMovie.getBackdropPath ());
    movies.setId (detailMovie.getId ());
    movies.setVoteCount (detailMovie.getVoteCount ());
    movies.setVoteAverage (detailMovie.getVoteAverage ());
    movies.setOriginalLanguage (detailMovie.getOriginalLanguage ());
    movies.setOriginalTitle (detailMovie.getOriginalTitle ());
    movies.setPopularity (detailMovie.getPopularity ());
    movies.setOverview (detailMovie.getOverview ());
    movies.setTitle (detailMovie.getTitle ());
    movies.setPosterPath (detailMovie.getPosterPath ());
    movies.setReleaseDate (detailMovie.getReleaseDate ());
    Log.e ("DetailActivity", "addTofavorite: " + movies.toString ());
    localMovieDataSource.insert (movies).subscribe (
        () -> showAddMessage ("Add to Favorite")
    );
  }

  private void showAddMessage (String message) {
    Toast.makeText (this,message,Toast.LENGTH_SHORT).show ();
    onBackPressed ();
  }
}
