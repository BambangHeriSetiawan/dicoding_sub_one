package com.simxdeveloper.catalogmovie.preference;

/**
 * User: simx Date: 21/05/18 1:12
 */
public class GlobalPreference {
  private static final String CACHE_NAME = GlobalPreference.class.getName ();
  private static CachePreference cachePreference;
  private static CachePreference getInstance(){
    if(cachePreference == null) cachePreference = new CachePreference (CACHE_NAME);
    return cachePreference;
  }
  public static synchronized <T> T read(String key, Class<T> tClass) {
    return getInstance().read(key, tClass);
  }

  public static synchronized <T> void write(String key, T value, Class<T> tClass) {
    getInstance().write(key, value, tClass);
  }

  public static synchronized void clear() {
    getInstance().clear();
  }

}
