package com.simxdeveloper.catalogmovie.ui.detail;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
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
import com.simxdeveloper.catalogmovie.data.local.MovieDatabaseHelper;
import com.simxdeveloper.catalogmovie.data.local.Movies;
import com.simxdeveloper.catalogmovie.data.local.TableConst;
import com.simxdeveloper.catalogmovie.data.provider.MovieContentProvider;
import com.simxdeveloper.catalogmovie.data.repo.model.global.ResultsItem;
import com.simxdeveloper.catalogmovie.helper.Const;


public class DetailActivity extends AppCompatActivity implements DetailPresenter {

  public static String KEY_DETAIL = "detail";
  public static String KEY_FAV = "fav";

  public static int REQUEST_ADD = 100;
  public static int RESULT_ADD = 101;
  public static int REQUEST_UPDATE = 200;
  public static int RESULT_UPDATE = 201;
  public static int RESULT_DELETE = 301;


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
  private boolean isFav;
  private Uri uri;
  private MovieDatabaseHelper helper;
  private Movies movies;

  public static void start(Context context, Uri uri, boolean fav) {
      Intent starter = new Intent(context, DetailActivity.class);
      starter.setData (uri);
      starter.putExtra (KEY_FAV,fav);
      context.startActivity(starter);
  }

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
    initActionbar ();
    presenter = new DetailPresenterImpl (this,this);
    if (getIntent ().getExtras () != null) {
      isFav = getIntent ().getExtras ().getBoolean (KEY_FAV);
      if (!isFav){
        detailMovie = getIntent ().getExtras ().getParcelable (KEY_DETAIL);
        initUi (detailMovie);
      }else {
        uri = getIntent().getData();
        Log.e ("DetailActivity", "onCreate: " + uri.toString ());
        presenter.cursorCheck (uri);
        if (uri != null) {
          Cursor cursor = getContentResolver().query(uri, null, null, null, null);
          if (cursor != null){
            if(cursor.moveToFirst()) movies = new Movies (cursor);
            cursor.close();
          }
        }
      }
    }
    helper = new MovieDatabaseHelper (this);
    helper.open();

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
  public void initFromCursor (Movies detailMovie) {
    Log.e ("DetailActivity", "initUi: " + detailMovie.toString ());
    getSupportActionBar ().setTitle (detailMovie.getTitle ());
    Glide.with (this).load (Const.IMAGE_PATH + detailMovie.getPosterPath ()).into (imgPoster);
    tvTitle.setText (detailMovie.getTitle ());
    tvTitileOri.setText (detailMovie.getOriginalTitle ());
    tvReleaseDate.setText (detailMovie.getReleaseDate ());
    tvOverview.setText (detailMovie.getOverview ());
    rating.setRating ((float) detailMovie.getVoteCount ());
  }

  @Override
  public void setIsFav (boolean isFav) {
    this.isFav = isFav;
  }

  private void initActionbar () {
    setSupportActionBar (toolbar);
    getSupportActionBar ().setDisplayShowTitleEnabled (true);
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
      case R.id.nav_share:
        shareMovie();
        break;
    }
    return super.onOptionsItemSelected (item);
  }

  private void shareMovie () {
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.setType("text/plain");
    intent.putExtra(Intent.EXTRA_TEXT, Const.IMAGE_PATH + detailMovie.getPosterPath ());
    intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out this movie!");
    startActivity(Intent.createChooser(intent, "Share"));
  }

  private void removeFromfavorite () {
    getApplicationContext ().getContentResolver ().delete (uri, null,null);
    showAddMessage ("Delete from Favorite");
  }

  private void addTofavorite () {
    ContentValues values = new ContentValues();
    values.put(TableConst.COLUMN_ID,detailMovie.getId ());
    values.put(TableConst.COLUMN_OVERVIEW,detailMovie.getOverview ());
    values.put(TableConst.COLUMN_ORIGINAL_LANGUAGE,detailMovie.getOriginalLanguage ());
    values.put(TableConst.COLUMN_ORIGINAL_TITLE,detailMovie.getOriginalTitle ());
    values.put(TableConst.COLUMN_TITLE,detailMovie.getTitle ());
    values.put(TableConst.COLUMN_POSTER_PATH,detailMovie.getPosterPath ());
    values.put(TableConst.COLUMN_BACKDROP_PATH,detailMovie.getBackdropPath ());
    values.put(TableConst.COLUMN_RELEASE_DATE,detailMovie.getReleaseDate ());
    values.put(TableConst.COLUMN_VOTE_AVARAGE,detailMovie.getVoteAverage ());
    values.put(TableConst.COLUMN_POPULARITY,detailMovie.getPopularity ());
    values.put(TableConst.COLUMN_VOTE_COUNT,detailMovie.getVoteCount ());
    getApplicationContext ().getContentResolver ().insert (TableConst.CONTENT_URI,values);
    showAddMessage ("Add to favorite");

  }

  private void showAddMessage (String message) {
    Toast.makeText (this,message,Toast.LENGTH_SHORT).show ();
    onBackPressed ();
  }

}
