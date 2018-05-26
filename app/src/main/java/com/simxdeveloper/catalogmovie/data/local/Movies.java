package com.simxdeveloper.catalogmovie.data.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * User: simx Date: 26/05/18 18:17
 */
@Entity(tableName = TableConst.TABLE_NAME_FAV)
public class Movies {
  @PrimaryKey
  private int id;
  @ColumnInfo(name = TableConst.COLUMN_OVERVIEW)
  private String overview;

  @ColumnInfo(name = TableConst.COLUMN_ORIGINAL_LANGUAGE)
  private String originalLanguage;

  @ColumnInfo(name = TableConst.COLUMN_ORIGINAL_TITLE)
  private String originalTitle;

  @ColumnInfo(name = TableConst.COLUMN_VIDEO)
  private boolean video;

  @ColumnInfo(name = TableConst.COLUMN_TITLE)
  private String title;

  @ColumnInfo(name = TableConst.COLUMN_POSTER_PATH)
  private String posterPath;

  @ColumnInfo(name = TableConst.COLUMN_BACKDROP_PATH)
  private String backdropPath;

  @ColumnInfo(name = TableConst.COLUMN_RELEASE_DATE)
  private String releaseDate;

  @ColumnInfo(name = TableConst.COLUMN_VOTE_AVARAGE)
  private double voteAverage;

  @ColumnInfo(name = TableConst.COLUMN_POPULARITY)
  private double popularity;

  @ColumnInfo(name = TableConst.COLUMN_ADULTH)
  private boolean adult;

  @ColumnInfo(name = TableConst.COLUMN_VOTE_COUNT)
  private int voteCount;

}
