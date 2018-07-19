package com.simxdeveloper.catalogmovie.ui.mvvm;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.simxdeveloper.catalogmovie.R;
import com.simxdeveloper.catalogmovie.databinding.ActivityMvvMBinding;
import com.simxdeveloper.catalogmovie.ui.main.AdapterMovie;

import java.util.ArrayList;

public class MvvMActivity extends AppCompatActivity {
    ActivityMvvMBinding binding;
    MvvmViewModel mvvmViewModel;
    private AdapterMovie adapterMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_mvv_m);
        mvvmViewModel  = ViewModelProviders.of(this).get(MvvmViewModel.class);
        binding.setVm(mvvmViewModel);
        adapterMovie = new AdapterMovie(this,new ArrayList<>());
        mvvmViewModel.setResultsItemMutableLiveData();
        binding.rcvMovies.setHasFixedSize(true);
        binding.rcvMovies.setItemAnimator(new DefaultItemAnimator());
        binding.rcvMovies.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvMovies.setAdapter(adapterMovie);
        mvvmViewModel.setResultsItemMutableLiveData().subscribe(responseUpcoming -> {
            adapterMovie.setResultsItems(responseUpcoming.getResults());
        },throwable -> {
            Log.e("MvvMActivity", "onCreate: " + throwable.getMessage());
        });


    }
}
