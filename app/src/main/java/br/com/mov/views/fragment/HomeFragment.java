package br.com.mov.views.fragment;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import br.com.mov.R;
import br.com.mov.models.Movie;
import br.com.mov.models.Slide;
import br.com.mov.views.recyclerview.adapter.MovieAdapter;
import br.com.mov.views.slidepager.adapter.SlidePagerAdapter;

public class HomeFragment extends Fragment {

    private List<Slide> slideList;
    private ViewPager sliderPager;
    private TabLayout indicator;
    private RecyclerView moviesRv;
    private RecyclerView moviesfiltrated;
    private Typeface font;
    private TextView firstMovies, secondMovies;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle("Sotck Movie");

        font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Semibold.ttf");

        sliderPager = view.findViewById(R.id.view_pager);
        indicator = view.findViewById(R.id.indicator);
        moviesRv = view.findViewById(R.id.Rv_movies);
        moviesfiltrated = view.findViewById(R.id.Rv_movies_filtrates);
        firstMovies = view.findViewById(R.id.first_filtration);
        secondMovies = view.findViewById(R.id.second_filtration);
        firstMovies.setTypeface(font);
        secondMovies.setTypeface(font);

        slideList = new ArrayList<>();
        slideList.add(new Slide(R.drawable.slide1));
        slideList.add(new Slide(R.drawable.slide2));
        slideList.add(new Slide(R.drawable.slide1));
        slideList.add(new Slide(R.drawable.slide2));

        SlidePagerAdapter adapter = new SlidePagerAdapter(getContext(), slideList);
        sliderPager.setAdapter(adapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);


        indicator.setupWithViewPager(sliderPager, true);

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Moana", R.drawable.moana));
        movies.add(new Movie("Black P", R.drawable.blackp));
        movies.add(new Movie("Perdido em Marte", R.drawable.themartian));
        movies.add(new Movie("Moana", R.drawable.moana));
        movies.add(new Movie("Black P", R.drawable.blackp));
        movies.add(new Movie("Perdido em Marte", R.drawable.themartian));
        movies.add(new Movie("Moana", R.drawable.moana));
        movies.add(new Movie("Black P", R.drawable.blackp));
        movies.add(new Movie("Perdido em Marte", R.drawable.themartian));
        movies.add(new Movie("Moana", R.drawable.moana));
        movies.add(new Movie("Black P", R.drawable.blackp));
        movies.add(new Movie("Perdido em Marte", R.drawable.themartian));

        List<Movie> moviesFitrates = new ArrayList<>();
        moviesFitrates.add(new Movie("Black P", R.drawable.blackp));
        moviesFitrates.add(new Movie("Black P", R.drawable.blackp));
        moviesFitrates.add(new Movie("Black P", R.drawable.blackp));
        moviesFitrates.add(new Movie("Black P", R.drawable.blackp));
        moviesFitrates.add(new Movie("Moana", R.drawable.moana));
        moviesFitrates.add(new Movie("Moana", R.drawable.moana));
        moviesFitrates.add(new Movie("Moana", R.drawable.moana));
        moviesFitrates.add(new Movie("Moana", R.drawable.moana));
        moviesFitrates.add(new Movie("Perdido em Marte", R.drawable.themartian));
        moviesFitrates.add(new Movie("Perdido em Marte", R.drawable.themartian));
        moviesFitrates.add(new Movie("Perdido em Marte", R.drawable.themartian));
        moviesFitrates.add(new Movie("Perdido em Marte", R.drawable.themartian));

        MovieAdapter movieAdapter = new MovieAdapter(getContext(), movies);
        MovieAdapter movieAdapter1 = new MovieAdapter(getContext(), moviesFitrates);
        moviesfiltrated.setAdapter(movieAdapter1);
        moviesRv.setAdapter(movieAdapter);
        moviesfiltrated.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        moviesRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        return view;
    }

    class SliderTimer extends TimerTask {

        @Override
        public void run() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && getActivity() != null) {
                Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (sliderPager.getCurrentItem() < slideList.size() - 1) {
                            sliderPager.setCurrentItem(sliderPager.getCurrentItem() + 1);
                        } else {
                            sliderPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }
    }
}
