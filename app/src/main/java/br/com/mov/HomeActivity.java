package br.com.mov;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends AppCompatActivity {

    private List<Slide> slideList;
    private ViewPager sliderPager;
    private TabLayout indicator;
    private RecyclerView moviesRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Movie Invest");

        sliderPager = findViewById(R.id.view_pager);
        indicator = findViewById(R.id.indicator);
        moviesRv = findViewById(R.id.Rv_movies);

        slideList = new ArrayList<>();
        slideList.add( new Slide(R.drawable.slide1));
        slideList.add( new Slide(R.drawable.slide2));
        slideList.add( new Slide(R.drawable.slide1));
        slideList.add( new Slide(R.drawable.slide2));

        SlidePagerAdapter adapter = new SlidePagerAdapter(this, slideList);
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

        MovieAdapter movieAdapter = new MovieAdapter(this, movies);
        moviesRv.setAdapter(movieAdapter);
        moviesRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }

    class SliderTimer extends TimerTask{

        @Override
        public void run() {
            HomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderPager.getCurrentItem()<slideList.size()-1){
                        sliderPager.setCurrentItem(sliderPager.getCurrentItem()+1);
                    } else {
                        sliderPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
