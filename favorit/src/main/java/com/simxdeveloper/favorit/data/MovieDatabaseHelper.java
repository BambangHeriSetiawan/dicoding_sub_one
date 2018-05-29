package com.simxdeveloper.favorit.data;

import static com.simxdeveloper.favorit.data.Const.COLUMN_ORIGINAL_TITLE;
import static com.simxdeveloper.favorit.data.Const.COLUMN_OVERVIEW;
import static com.simxdeveloper.favorit.data.Const.COLUMN_TITLE;
import static com.simxdeveloper.favorit.data.Const.DATABASE_NAME;
import static com.simxdeveloper.favorit.data.Const.TABLE_NAME_FAV;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * User: simx Date: 29/05/18 19:12
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
  public long insert(Favorite favorite){
    ContentValues initialValues =  new ContentValues();
    initialValues.put(COLUMN_TITLE, favorite.getTitle ());
    initialValues.put(COLUMN_ORIGINAL_TITLE, favorite.getOriginalTitle ());
    initialValues.put(COLUMN_OVERVIEW, favorite.getOverview ());
    return database.insert(DATABASE_NAME, null, initialValues);
  }

  public Cursor queryByIdProvider(String id){
    return database.query(TABLE_NAME_FAV,null
        ,Const.COLUMN_ID + " = ?"
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
        ,Const.COLUMN_ID + " DESC");
  }
  public long insertProvider(ContentValues values){
    return database.insert(TABLE_NAME_FAV,null,values);
  }

  public int updateProvider(String id,ContentValues values){
    return database.update(TABLE_NAME_FAV,values,Const.COLUMN_ID +" = ?",new String[]{id} );
  }
  public int deleteProvider(String id){
    return database.delete(TABLE_NAME_FAV,Const.COLUMN_ID + " = ?", new String[]{id});
  }
}
