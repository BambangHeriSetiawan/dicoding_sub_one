package com.simxdeveloper.favorit.data;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable.Creator;

/**
 * User: simx Date: 29/05/18 19:09
 */
public class Favorite implements android.os.Parcelable {
  public int id;
  private String overview;
  private String originalLanguage;
  private String originalTitle;
  private String title;
  private String posterPath;
  private String backdropPath;
  private String releaseDate;
  private double voteAverage;
  private double popularity;
  private int voteCount;

  public Favorite () {
  }

  public Favorite (int id, String overview, String originalLanguage, String originalTitle,
      String title, String posterPath, String backdropPath, String releaseDate, double voteAverage,
      double popularity, int voteCount) {
    this.id = id;
    this.overview = overview;
    this.originalLanguage = originalLanguage;
    this.originalTitle = originalTitle;
    this.title = title;
    this.posterPath = posterPath;
    this.backdropPath = backdropPath;
    this.releaseDate = releaseDate;
    this.voteAverage = voteAverage;
    this.popularity = popularity;
    this.voteCount = voteCount;
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

  public int getVoteCount () {
    return voteCount;
  }

  public void setVoteCount (int voteCount) {
    this.voteCount = voteCount;
  }

  @Override
  public int describeContents () {
    return 0;
  }

  @Override
  public void writeToParcel (Parcel dest, int flags) {
    dest.writeInt (this.id);
    dest.writeString (this.overview);
    dest.writeString (this.originalLanguage);
    dest.writeString (this.originalTitle);
    dest.writeString (this.title);
    dest.writeString (this.posterPath);
    dest.writeString (this.backdropPath);
    dest.writeString (this.releaseDate);
    dest.writeDouble (this.voteAverage);
    dest.writeDouble (this.popularity);
    dest.writeInt (this.voteCount);
  }

  protected Favorite (Parcel in) {
    this.id = in.readInt ();
    this.overview = in.readString ();
    this.originalLanguage = in.readString ();
    this.originalTitle = in.readString ();
    this.title = in.readString ();
    this.posterPath = in.readString ();
    this.backdropPath = in.readString ();
    this.releaseDate = in.readString ();
    this.voteAverage = in.readDouble ();
    this.popularity = in.readDouble ();
    this.voteCount = in.readInt ();
  }

  public static final Creator<Favorite> CREATOR = new Creator<Favorite> () {
    @Override
    public Favorite createFromParcel (Parcel source) {
      return new Favorite (source);
    }

    @Override
    public Favorite[] newArray (int size) {
      return new Favorite[size];
    }
  };
  public Favorite (Cursor cursor) {
    this.id = cursor.getInt (cursor.getColumnIndex (Const.COLUMN_ID));
    this.overview = cursor.getString (cursor.getColumnIndex (Const.COLUMN_OVERVIEW));
    this.originalLanguage = cursor.getString (cursor.getColumnIndex (Const.COLUMN_ORIGINAL_LANGUAGE));
    this.originalTitle = cursor.getString (cursor.getColumnIndex (Const.COLUMN_ORIGINAL_TITLE));
    this.title = cursor.getString (cursor.getColumnIndex (Const.COLUMN_TITLE));
    this.posterPath = cursor.getString (cursor.getColumnIndex (Const.COLUMN_POSTER_PATH));
    this.backdropPath = cursor.getString (cursor.getColumnIndex (Const.COLUMN_BACKDROP_PATH));
    this.releaseDate = cursor.getString (cursor.getColumnIndex (Const.COLUMN_RELEASE_DATE));
    this.voteAverage = cursor.getDouble (cursor.getColumnIndex (Const.COLUMN_VOTE_AVARAGE));
    this.popularity = cursor.getDouble (cursor.getColumnIndex (Const.COLUMN_POPULARITY));
    this.voteCount = cursor.getInt (cursor.getColumnIndex (Const.COLUMN_VOTE_COUNT));

  }
}
