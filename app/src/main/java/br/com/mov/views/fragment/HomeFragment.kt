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
import br.com.mov.extensions.getMovieResources
import br.com.mov.extensions.getMoviesFiltratesResource
import br.com.mov.models.Movie
import br.com.mov.models.Slide
import br.com.mov.views.recyclerview.adapter.MovieAdapter
import br.com.mov.views.slidepager.adapter.SlidePagerAdapter
import br.com.mov.views.viewmodel.LoginViewModel
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
    private val navController by lazy { findNavController(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        checkStateLogin()
        appComponentsViewModel.havCoponent = VisualComponents(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val font = Typeface.createFromAsset(activity!!.assets, "fonts/OpenSans-Semibold.ttf")

        first_msg_movies.typeface = font
        second_msg_movies.typeface = font


        this.slideList = arrayListOf(
                Slide(R.drawable.slide1),
                Slide(R.drawable.slide2),
                Slide(R.drawable.slide1),
                Slide(R.drawable.slide2))

        val adapter = context?.let { SlidePagerAdapter(it, slideList) }
        slider_pager.adapter = adapter

        val timer = Timer()
        timer.scheduleAtFixedRate(SliderTimer(), 4000, 6000)


        indicator.setupWithViewPager(slider_pager, true)

        val movieAdapter = context?.let { MovieAdapter(it, Movie("", "").getMovieResources()) }
        val movieAdapter1 = context?.let { MovieAdapter(it, Movie("", "").getMoviesFiltratesResource()) }
        rv_movies_filtrates.adapter = movieAdapter1
        rv_movies.adapter = movieAdapter
        rv_movies_filtrates.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_movies.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        movieAdapter!!.onItemCliclListener = {
            goToMovieDetailFragment()
        }
        movieAdapter1!!.onItemCliclListener = {
            goToMovieDetailFragment()
        }
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

    private fun goToMovieDetailFragment() {
        navController.navigate(R.id.action_homeFragment_to_movieDetailFragment)
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
