package com.simxdeveloper.submissionone.data;

import com.simxdeveloper.submissionone.BuildConfig;
import com.simxdeveloper.submissionone.data.model.ResponseSearchMovie;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * User: simx Date: 18/05/18 22:41
 */
public class ObservableHelper {
  public Observable<ResponseSearchMovie> searchMovieObservable (String query){
    return API.Factory.create ().getMovies (BuildConfig.ApiKey,query).subscribeOn (Schedulers.newThread ()).observeOn (
        AndroidSchedulers.mainThread ());
  }
}
