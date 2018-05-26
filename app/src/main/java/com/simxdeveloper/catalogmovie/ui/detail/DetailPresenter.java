package com.simxdeveloper.catalogmovie.ui.detail;

import com.simxdeveloper.catalogmovie.data.local.Movies; /**
 * User: simx Date: 20/05/18 14:41
 */
public interface DetailPresenter {

  void setIsFav (boolean isFav);

  void initFromCursor (Movies movies);
}
