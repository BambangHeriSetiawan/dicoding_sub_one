package com.simxdeveloper.catalogmovie.data.local;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import java.util.List;

/**
 * User: simx Date: 26/05/18 18:39
 */
public interface MovieDataSource {

  Flowable<List<Movies>> getAll ();

  Completable insert (Movies movies);

  void deleteAll ();
}
