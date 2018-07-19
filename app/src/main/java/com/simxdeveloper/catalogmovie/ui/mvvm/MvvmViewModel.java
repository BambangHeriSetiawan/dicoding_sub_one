package com.simxdeveloper.catalogmovie.ui.mvvm;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.simxdeveloper.catalogmovie.data.repo.ObservableHelper;
import com.simxdeveloper.catalogmovie.data.repo.model.global.ResultsItem;
import com.simxdeveloper.catalogmovie.data.repo.model.upcoming.ResponseUpcoming;

import java.util.List;

import io.reactivex.Observable;

public class MvvmViewModel extends ViewModel {
    private MutableLiveData<List<ResultsItem>> resultsItemMutableLiveData;

    public MvvmViewModel() {
    }

    public Observable<ResponseUpcoming> setResultsItemMutableLiveData() {
       return ObservableHelper.upcomingObservable();
    }
}
