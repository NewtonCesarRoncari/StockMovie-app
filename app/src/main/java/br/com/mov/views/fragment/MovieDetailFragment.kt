package br.com.mov.views.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import br.com.mov.R
import br.com.mov.extensions.formatForUSACoin
import br.com.mov.extensions.loadThumbnail
import br.com.mov.models.BuyOrder
import br.com.mov.models.User
import br.com.mov.models.dto.MovieFullRequest
import br.com.mov.views.viewmodel.*
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieDetailFragment : Fragment() {

    private val arguments by navArgs<MovieDetailFragmentArgs>()
    private val movieId by lazy {
        arguments.movieId
    }
    private lateinit var fontSemiBold: Typeface
    private lateinit var fontRegular: Typeface
    private lateinit var movie: MovieFullRequest
    private lateinit var popup: Dialog
    private lateinit var stockQuantity: EditText
    private lateinit var stockPrice: TextView
    private lateinit var user: User
    private val appComponentsViewModel: StateAppComponentsViewModel by sharedViewModel()
    private val movieDetailViewModel: MovieDetailViewModel by viewModel { parametersOf(movieId) }
    private val userViewModel: UserViewModel by viewModel()
    private val buyOrderViewModel: BuyOrderViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movie_detail,
                container, false)

        findMovieById(movieId)
        
        userViewModel.findUserInDatabase().observe(this, Observer { user ->
            this.user = user
        })
        appComponentsViewModel.havCoponent = VisualComponents(false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fontSemiBold = Typeface.createFromAsset(activity!!.assets, "fonts/OpenSans-Semibold.ttf")
        fontRegular = Typeface.createFromAsset(activity!!.assets, "fonts/OpenSans-Regular.ttf")

        checkMovieId()
        popup = Dialog(context!!)

        fragment_movie_detail_btn_invest.setOnClickListener {
            showInvestPopup()
        }
    }

    private fun showInvestPopup() {
        popup.setContentView(R.layout.popup_invest)
        popup.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.stockQuantity = popup.findViewById(R.id.popup_invest_quant)
        this.stockPrice = popup.findViewById(R.id.popup_invest_price)
        stockPrice.text = movie.stockPrice!!.formatForUSACoin()
        stockQuantity.setOnClickListener {
            if (stockQuantity.text.toString().isNotEmpty()){
                stockPrice.text =
                        movieDetailViewModel
                                .priceStocksCalculated(stockQuantity.text.toString().toLong(),
                                        movie.stockPrice!!).formatForUSACoin()
            }
        }
        val btnInvest: Button = popup.findViewById(R.id.popup_invest_btn_invest)
        btnInvest.setOnClickListener {
            if (stockQuantity.text.toString().isNotEmpty()){
                buyOrderViewModel.postBuyOrder(
                        BuyOrder(movieId, user.id!!, stockQuantity.text.toString().toLong()))
            }
            popup.dismiss()
        }
        popup.show()
    }

    private fun findMovieById(movieId: Long) {
        movieDetailViewModel.findMovieById(movieId)
    }

    private fun checkMovieId() {
        movieDetailViewModel.checkMovieReturned().observe(
                this, Observer { movie ->
            if (movie != null) {
                this.movie = movie
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
            fragment_movie_detail_stock_quant.text = movie.quantityAvailable.toString()
            fragment_movie_detail_stock_price.text = movie.stockPrice!!.formatForUSACoin()
            fragment_movie_detail_budget.text = movie.budget!!.formatForUSACoin()
            fragment_movie_detail_box_office.text = movie.revenue!!.formatForUSACoin()
            setFonts()
        }
    }

    private fun setFonts() {
        fragment_movie_detail_msg_director.typeface = fontSemiBold
        fragment_movie_detail_name_director.typeface = fontRegular
        fragment_movie_detail_msg_cast.typeface = fontSemiBold
        fragment_movie_detail_name_actor_one.typeface = fontRegular
        fragment_movie_detail_name_actor_two.typeface = fontRegular
        fragment_movie_detail_msg_genre.typeface = fontSemiBold
        fragment_movie_detail_genre.typeface = fontRegular
        fragment_movie_detail_movie_title.typeface = fontSemiBold
        fragment_movie_detail_synopsis.typeface = fontRegular
        fragment_movie_detail_msg_stock.typeface = fontRegular
        fragment_movie_detail_stock_quant.typeface = fontRegular
        fragment_movie_detail_msg_value_stock.typeface = fontRegular
        fragment_movie_detail_stock_price.typeface = fontRegular
        fragment_movie_detail_msg_budget.typeface = fontRegular
        fragment_movie_detail_budget.typeface = fontRegular
        fragment_movie_detail_msg_amount.typeface = fontRegular
        fragment_movie_detail_msg_box_office.typeface = fontRegular
        fragment_movie_detail_box_office.typeface = fontRegular
        fragment_movie_detail_btn_invest.typeface = fontSemiBold
    }
}