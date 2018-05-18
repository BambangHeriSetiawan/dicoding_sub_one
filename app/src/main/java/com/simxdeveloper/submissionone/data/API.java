package com.simxdeveloper.submissionone.data;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.simxdeveloper.submissionone.data.model.ResponseSearchMovie;
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
  @GET("province")
  Observable<ResponseSearchMovie> getMovies(@Query ("api_key")String api_key,@Query ("query")String query);

  class Factory{
    public static API create(String base_url){
      return getRetrofitConfig (base_url).create (API.class);
    }

    public static Retrofit getRetrofitConfig(String base_url) {
      return new Retrofit.Builder()
          .baseUrl(base_url)
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
