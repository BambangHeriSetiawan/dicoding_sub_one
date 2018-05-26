package com.simxdeveloper.catalogmovie.data.local;

import static android.provider.BaseColumns._ID;
import static com.simxdeveloper.catalogmovie.data.local.TableConst.COLUMN_ORIGINAL_TITLE;
import static com.simxdeveloper.catalogmovie.data.local.TableConst.COLUMN_OVERVIEW;
import static com.simxdeveloper.catalogmovie.data.local.TableConst.COLUMN_TITLE;
import static com.simxdeveloper.catalogmovie.data.local.TableConst.DATABASE_NAME;
import static com.simxdeveloper.catalogmovie.data.local.TableConst.TABLE_NAME_FAV;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * User: simx Date: 27/05/18 3:23
 */
public class MovieDatabaseHelper {
  private Context context;
  private DatabaseHelper dataBaseHelper;
  private SQLiteDatabase database;

  public MovieDatabaseHelper (Context context) {
    this.context = context;
  }
  public MovieDatabaseHelper open() throws SQLException {
    dataBaseHelper = new DatabaseHelper(context);
    database = dataBaseHelper.getWritableDatabase();
    return this;
  }

  public void close(){
    dataBaseHelper.close();
  }
  public long insert(Movies movies){
    ContentValues initialValues =  new ContentValues();
    initialValues.put(COLUMN_TITLE, movies.getTitle ());
    initialValues.put(COLUMN_ORIGINAL_TITLE, movies.getOriginalTitle ());
    initialValues.put(COLUMN_OVERVIEW, movies.getOverview ());
    return database.insert(DATABASE_NAME, null, initialValues);
  }

  public Cursor queryByIdProvider(String id){
    return database.query(TABLE_NAME_FAV,null
        ,TableConst.COLUMN_ID + " = ?"
        ,new String[]{id}
        ,null
        ,null
        ,null
        ,null);
  }
  public Cursor queryProvider(){
    return database.query(TABLE_NAME_FAV
        ,null
        ,null
        ,null
        ,null
        ,null
        ,TableConst.COLUMN_ID + " DESC");
  }
  public long insertProvider(ContentValues values){
    return database.insert(TABLE_NAME_FAV,null,values);
  }

  public int updateProvider(String id,ContentValues values){
    return database.update(TABLE_NAME_FAV,values,TableConst.COLUMN_ID +" = ?",new String[]{id} );
  }
  public int deleteProvider(String id){
    return database.delete(TABLE_NAME_FAV,TableConst.COLUMN_ID + " = ?", new String[]{id});
  }

}
