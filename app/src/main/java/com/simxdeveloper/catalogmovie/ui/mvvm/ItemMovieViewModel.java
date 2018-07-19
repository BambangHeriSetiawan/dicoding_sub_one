package com.simxdeveloper.catalogmovie.ui.mvvm;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.simxdeveloper.catalogmovie.BuildConfig;
import com.simxdeveloper.catalogmovie.data.repo.model.global.ResultsItem;
import com.simxdeveloper.catalogmovie.helper.Const;

public class ItemMovieViewModel extends BaseObservable {
    private ResultsItem resultsItem;

    public ItemMovieViewModel(ResultsItem resultsItem) {
        this.resultsItem = resultsItem;
    }

    public void setResultsItem(ResultsItem resultsItem) {
        this.resultsItem = resultsItem;
    }

    public String titleMovie(){
        return resultsItem.getTitle();
    }

    public String originalDesc(){
        return resultsItem.getOverview();
    }
    public String releaseDate(){
        return resultsItem.getReleaseDate();
    }

    public  String imgUrl(){
        return resultsItem.getBackdropPath();
    }
    @BindingAdapter({"img_movie"})
    public static void loadImage(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(Const.IMAGE_PATH + url).into(imageView);
    }

    public void share(){
        Log.e("ItemMovieViewModel", "share: " + resultsItem.toString());
    }
    public void details(){
        Log.e("ItemMovieViewModel", "details: " + resultsItem.toString());
    }

    public void itemClick(){
        Log.e("ItemMovieViewModel", "itemClick: " + resultsItem.toString());
    }


}
