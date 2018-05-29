package com.simxdeveloper.favorit.main;

import android.content.Context;
import android.database.Cursor;
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
import com.simxdeveloper.favorit.R;
import com.simxdeveloper.favorit.data.Const;
import com.simxdeveloper.favorit.data.Favorite;
import com.simxdeveloper.favorit.main.AdapterFavoriteMovie.Holder;

/**
 * User: simx Date: 18/05/18 23:44
 */
public class AdapterFavoriteMovie extends Adapter<Holder> {


  private MainPresenter presenter;
  private Context context;
  private Cursor listMovie;

  public void setListMovie (Cursor listMovie) {
    this.listMovie = listMovie;
  }

  public AdapterFavoriteMovie (MainPresenter presenter, Context context) {
    this.presenter = presenter;
    this.context = context;
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
    Favorite movies = getItem (position);
    holder.tvTitle.setText (movies.getTitle ());
    holder.tvDesc.setText (movies.getOverview ());
    holder.tvDate.setText (movies.getReleaseDate ());
    Glide.with (context).load (Const.IMAGE_PATH + movies.getPosterPath ())
        .into (holder.imgMovie);
    holder.btnDetail.setOnClickListener (v -> {
      presenter.onMovieClicked (movies);
    });
    holder.btnShare.setOnClickListener (v -> {
      presenter.onMovieShare (movies);
    });

  }

  @Override
  public int getItemCount () {
    return listMovie == null ? 0 : listMovie.getCount ();
  }

  private Favorite getItem (int pos) {
    if (!listMovie.moveToPosition (pos)) {
      throw new IllegalStateException ("Possition Invalid");
    }
    return new Favorite (listMovie);
  }

  public void updateAdapter (Cursor cursor) {
    this.listMovie = cursor;
    notifyDataSetChanged ();
  }

  public class Holder extends ViewHolder {
    ImageView imgMovie;
    TextView tvTitle;
    TextView tvDesc;
    TextView tvDate;
    Button btnShare;
    Button btnDetail;

    public Holder (View itemView) {
      super (itemView);
      imgMovie = itemView.findViewById (R.id.img_movie);
      tvTitle = itemView.findViewById (R.id.tv_title);
      tvDesc = itemView.findViewById (R.id.tv_desc);
      tvDate = itemView.findViewById (R.id.tv_date);
      btnDetail = itemView.findViewById (R.id.btn_detail);
      btnShare = itemView.findViewById (R.id.btn_share);

    }
  }
}
