package br.com.mov.views.fragment

import android.app.Dialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import br.com.mov.R
import br.com.mov.views.viewmodel.StateAppViewModel
import br.com.mov.views.viewmodel.VisualComponents
import org.koin.android.viewmodel.ext.android.sharedViewModel

class LoginFragment : Fragment() {

    private var popup: Dialog? = null
    private var fontSemiBold: Typeface? = null
    private var fontRegular: Typeface? = null
    private var msgWelcome: TextView? = null
    private var msg: TextView? = null
    private var msg2: TextView? = null
    private var toggleMsg: TextView? = null
    private var positiveButton: Button? = null
    private val viewModel: StateAppViewModel by sharedViewModel()

    private lateinit var navController : NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        (activity as AppCompatActivity).supportActionBar!!.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity!!.window.statusBarColor = Color.BLACK
        }

        fontSemiBold = Typeface.createFromAsset(activity!!.assets, "fonts/OpenSans-Semibold.ttf")
        fontRegular = Typeface.createFromAsset(activity!!.assets, "fonts/OpenSans-Regular.ttf")
        navController = NavHostFragment.findNavController(this)

        popup = Dialog(context!!)
        val logoMsg = view.findViewById<TextView>(R.id.fragment_login_logo)
        val description = view.findViewById<TextView>(R.id.fragment_login_msg)
        val btnLogin = view.findViewById<Button>(R.id.fragment_login_btn)
        val btnCount = view.findViewById<Button>(R.id.fragment_login_count_btn)

        logoMsg.typeface = fontSemiBold
        description.typeface = fontRegular

        btnCount.setOnClickListener { showCountPopup() }

        btnLogin.setOnClickListener { showLoginPopup() }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.havCoponent = VisualComponents()
    }

    //regionPopup Count
    private fun showCountPopup() {
        popup!!.setContentView(R.layout.popup_count)
        initFieldsCountPopup()
        setFonts()
        toggleMsg!!.setOnClickListener {
            popup!!.dismiss()
            showLoginPopup()
        }
        popup!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popup!!.show()
    }

    private fun initFieldsCountPopup() {
        toggleMsg = popup!!.findViewById(R.id.popup_count_new_login)
        msgWelcome = popup!!.findViewById(R.id.popup_count_welcome)
        msg = popup!!.findViewById(R.id.popup_count_msg)
        msg2 = popup!!.findViewById(R.id.popup_count_msg2)
    }
    //endregion

    //regionPopup Login
    private fun showLoginPopup() {
        popup!!.setContentView(R.layout.popup_login)
        initFieldsLoginPopup()
        setFonts()
        positiveButton!!.setOnClickListener {
            popup!!.dismiss()
            navController.navigate(R.id.action_loginFragment_to_homeFragment)
        }
        toggleMsg!!.setOnClickListener {
            popup!!.dismiss()
            showCountPopup()
        }
        popup!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popup!!.show()
    }

    private fun initFieldsLoginPopup() {
        positiveButton = popup!!.findViewById(R.id.popup_login_login_btn)
        toggleMsg = popup!!.findViewById(R.id.popup_login_new_count)
        msgWelcome = popup!!.findViewById(R.id.popup_login_welcome)
        msg = popup!!.findViewById(R.id.popup_login_msg)
        msg2 = popup!!.findViewById(R.id.popup_login_msg2)
    }
    //endregion

    private fun setFonts() {
        msgWelcome!!.typeface = fontSemiBold
        msg!!.typeface = fontRegular
        msg2!!.typeface = fontRegular
    }
}
