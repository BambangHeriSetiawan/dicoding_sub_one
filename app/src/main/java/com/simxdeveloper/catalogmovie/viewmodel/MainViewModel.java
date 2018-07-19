package com.simxdeveloper.catalogmovie.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import com.simxdeveloper.catalogmovie.data.repo.ObservableHelper;
import com.simxdeveloper.catalogmovie.data.repo.model.global.ResultsItem;
import com.simxdeveloper.catalogmovie.ui.main.AdapterMovie;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    public AdapterMovie adapterMovie;
    private ResutlsViewModel resutlsViewModel;
    public MainViewModel() {
    }

    public MainViewModel(AdapterMovie adapterMovie) {
        this.adapterMovie = adapterMovie;
        resutlsViewModel = new ResutlsViewModel();
        getMovie();
    }

    public void getMovie() {
        ObservableHelper.upcomingObservable().subscribe(
                responseUpcoming -> {
                }
                ,throwable -> {
                    Log.e("MainViewModel", "getMovie: ");
                });
    }

}
