package com.simxdeveloper.favorit.detail;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.simxdeveloper.favorit.R;
import com.simxdeveloper.favorit.data.Const;
import com.simxdeveloper.favorit.data.Favorite;
import com.simxdeveloper.favorit.data.MovieDatabaseHelper;

public class DetailFavActivity extends AppCompatActivity {

  private static String KEY_DATA = "detail";
  private Uri uri;
  private Favorite favorit;
  private MovieDatabaseHelper helper;

  public static void start (Context context, Uri uri) {
    Intent starter = new Intent (context, DetailFavActivity.class);
    starter.setData (uri);
    context.startActivity (starter);
  }

  @Override
  protected void onCreate (Bundle savedInstanceState) {
    super.onCreate (savedInstanceState);
    setContentView (R.layout.activity_detail_fav);
    initActionbar ();
    uri = getIntent ().getData ();
    if (uri != null) {
      Cursor cursor = getContentResolver ().query (uri, null, null, null, null);
      if (cursor != null) {
        if (cursor.moveToFirst ()) {
          favorit = new Favorite (cursor);
        }
        initUi(favorit);
        cursor.close ();
      }
    }
    helper = new MovieDatabaseHelper (this);
    helper.open ();
  }

  private void initUi (Favorite favorit) {
    Log.e ("DetailFavActivity", "initUi: " + favorit);
    Glide.with (getApplicationContext ()).load (Const.IMAGE_PATH + favorit.getPosterPath ()).into (
        ((ImageView)findViewById (R.id.img_poster))
    );

    ((TextView)findViewById (R.id.tv_title_fav)).setText (favorit.getOriginalTitle ());
    ((TextView)findViewById (R.id.tv_titile_ori_fav)).setText (favorit.getOriginalTitle ());
    ((TextView)findViewById (R.id.tv_release_date_fav)).setText (favorit.getReleaseDate ());
    ((TextView)findViewById (R.id.tv_overview_fav)).setText (favorit.getOverview ());
    ((RatingBar)findViewById (R.id.rating_fav)).setRating ((float) favorit.getVoteAverage ());
    getSupportActionBar ().setTitle (favorit.getTitle ());
  }

  private void initActionbar () {
    setSupportActionBar (findViewById (R.id.toolbar_fav));
    getSupportActionBar ().setDisplayShowTitleEnabled (true);
    getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
    getSupportActionBar ().setHomeAsUpIndicator (R.drawable.ic_arrow_back_black_24dp);
  }

  @Override
  public boolean onOptionsItemSelected (MenuItem item) {
    int i = item.getItemId ();
    if (i == android.R.id.home) {
      onBackPressed ();
    } else if (i == R.id.nav_fav) {
      removeFromfavorite ();
    } else if (i == R.id.nav_share_fav) {
      shareMovie ();
    }
    return super.onOptionsItemSelected (item);
  }

  private void shareMovie () {
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.setType("text/plain");
    intent.putExtra(Intent.EXTRA_TEXT, Const.IMAGE_PATH + favorit.getPosterPath ());
    intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Check out this movie!");
    startActivity(Intent.createChooser(intent, "Share"));
  }

  private void removeFromfavorite () {
    getApplicationContext ().getContentResolver ().delete (uri, null,null);
    showAddMessage ("Delete from Favorite");
  }

  private void showAddMessage (String message) {
    Toast.makeText (this,message,Toast.LENGTH_SHORT).show ();
    onBackPressed ();
  }

  @Override
  public boolean onCreateOptionsMenu (Menu menu) {
    getMenuInflater ().inflate (R.menu.detail_fav_menu,menu);
    return super.onCreateOptionsMenu (menu);
  }
}
