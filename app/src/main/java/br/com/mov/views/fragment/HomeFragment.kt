package br.com.mov.views.fragment

import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.mov.R
import br.com.mov.models.Slide
import br.com.mov.views.recyclerview.adapter.MovieAdapter
import br.com.mov.views.slidepager.adapter.SlidePagerAdapter
import br.com.mov.views.viewmodel.LoginViewModel
import br.com.mov.views.viewmodel.MovieViewModel
import br.com.mov.views.viewmodel.StateAppComponentsViewModel
import br.com.mov.views.viewmodel.VisualComponents
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var slideList: ArrayList<Slide>
    private val appComponentsViewModel: StateAppComponentsViewModel by sharedViewModel()
    private val loginViewModel: LoginViewModel by viewModel()
    private val movieViewModel: MovieViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var orderTitleMovieAdapter: MovieAdapter
    private lateinit var orderRatingMovieAdapter: MovieAdapter
    private val navController by lazy { findNavController(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        checkStateLogin()
        appComponentsViewModel.havCoponent = VisualComponents(true)
        movieViewModel.getMovies()
        movieViewModel.getMoviesOrderByTitle()
        movieViewModel.getMoviesOrderByRating()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val font = Typeface.createFromAsset(activity!!.assets, "fonts/OpenSans-Semibold.ttf")

        first_msg_movies.typeface = font
        msg_movies_order_title.typeface = font


        this.slideList = arrayListOf(
                Slide(R.drawable.batman_slide),
                Slide(R.drawable.interestellar_slide),
                Slide(R.drawable.starwars_slide),
                Slide(R.drawable.thorin_slide))

        val adapter = context?.let { SlidePagerAdapter(it, slideList) }
        slider_pager.adapter = adapter

        val timer = Timer()
        timer.scheduleAtFixedRate(SliderTimer(), 4000, 6000)

        indicator.setupWithViewPager(slider_pager, true)

        initMovieAdapter()
        initOrderTitleMovieAdapter()
        initOrderRatingMovieAdapter()
    }

    private fun initMovieAdapter() {
        movieViewModel.checkMoviesReturned()?.observe(viewLifecycleOwner,
                androidx.lifecycle.Observer { movieList ->
                    if (movieList != null) {
                        this.movieAdapter = context?.let { context ->
                            MovieAdapter(context, movieList)
                        }!!
                        rv_movies.adapter = movieAdapter
                        rv_movies.layoutManager = LinearLayoutManager(context,
                                LinearLayoutManager.HORIZONTAL, false)
                        movieAdapter.onItemCliclListener = { movie ->
                            goToMovieDetailFragment(movie.id)
                        }
                    }
                })
    }

    private fun initOrderTitleMovieAdapter() {
        movieViewModel.checkMoviesOrderByTitleReturned()?.observe(viewLifecycleOwner,
                androidx.lifecycle.Observer { movieList ->
                    if (movieList != null) {
                        this.orderTitleMovieAdapter = context?.let { context ->
                            MovieAdapter(context, movieList)
                        }!!
                        rv_movies_order_title.adapter = orderTitleMovieAdapter
                        rv_movies_order_title.layoutManager = LinearLayoutManager(context,
                                LinearLayoutManager.HORIZONTAL, false)
                        orderTitleMovieAdapter.onItemCliclListener = { movie ->
                            goToMovieDetailFragment(movie.id)
                        }
                    }
                })
    }

    private fun initOrderRatingMovieAdapter() {
        movieViewModel.checkMoviesOrderByRatingReturned()?.observe(viewLifecycleOwner,
                androidx.lifecycle.Observer { movieList ->
                    if (movieList != null) {
                        this.orderRatingMovieAdapter = context?.let { context ->
                            MovieAdapter(context, movieList)
                        }!!
                        rv_movies_order_rating.adapter = orderRatingMovieAdapter
                        rv_movies_order_rating.layoutManager = LinearLayoutManager(context,
                                LinearLayoutManager.HORIZONTAL, false)
                        orderRatingMovieAdapter.onItemCliclListener = { movie ->
                            goToMovieDetailFragment(movie.id)
                        }
                    }
                })
    }

    private fun checkStateLogin() {
        if (!loginViewModel.isLogged()) {
            goToLoginFragment()
        }
    }

    private fun goToLoginFragment() {
        val direction = HomeFragmentDirections
                .actionHomeFragmentToLoginFragment()
        navController.navigate(direction)
    }

    private fun goToMovieDetailFragment(movieId: Long?) {
        val id: Long = movieId!!
        val direction = HomeFragmentDirections.
                actionHomeFragmentToMovieDetailFragment(id)
        navController.navigate(direction)
    }

    internal inner class SliderTimer : TimerTask() {

        override fun run() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && activity != null && slider_pager != null) {
                Objects.requireNonNull<FragmentActivity>(activity).runOnUiThread {
                    if (slider_pager.currentItem < slideList.size - 1 && slider_pager != null) {
                        slider_pager.currentItem = slider_pager.currentItem + 1
                    } else {
                        slider_pager.currentItem = 0
                    }
                }
            }
        }
    }
}
