package com.simxdeveloper.catalogmovie.data;

import com.simxdeveloper.catalogmovie.BuildConfig;
import com.simxdeveloper.catalogmovie.data.model.discover.ResponseDiscoverMovies;
import com.simxdeveloper.catalogmovie.data.model.now_play.ResponseNowPlaying;
import com.simxdeveloper.catalogmovie.data.model.search.ResponseSearchMovie;
import com.simxdeveloper.catalogmovie.data.model.upcoming.ResponseUpcoming;
import com.simxdeveloper.catalogmovie.preference.GlobalPreference;
import com.simxdeveloper.catalogmovie.preference.PrefKey;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * User: simx Date: 18/05/18 22:41
 */
public class ObservableHelper {
  public static Observable<ResponseSearchMovie> searchMovieObservable (String query){
    return API.Factory.create ().getMovies (BuildConfig.ApiKey,query, GlobalPreference.read (PrefKey.LANGUAGE,String.class)).subscribeOn (Schedulers.newThread ()).observeOn (
        AndroidSchedulers.mainThread ());
  }
  public static Observable<ResponseDiscoverMovies> discoverMoviesObservable (String sort_by){
    return API.Factory.create ().getAllMovie (BuildConfig.ApiKey,sort_by).subscribeOn (Schedulers.newThread ()).observeOn (
        AndroidSchedulers.mainThread ());
  }
  public static Observable<ResponseNowPlaying> nowPlayingObservable (){
    return API.Factory.create ().getNowPlayingMovie (BuildConfig.ApiKey,GlobalPreference.read (PrefKey.LANGUAGE,String.class)).subscribeOn (Schedulers.newThread ()).observeOn (
        AndroidSchedulers.mainThread ());
  }
  public static Observable<ResponseUpcoming> upcomingObservable (){
    return API.Factory.create ().getUpcomingMovie (BuildConfig.ApiKey,GlobalPreference.read (PrefKey.LANGUAGE,String.class)).subscribeOn (Schedulers.newThread ()).observeOn (
        AndroidSchedulers.mainThread ());
  }
}
