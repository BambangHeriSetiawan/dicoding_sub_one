package com.simxdeveloper.catalogmovie.data;

import com.simxdeveloper.catalogmovie.BuildConfig;
import com.simxdeveloper.catalogmovie.data.model.discover.ResponseDiscoverMovies;
import com.simxdeveloper.catalogmovie.data.model.search.ResponseSearchMovie;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * User: simx Date: 18/05/18 22:41
 */
public class ObservableHelper {
  public static Observable<ResponseSearchMovie> searchMovieObservable (String query){
    return API.Factory.create ().getMovies (BuildConfig.ApiKey,query).subscribeOn (Schedulers.newThread ()).observeOn (
        AndroidSchedulers.mainThread ());
  }
  public static Observable<ResponseDiscoverMovies> discoverMoviesObservable (String sort_by){
    return API.Factory.create ().getAllMovie (BuildConfig.ApiKey,sort_by).subscribeOn (Schedulers.newThread ()).observeOn (
        AndroidSchedulers.mainThread ());
  }
}
