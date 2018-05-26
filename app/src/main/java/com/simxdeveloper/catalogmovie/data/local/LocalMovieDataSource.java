package com.simxdeveloper.catalogmovie.data.local;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * User: simx Date: 26/05/18 18:39
 */
public class LocalMovieDataSource implements MovieDataSource{

  private MovieDAO movieDAO;

  public LocalMovieDataSource (MovieDAO movieDAO) {
    this.movieDAO = movieDAO;
  }

  @Override
  public Flowable<List<Movies>> getAll () {
    return movieDAO.getAll ().subscribeOn (Schedulers.newThread ()).observeOn (AndroidSchedulers.mainThread ());
  }

  @Override
  public Completable insert (Movies movies) {
    return Completable.fromAction (()-> movieDAO.inserData (movies)).subscribeOn (Schedulers.newThread ()).observeOn (AndroidSchedulers.mainThread ());
  }

  @Override
  public void deleteAll () {
    movieDAO.deleteData ();
  }
}
