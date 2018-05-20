package com.simxdeveloper.catalogmovie.data;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.simxdeveloper.catalogmovie.BuildConfig;
import com.simxdeveloper.catalogmovie.data.model.discover.ResponseDiscoverMovies;
import com.simxdeveloper.catalogmovie.data.model.now_play.ResponseNowPlaying;
import com.simxdeveloper.catalogmovie.data.model.search.ResponseSearchMovie;
import com.simxdeveloper.catalogmovie.data.model.upcoming.ResponseUpcoming;
import com.simxdeveloper.catalogmovie.helper.Const;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * User: simx Date: 18/05/18 22:03
 */
public interface API {

  /**
   * Search moview depend on query param
   * @param api_key
   * @param query
   * @return
   */
  @GET(Const.PATH_SEARCH)
  Observable<ResponseSearchMovie> getMovies(
      @Query ("api_key")String api_key,
      @Query ("language") String language,
      @Query ("query")String query
  );
  /**
   * Get All Movie discover
   * @param api_key
   * @param sort_by
   * @return
   */
  @GET(Const.PATH_DISCOVER)
  Observable<ResponseDiscoverMovies> getAllMovie(@Query ("api_key")String api_key,@Query ("sort_by")String sort_by);

  @GET(Const.PATH_UPCOMING)
  Observable<ResponseUpcoming> getUpcomingMovie(
      @Query ("api_key") String api_key,
      @Query ("language") String language

  );

  @GET(Const.PATH_NOW_PLAYING)
  Observable<ResponseNowPlaying> getNowPlayingMovie(
      @Query ("api_key") String api_key,
      @Query ("language") String language
  );

  /**
   * Facroty class to build retrofit
   */
  class Factory{

    /**
     * create class create to access retroift
     * @return
     */
    public static API create(){
      return getRetrofitConfig ().create (API.class);
    }

    /**
     * Config retrofilt
     * @return
     */
    public static Retrofit getRetrofitConfig() {
      return new Retrofit.Builder()
          .baseUrl(BuildConfig.BaseUrl)
          .addConverterFactory(GsonConverterFactory.create(getGson ()))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .client(client())
          .build();
    }

    /**
     * Config GSON
     * @return
     */
    public static Gson getGson(){
      GsonBuilder gsonBuilder = new GsonBuilder();
      gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
      return gsonBuilder.create ();
    }

    /**
     * Config OkhttpClient and interceptions
     * @return
     */
    private static OkHttpClient client() {
      return new OkHttpClient.Builder()
          .readTimeout(60, TimeUnit.SECONDS)
          .connectTimeout(60, TimeUnit.SECONDS)
          .addInterceptor(new HttpLoggingInterceptor ()
              .setLevel(HttpLoggingInterceptor.Level.BODY)
          )
          .build();
    }

  }
}
