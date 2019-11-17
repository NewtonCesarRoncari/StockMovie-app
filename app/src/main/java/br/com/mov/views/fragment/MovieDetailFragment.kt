package br.com.mov.views.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import br.com.mov.R
import br.com.mov.extensions.formatForUSACoin
import br.com.mov.extensions.loadThumbnail
import br.com.mov.models.dto.MovieFullRequest
import br.com.mov.views.viewmodel.MovieDetailViewModel
import br.com.mov.views.viewmodel.StateAppComponentsViewModel
import br.com.mov.views.viewmodel.VisualComponents
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieDetailFragment : Fragment() {

    private val arguments by navArgs<MovieDetailFragmentArgs>()
    private val movieId by lazy {
        arguments.movieId
    }
    private lateinit var popup: Dialog
    private val appComponentsViewModel: StateAppComponentsViewModel by sharedViewModel()
    private val movieDetailViewModel: MovieDetailViewModel by viewModel { parametersOf(movieId) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movie_detail,
                container, false)

        findMovieById(movieId)

        appComponentsViewModel.havCoponent = VisualComponents(false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkMovieId()
        popup = Dialog(context!!)

        fragment_movie_detail_btn_invest.setOnClickListener {
            showInvestPopup()
        }
    }

    private fun showInvestPopup() {
        popup.setContentView(R.layout.popup_invest)
        popup.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val btnInvest: Button = popup.findViewById(R.id.popup_btn_invest)
        btnInvest.setOnClickListener {
            popup.dismiss()
        }
        popup.show()
    }

    private fun findMovieById(movieId: Long) {
        movieDetailViewModel.findMovieById(movieId)
    }

    private fun checkMovieId() {
        movieDetailViewModel.checkMovieReturned().observe(this, Observer {
            movie -> if (movie != null) {
                initFileds(movie)
            }
        })
    }

    private fun initFileds(movie: MovieFullRequest?) {
        if (movie != null) {
            movie.pictureUrl.let { fragment_movie_detail_item_movie_img.loadThumbnail(it) }
            fragment_movie_detail_name_director.text = movie.production!![0].name
            fragment_movie_detail_name_actor_one.text = movie.cast!![0].actor
            fragment_movie_detail_name_actor_two.text = movie.cast!![0].character
            fragment_movie_detail_genre.text = movie.genres!![0].name
            fragment_movie_detail_movie_title.text = movie.originalTitle
            fragment_movie_detail_synopsis.text = movie.overview
            fragment_movie_detail_budget.text = movie.budget!!.formatForUSACoin()
            fragment_movie_detail_box_office.text = movie.revenue!!.formatForUSACoin()
        }
    }
}