package com.simxdeveloper.catalogmovie.data.local;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import io.reactivex.Flowable;
import java.util.List;

/**
 * User: simx Date: 26/05/18 18:31
 */
@Dao
public interface MovieDAO {
  @Query("SELECT * FROM movie_favorit")
  Flowable<List<Movies>> getAll();

  @Insert(onConflict = REPLACE)
  List<Long> insertAll (List<Movies> wordsEngIndos);

  @Insert
  void inserData(Movies...movies);

  @Delete
  void deleteData(Movies...movies);
}
