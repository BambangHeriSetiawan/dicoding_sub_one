package com.simxdeveloper.catalogmovie.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * User: simx Date: 26/05/18 18:34
 */
@Database (entities = {Movies.class},exportSchema = false,version = TableConst.DATABASE_VERSION)
public abstract class AppDatabases extends RoomDatabase{
  private static AppDatabases INSTANCE;
  public abstract MovieDAO movieDAO();
  public static AppDatabases getINSTANCE(Context context){
    if (INSTANCE == null) {
      synchronized (AppDatabases.class){
        INSTANCE = Room.databaseBuilder (
            context.getApplicationContext (),
            AppDatabases.class,
            TableConst.DATABASE_NAME
        )
            .allowMainThreadQueries ()
            .build ();
      }
    }
    return INSTANCE;
  }

  public void destroyInstance(){
    INSTANCE = null;
  }

}
