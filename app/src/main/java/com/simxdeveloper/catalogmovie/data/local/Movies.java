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

  public Movies () {
  }

  public int getId () {
    return id;
  }

  public void setId (int id) {
    this.id = id;
  }

  public String getOverview () {
    return overview;
  }

  public void setOverview (String overview) {
    this.overview = overview;
  }

  public String getOriginalLanguage () {
    return originalLanguage;
  }

  public void setOriginalLanguage (String originalLanguage) {
    this.originalLanguage = originalLanguage;
  }

  public String getOriginalTitle () {
    return originalTitle;
  }

  public void setOriginalTitle (String originalTitle) {
    this.originalTitle = originalTitle;
  }

  public boolean isVideo () {
    return video;
  }

  public void setVideo (boolean video) {
    this.video = video;
  }

  public String getTitle () {
    return title;
  }

  public void setTitle (String title) {
    this.title = title;
  }

  public String getPosterPath () {
    return posterPath;
  }

  public void setPosterPath (String posterPath) {
    this.posterPath = posterPath;
  }

  public String getBackdropPath () {
    return backdropPath;
  }

  public void setBackdropPath (String backdropPath) {
    this.backdropPath = backdropPath;
  }

  public String getReleaseDate () {
    return releaseDate;
  }

  public void setReleaseDate (String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public double getVoteAverage () {
    return voteAverage;
  }

  public void setVoteAverage (double voteAverage) {
    this.voteAverage = voteAverage;
  }

  public double getPopularity () {
    return popularity;
  }

  public void setPopularity (double popularity) {
    this.popularity = popularity;
  }

  public boolean isAdult () {
    return adult;
  }

  public void setAdult (boolean adult) {
    this.adult = adult;
  }

  public int getVoteCount () {
    return voteCount;
  }

  public void setVoteCount (int voteCount) {
    this.voteCount = voteCount;
  }

  @Override
  public String toString () {
    return "Movies{" +
        "id=" + id +
        ", overview='" + overview + '\'' +
        ", originalLanguage='" + originalLanguage + '\'' +
        ", originalTitle='" + originalTitle + '\'' +
        ", video=" + video +
        ", title='" + title + '\'' +
        ", posterPath='" + posterPath + '\'' +
        ", backdropPath='" + backdropPath + '\'' +
        ", releaseDate='" + releaseDate + '\'' +
        ", voteAverage=" + voteAverage +
        ", popularity=" + popularity +
        ", adult=" + adult +
        ", voteCount=" + voteCount +
        '}';
  }
}
