package com.simxdeveloper.submissionone.data;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.simxdeveloper.submissionone.BuildConfig;
import com.simxdeveloper.submissionone.data.model.search.ResponseSearchMovie;
import com.simxdeveloper.submissionone.helper.Const;
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
  @GET(Const.PATH_SEARCH)
  Observable<ResponseSearchMovie> getMovies(@Query ("api_key")String api_key,@Query ("query")String query);

  @GET(Const.PATH_DISCOVER)
  Observable<ResponseSearchMovie> getAllMovie(@Query ("api_key")String api_key,@Query ("sort_by")String sort_by);

  class Factory{
    public static API create(){
      return getRetrofitConfig ().create (API.class);
    }

    public static Retrofit getRetrofitConfig() {
      return new Retrofit.Builder()
          .baseUrl(BuildConfig.BaseUrl)
          .addConverterFactory(GsonConverterFactory.create(getGson ()))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .client(client())
          .build();
    }
    public static Gson getGson(){
      GsonBuilder gsonBuilder = new GsonBuilder();
      gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
      return gsonBuilder.create ();
    }
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
