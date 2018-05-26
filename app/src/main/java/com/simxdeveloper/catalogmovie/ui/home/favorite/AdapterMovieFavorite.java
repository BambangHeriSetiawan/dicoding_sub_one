package com.simxdeveloper.catalogmovie.ui.home.favorite;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.simxdeveloper.catalogmovie.Apps;
import com.simxdeveloper.catalogmovie.R;
import com.simxdeveloper.catalogmovie.data.local.Movies;
import com.simxdeveloper.catalogmovie.data.repo.model.global.ResultsItem;
import com.simxdeveloper.catalogmovie.helper.Const;
import com.simxdeveloper.catalogmovie.ui.home.now_play.AdapterMovieNowPlaying.Holder;
import com.simxdeveloper.catalogmovie.ui.home.now_play.NowPlayingPresenter;
import java.util.List;

/**
 * User: simx Date: 18/05/18 23:44
 */
public class AdapterMovieFavorite extends Adapter<AdapterMovieFavorite.Holder> {

  private FavoritePresenter presenter;
  private List<Movies> resultsItems;

  public AdapterMovieFavorite (FavoritePresenter presenter,
      List<Movies> resultsItems) {
    this.presenter = presenter;
    this.resultsItems = resultsItems;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from (parent.getContext ())
        .inflate (R.layout.item_movie, parent, false);
    return new Holder (view);
  }

  @Override
  public void onBindViewHolder (@NonNull Holder holder, int position) {
    Movies movies = getItem (position);
    holder.tvTitle.setText (movies.getTitle ());
    holder.tvDesc.setText (movies.getOverview ());
    holder.tvDate.setText (movies.getReleaseDate ());
    Glide.with (Apps.getContext ()).load (Const.IMAGE_PATH + movies.getPosterPath ())
        .into (holder.imgMovie);
    holder.btnDetail.setOnClickListener (v -> {
      presenter.onMovieClicked (movies);
    });
    holder.btnShare.setOnClickListener (v -> {
      presenter.onMovieShare(movies);
    });
  }

  @Override
  public int getItemCount () {
    return resultsItems.size ();
  }

  private Movies getItem (int pos) {
    return resultsItems.get (pos);
  }

  public void updateAdapter (List<Movies> resultsItems) {
    this.resultsItems = resultsItems;
    notifyDataSetChanged ();
  }

  public class Holder extends ViewHolder {

    @BindView(R.id.img_movie)
    ImageView imgMovie;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.tv_date)
    TextView tvDate;

    @BindView(R.id.btn_share)
    Button btnShare;
    @BindView(R.id.btn_detail)
    Button btnDetail;

    public Holder (View itemView) {
      super (itemView);
      ButterKnife.bind (this, itemView);
    }
  }
}
