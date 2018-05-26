package com.simxdeveloper.catalogmovie.data.local;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;

/**
 * User: simx Date: 26/05/18 18:39
 */
public interface MovieDataSource {

  Flowable<List<Movies>> getAll ();

  Flowable<Movies> getMovieById(int id);

  Completable insert (Movies movies);



  void deleteAll (Movies movies);
}
