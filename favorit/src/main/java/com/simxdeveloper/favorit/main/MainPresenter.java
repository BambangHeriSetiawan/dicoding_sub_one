package com.simxdeveloper.favorit.main;

import com.simxdeveloper.favorit.data.Favorite; /**
 * User: simx Date: 29/05/18 18:42
 */
public interface MainPresenter {

  void onMovieClicked (Favorite favorite);

  void onMovieShare (Favorite favorite);
}
