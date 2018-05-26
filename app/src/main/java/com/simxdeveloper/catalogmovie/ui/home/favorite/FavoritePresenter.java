package com.simxdeveloper.catalogmovie.ui.home.favorite;

import com.simxdeveloper.catalogmovie.data.local.Movies;
import java.util.List; /**
 * User: simx Date: 26/05/18 18:47
 */
public interface FavoritePresenter {

  void onMovieClicked (Movies movies);

  void onMovieShare (Movies movies);

  void initDataMovie (List<Movies> movies);

  void showError (String message);
}
