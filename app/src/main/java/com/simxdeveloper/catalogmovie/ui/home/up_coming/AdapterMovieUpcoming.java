package com.simxdeveloper.catalogmovie.ui.home.up_coming;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
import com.simxdeveloper.catalogmovie.data.model.global.ResultsItem;
import com.simxdeveloper.catalogmovie.helper.Const;
import com.simxdeveloper.catalogmovie.ui.main.MainPresenter;
import java.util.List;

/**
 * User: simx Date: 18/05/18 23:44
 */
public class AdapterMovieUpcoming extends RecyclerView.Adapter<AdapterMovieUpcoming.Holder> {

  private UpcomingFragmentPresenter presenter;
  private List<ResultsItem> resultsItems;

  public AdapterMovieUpcoming (UpcomingFragmentPresenter presenter,
      List<ResultsItem> resultsItems) {
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
    ResultsItem resultsItem = getItem (position);
    holder.tvTitle.setText (resultsItem.getTitle ());
    holder.tvDesc.setText (resultsItem.getOverview ());
    holder.tvDate.setText (resultsItem.getReleaseDate ());
    Glide.with (Apps.getContext ()).load (Const.IMAGE_PATH + resultsItem.getPosterPath ()).into (holder.imgMovie);
    holder.itemView.setOnClickListener (v -> {
      presenter.onMovieClicked(resultsItem);
    });
  }

  @Override
  public int getItemCount () {
    return resultsItems.size ();
  }

  private ResultsItem getItem (int pos) {
    return resultsItems.get (pos);
  }

  public void updateAdapter (List<ResultsItem> resultsItems) {
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
