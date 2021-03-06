package com.simxdeveloper.catalogmovie.data.repo;

import com.simxdeveloper.catalogmovie.BuildConfig;
import com.simxdeveloper.catalogmovie.data.repo.model.discover.ResponseDiscoverMovies;
import com.simxdeveloper.catalogmovie.data.repo.model.now_play.ResponseNowPlaying;
import com.simxdeveloper.catalogmovie.data.repo.model.search.ResponseSearchMovie;
import com.simxdeveloper.catalogmovie.data.repo.model.upcoming.ResponseUpcoming;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.Locale;

/**
 * User: simx Date: 18/05/18 22:41
 */
public class ObservableHelper {
  private static final String region = Locale.getDefault ().getCountry ();
  private static final String lang = Locale.getDefault ().getLanguage ();

  public static Observable<ResponseSearchMovie> searchMovieObservable (String query){
    return API.Factory.create ().getMovies (
        BuildConfig.ApiKey,
        query
    ).subscribeOn (Schedulers.newThread ()).observeOn (
        AndroidSchedulers.mainThread ());
  }
  public static Observable<ResponseDiscoverMovies> discoverMoviesObservable (String sort_by){
    return API.Factory.create ().getAllMovie (BuildConfig.ApiKey,sort_by).subscribeOn (Schedulers.newThread ()).observeOn (
        AndroidSchedulers.mainThread ());
  }
  public static Observable<ResponseNowPlaying> nowPlayingObservable (){
    return API.Factory.create ().getNowPlayingMovie (BuildConfig.ApiKey).subscribeOn (Schedulers.newThread ()).observeOn (
        AndroidSchedulers.mainThread ());
  }
  public static Observable<ResponseUpcoming> upcomingObservable (){
    return API.Factory.create ().getUpcomingMovie (BuildConfig.ApiKey).subscribeOn (Schedulers.newThread ()).observeOn (
        AndroidSchedulers.mainThread ());
  }
}
