package com.simxdeveloper.catalogmovie.ui.home.search;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.simxdeveloper.catalogmovie.Apps;
import com.simxdeveloper.catalogmovie.R;
import com.simxdeveloper.catalogmovie.data.repo.model.global.ResultsItem;
import com.simxdeveloper.catalogmovie.ui.detail.DetailActivity;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements SearchFragmentPresenter {


  @BindView(R.id.et_search)
  TextInputEditText etSearch;
  @BindView(R.id.rcv_movies)
  RecyclerView rcvMovies;
  Unbinder unbinder;

  public SearchFragment () {
  }

  private InputMethodManager imm;
  private SearchFragmentPresenterImpl presenter;
  private AdapterMovieSearch adapterMovieSearch;
  private View view;
  public static SearchFragment newInstance () {
    Bundle args = new Bundle ();
    SearchFragment fragment = new SearchFragment ();
    fragment.setArguments (args);
    return fragment;
  }

  @Override
  public View onCreateView (LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    view = inflater.inflate (R.layout.fragment_search, container, false);
    unbinder = ButterKnife.bind (this, view);
    presenter = new SearchFragmentPresenterImpl (this);
    imm = (InputMethodManager) Apps.getContext ().getSystemService (Apps.getContext ().INPUT_METHOD_SERVICE);
    adapterMovieSearch = new AdapterMovieSearch (this,new ArrayList<> ());
    return view;
  }

  @Override
  public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated (view, savedInstanceState);
    rcvMovies.setHasFixedSize (true);
    rcvMovies.setItemAnimator (new DefaultItemAnimator ());
    rcvMovies.setLayoutManager (new LinearLayoutManager (getContext (),LinearLayoutManager.VERTICAL,false));
    rcvMovies.setAdapter (adapterMovieSearch);
  }

  @Override
  public void initMovie (List<ResultsItem> resultsItems) {
    adapterMovieSearch.updateAdapter (resultsItems);
  }

  @Override
  public void showError (String message) {
    Toast.makeText (getContext (),message,Toast.LENGTH_SHORT).show ();
  }

  @Override
  public void onMovieClicked (ResultsItem resultsItem) {
    DetailActivity.start (getContext (), resultsItem);
  }

  @Override
  public void onDestroyView () {
    super.onDestroyView ();
    unbinder.unbind ();
  }

  @OnClick(R.id.btn_search)
  public void onViewClicked () {
    if (!TextUtils.isEmpty (etSearch.getText ().toString ())){
      presenter.getMovie(etSearch.getText ().toString ());
    }else {
      showError ("input is null");
      etSearch.setFocusable (true);
    }
    hiddenKeyboard (view);
  }
  private void hiddenKeyboard(View view){
    imm.hideSoftInputFromWindow (view.getWindowToken (), 0);
  }
}
