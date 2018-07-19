package com.simxdeveloper.catalogmovie.ui.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.simxdeveloper.catalogmovie.R;
import com.simxdeveloper.catalogmovie.data.repo.model.global.ResultsItem;
import com.simxdeveloper.catalogmovie.databinding.ItemMovieBinding;
import com.simxdeveloper.catalogmovie.ui.mvvm.ItemMovieViewModel;

import java.util.List;

/**
 * User: simx Date: 18/05/18 23:44
 */
public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.BindingHolder> {

  private Context context;
  private List<ResultsItem> resultsItems;
  private ItemMovieViewModel itemMovieViewModel;
  public AdapterMovie(Context context, List<ResultsItem> resultsItems) {
    this.context = context;
    this.resultsItems = resultsItems;
  }

  @NonNull
  @Override
  public BindingHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
    ItemMovieBinding itemMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.getContext()),
            R.layout.item_movie,
            parent,
            false
    );
    return new BindingHolder(itemMovieBinding);
  }

  @Override
  public void onBindViewHolder (@NonNull BindingHolder holder, int position) {
    itemMovieViewModel = new ItemMovieViewModel(getItem(position));
    holder.itemMovieBinding.setItem(itemMovieViewModel);
    //holder.bind(getItem(position));
  }

  @Override
  public int getItemCount () {
    return resultsItems.size ();
  }

  private ResultsItem getItem (int pos) {
    return resultsItems.get (pos);
  }

  public void setResultsItems(List<ResultsItem> resultsItems) {
    this.resultsItems = resultsItems;
    notifyDataSetChanged();
  }
  public void addResultItem(ResultsItem resultsItem){
    this.resultsItems.add(resultsItem);
    notifyDataSetChanged();
  }

  public class BindingHolder extends RecyclerView.ViewHolder {
    private  ItemMovieBinding itemMovieBinding;
    public BindingHolder(ItemMovieBinding itemMovieBinding) {
      super(itemMovieBinding.getRoot());
      this.itemMovieBinding  = itemMovieBinding;
    }

    public void bind(ResultsItem item) {
      itemMovieBinding.setItem(itemMovieViewModel);
      itemMovieBinding.executePendingBindings();
    }
  }
}
