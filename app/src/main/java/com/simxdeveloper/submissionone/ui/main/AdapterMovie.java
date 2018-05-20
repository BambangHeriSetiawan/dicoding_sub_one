package com.simxdeveloper.submissionone.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.simxdeveloper.submissionone.Apps;
import com.simxdeveloper.submissionone.BuildConfig;
import com.simxdeveloper.submissionone.R;
import com.simxdeveloper.submissionone.data.model.search.ResultsItem;
import com.simxdeveloper.submissionone.helper.Const;
import com.simxdeveloper.submissionone.ui.main.AdapterMovie.Holder;
import java.util.List;

/**
 * User: simx Date: 18/05/18 23:44
 */
public class AdapterMovie extends Adapter<Holder> {

  private MainPresenter presenter;
  private List<ResultsItem> resultsItems;

  public AdapterMovie (MainPresenter presenter,
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
    public Holder (View itemView) {
      super (itemView);
      ButterKnife.bind (this, itemView);
    }
  }
}
