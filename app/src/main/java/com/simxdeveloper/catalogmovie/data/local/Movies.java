package com.simxdeveloper.catalogmovie.data.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.provider.BaseColumns;

/**
 * User: simx Date: 26/05/18 18:17
 */
@Entity(tableName = TableConst.TABLE_NAME_FAV)
public class Movies implements android.os.Parcelable {

  @PrimaryKey
  public int id;
  @ColumnInfo(name = TableConst.COLUMN_OVERVIEW)
  private String overview;

  @ColumnInfo(name = TableConst.COLUMN_ORIGINAL_LANGUAGE)
  private String originalLanguage;

  @ColumnInfo(name = TableConst.COLUMN_ORIGINAL_TITLE)
  private String originalTitle;

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

  public static Movies fromContentValues(ContentValues values) {
    final Movies movies = new Movies ();
    if (values.containsKey(TableConst.COLUMN_ID)) {
      movies.id = values.getAsInteger (TableConst.COLUMN_ID);
    }
    if (values.containsKey(TableConst.COLUMN_OVERVIEW)) {
      movies.overview = values.getAsString(TableConst.COLUMN_OVERVIEW);
    }
    if (values.containsKey(TableConst.COLUMN_ORIGINAL_LANGUAGE)) {
      movies.originalLanguage = values.getAsString(TableConst.COLUMN_ORIGINAL_LANGUAGE);
    }
    if (values.containsKey(TableConst.COLUMN_ORIGINAL_TITLE)) {
      movies.originalTitle = values.getAsString(TableConst.COLUMN_ORIGINAL_TITLE);
    }
    if (values.containsKey(TableConst.COLUMN_TITLE)) {
      movies.title = values.getAsString(TableConst.COLUMN_TITLE);
    }
    if (values.containsKey(TableConst.COLUMN_POSTER_PATH)) {
      movies.posterPath = values.getAsString (TableConst.COLUMN_POSTER_PATH);
    }
    if (values.containsKey(TableConst.COLUMN_BACKDROP_PATH)) {
      movies.backdropPath = values.getAsString (TableConst.COLUMN_BACKDROP_PATH);
    }
    if (values.containsKey(TableConst.COLUMN_RELEASE_DATE)) {
      movies.releaseDate = values.getAsString (TableConst.COLUMN_RELEASE_DATE);
    }
    if (values.containsKey(TableConst.COLUMN_VOTE_AVARAGE)) {
      movies.voteAverage = values.getAsDouble (TableConst.COLUMN_VOTE_AVARAGE);
    }
    if (values.containsKey(TableConst.COLUMN_POPULARITY)) {
      movies.popularity = values.getAsDouble (TableConst.COLUMN_POPULARITY);
    }
    if (values.containsKey(TableConst.COLUMN_VOTE_COUNT)) {
      movies.voteCount = values.getAsInteger (TableConst.COLUMN_VOTE_COUNT);
    }
    return movies;
  }


  public Movies (Cursor cursor) {
    this.id = cursor.getInt (cursor.getColumnIndex (TableConst.COLUMN_ID));
    this.overview = cursor.getString (cursor.getColumnIndex (TableConst.COLUMN_OVERVIEW));
    this.originalLanguage = cursor.getString (cursor.getColumnIndex (TableConst.COLUMN_ORIGINAL_LANGUAGE));
    this.originalTitle = cursor.getString (cursor.getColumnIndex (TableConst.COLUMN_ORIGINAL_TITLE));
    this.title = cursor.getString (cursor.getColumnIndex (TableConst.COLUMN_TITLE));
    this.posterPath = cursor.getString (cursor.getColumnIndex (TableConst.COLUMN_POSTER_PATH));
    this.backdropPath = cursor.getString (cursor.getColumnIndex (TableConst.COLUMN_BACKDROP_PATH));
    this.releaseDate = cursor.getString (cursor.getColumnIndex (TableConst.COLUMN_RELEASE_DATE));
    this.voteAverage = cursor.getDouble (cursor.getColumnIndex (TableConst.COLUMN_VOTE_AVARAGE));
    this.popularity = cursor.getDouble (cursor.getColumnIndex (TableConst.COLUMN_POPULARITY));
    this.voteCount = cursor.getInt (cursor.getColumnIndex (TableConst.COLUMN_VOTE_COUNT));

  }

  @Override
  public String toString () {
    return "Movies{" +
        "id=" + id +
        ", overview='" + overview + '\'' +
        ", originalLanguage='" + originalLanguage + '\'' +
        ", originalTitle='" + originalTitle + '\'' +
        ", title='" + title + '\'' +
        ", posterPath='" + posterPath + '\'' +
        ", backdropPath='" + backdropPath + '\'' +
        ", releaseDate='" + releaseDate + '\'' +
        ", voteAverage=" + voteAverage +
        ", popularity=" + popularity +
        ", voteCount=" + voteCount +
        '}';
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

  protected Movies (Parcel in) {
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

  public static final Creator<Movies> CREATOR = new Creator<Movies> () {
    @Override
    public Movies createFromParcel (Parcel source) {
      return new Movies (source);
    }

    @Override
    public Movies[] newArray (int size) {
      return new Movies[size];
    }
  };
}
