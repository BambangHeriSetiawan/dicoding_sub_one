package com.simxdeveloper.catalogmovie.viewmodel;

import android.databinding.BaseObservable;

import com.simxdeveloper.catalogmovie.data.repo.model.global.ResultsItem;

import java.util.ArrayList;
import java.util.List;

public class ResutlsViewModel extends BaseObservable {
    private List<ResultsItem> resultsItem;

    public ResutlsViewModel() {
    }

    public ResutlsViewModel(List<ResultsItem> resultsItem) {
        this.resultsItem = new ArrayList<>();
    }

    public List<ResultsItem> getResultsItem() {
        return resultsItem;
    }

    public void setResultsItem(List<ResultsItem> resultsItem) {
        this.resultsItem = resultsItem;
    }
}
