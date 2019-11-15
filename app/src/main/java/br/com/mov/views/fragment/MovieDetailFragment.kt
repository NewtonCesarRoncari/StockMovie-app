package br.com.mov.views.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.mov.R
import br.com.mov.views.viewmodel.StateAppComponentsViewModel
import br.com.mov.views.viewmodel.VisualComponents
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MovieDetailFragment: Fragment(){

    private lateinit var popup: Dialog
    private val appComponentsViewModel: StateAppComponentsViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_movie_detail,
                container, false)

        appComponentsViewModel.havCoponent = VisualComponents(false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popup = Dialog(context!!)

        fragment_movie_detail_btn_invest.setOnClickListener {
            showInvestPopup()
        }
    }

    private fun showInvestPopup() {
        popup.setContentView(R.layout.popup_invest)
        popup.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popup.show()
    }
}