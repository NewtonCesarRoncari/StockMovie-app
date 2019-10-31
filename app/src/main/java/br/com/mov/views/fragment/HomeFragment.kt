package br.com.mov.views.fragment

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import br.com.mov.R
import br.com.mov.models.Movie
import br.com.mov.models.Slide
import br.com.mov.views.recyclerview.adapter.MovieAdapter
import br.com.mov.views.slidepager.adapter.SlidePagerAdapter
import br.com.mov.views.viewmodel.StateAppViewModel
import br.com.mov.views.viewmodel.VisualComponents
import com.google.android.material.tabs.TabLayout
import org.koin.android.viewmodel.ext.android.sharedViewModel
import java.util.*

class HomeFragment : Fragment() {

    private var slideList: MutableList<Slide>? = null
    private var sliderPager: ViewPager? = null
    private val viewModel: StateAppViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        (activity as AppCompatActivity).supportActionBar!!.show()
        activity!!.title = "Sotck Movie"

        val font = Typeface.createFromAsset(activity!!.assets, "fonts/OpenSans-Semibold.ttf")

        sliderPager = view.findViewById(R.id.view_pager)
        val indicator = view.findViewById<TabLayout>(R.id.indicator)
        val moviesRv = view.findViewById<RecyclerView>(R.id.Rv_movies)
        val moviesfiltrated = view.findViewById<RecyclerView>(R.id.Rv_movies_filtrates)
        val firstMovies = view.findViewById<TextView>(R.id.first_filtration)
        val secondMovies = view.findViewById<TextView>(R.id.second_filtration)
        firstMovies.typeface = font
        secondMovies.typeface = font

        slideList = ArrayList()
        slideList!!.add(Slide(R.drawable.slide1))
        slideList!!.add(Slide(R.drawable.slide2))
        slideList!!.add(Slide(R.drawable.slide1))
        slideList!!.add(Slide(R.drawable.slide2))

        val adapter = context?.let { SlidePagerAdapter(it, slideList as ArrayList<Slide>) }
        sliderPager!!.adapter = adapter

        val timer = Timer()
        timer.scheduleAtFixedRate(SliderTimer(), 4000, 6000)


        indicator.setupWithViewPager(sliderPager, true)

        val movies = ArrayList<Movie>()
        movies.add(Movie("Moana", R.drawable.moana))
        movies.add(Movie("Black P", R.drawable.blackp))
        movies.add(Movie("Perdido em Marte", R.drawable.themartian))
        movies.add(Movie("Moana", R.drawable.moana))
        movies.add(Movie("Black P", R.drawable.blackp))
        movies.add(Movie("Perdido em Marte", R.drawable.themartian))
        movies.add(Movie("Moana", R.drawable.moana))
        movies.add(Movie("Black P", R.drawable.blackp))
        movies.add(Movie("Perdido em Marte", R.drawable.themartian))
        movies.add(Movie("Moana", R.drawable.moana))
        movies.add(Movie("Black P", R.drawable.blackp))
        movies.add(Movie("Perdido em Marte", R.drawable.themartian))

        val moviesFitrates = ArrayList<Movie>()
        moviesFitrates.add(Movie("Black P", R.drawable.blackp))
        moviesFitrates.add(Movie("Black P", R.drawable.blackp))
        moviesFitrates.add(Movie("Black P", R.drawable.blackp))
        moviesFitrates.add(Movie("Black P", R.drawable.blackp))
        moviesFitrates.add(Movie("Moana", R.drawable.moana))
        moviesFitrates.add(Movie("Moana", R.drawable.moana))
        moviesFitrates.add(Movie("Moana", R.drawable.moana))
        moviesFitrates.add(Movie("Moana", R.drawable.moana))
        moviesFitrates.add(Movie("Perdido em Marte", R.drawable.themartian))
        moviesFitrates.add(Movie("Perdido em Marte", R.drawable.themartian))
        moviesFitrates.add(Movie("Perdido em Marte", R.drawable.themartian))
        moviesFitrates.add(Movie("Perdido em Marte", R.drawable.themartian))

        val movieAdapter = context?.let { MovieAdapter(it, movies) }
        val movieAdapter1 = context?.let { MovieAdapter(it, moviesFitrates) }
        moviesfiltrated.adapter = movieAdapter1
        moviesRv.adapter = movieAdapter
        moviesfiltrated.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        moviesRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.havCoponent = VisualComponents(true)
    }

    internal inner class SliderTimer : TimerTask() {

        override fun run() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && activity != null) {
                Objects.requireNonNull<FragmentActivity>(activity).runOnUiThread {
                    if (sliderPager!!.currentItem < slideList!!.size - 1) {
                        sliderPager!!.currentItem = sliderPager!!.currentItem + 1
                    } else {
                        sliderPager!!.currentItem = 0
                    }
                }
            }
        }
    }
}
