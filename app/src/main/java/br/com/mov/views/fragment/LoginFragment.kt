package br.com.mov.views.fragment

import android.animation.Animator
import android.app.Dialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
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
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class LoginFragment : Fragment() {

    private lateinit var popup: Dialog
    private lateinit var fontSemiBold: Typeface
    private lateinit var fontRegular: Typeface
    private lateinit var msgWelcome: TextView
    private lateinit var msg: TextView
    private lateinit var msg2: TextView
    private lateinit var toggleMsg: TextView
    private lateinit var positiveButton: Button
    private val viewModel: StateAppViewModel by sharedViewModel()

    private lateinit var navController : NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        (activity as AppCompatActivity).supportActionBar!!.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity!!.window.statusBarColor = Color.BLACK
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.havCoponent = VisualComponents()

        fontSemiBold = Typeface.createFromAsset(activity!!.assets, "fonts/OpenSans-Semibold.ttf")
        fontRegular = Typeface.createFromAsset(activity!!.assets, "fonts/OpenSans-Regular.ttf")
        navController = NavHostFragment.findNavController(this)
        animation.visibility = INVISIBLE

        popup = Dialog(context!!)

        fragment_login_logo_msg.typeface = fontSemiBold
        fragment_login_logo_msg.typeface = fontRegular

        fragment_login_count_btn.setOnClickListener { showCountPopup() }

        fragment_login_btn.setOnClickListener { showLoginPopup() }
    }

    //regionPopup Count
    private fun showCountPopup() {
        popup.setContentView(R.layout.popup_count)
        initFieldsCountPopup()
        setFonts()
        toggleMsg.setOnClickListener {
            popup.dismiss()
            showLoginPopup()
        }
        popup.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popup.show()
    }

    private fun initFieldsCountPopup() {
        toggleMsg = popup.findViewById(R.id.popup_count_new_login)
        msgWelcome = popup.findViewById(R.id.popup_count_welcome)
        msg = popup.findViewById(R.id.popup_count_msg)
        msg2 = popup.findViewById(R.id.popup_count_msg2)
    }
    //endregion

    //regionPopup Login
    private fun showLoginPopup() {
        popup.setContentView(R.layout.popup_login)
        initFieldsLoginPopup()
        setFonts()
        positiveButton.setOnClickListener {
            popup.dismiss()
            animation.visibility = VISIBLE
            animation.setAnimation("anim/logo_animated.json")
            animation.playAnimation()
            animation.addAnimatorListener(object: Animator.AnimatorListener {
                override fun onAnimationStart(animation:Animator) {
                    Log.e("Animation:", "start")
                    hiddenFields()
                }
                override fun onAnimationEnd(animation:Animator) {
                    try
                    {
                        navController.navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                    catch (ex:Exception) {
                        ex.toString()
                    }
                }
                override fun onAnimationCancel(animation:Animator) {
                    Log.e("Animation:", "cancel")
                }
                override fun onAnimationRepeat(animation:Animator) {
                    Log.e("Animation:", "repeat")
                }
            })
//            navController.navigate(R.id.action_loginFragment_to_homeFragment)
        }
        toggleMsg.setOnClickListener {
            popup.dismiss()
            showCountPopup()
        }
        popup.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popup.show()
    }

    private fun hiddenFields() {
        fragment_login_img.visibility = GONE
        fragment_login_logo_msg.visibility = GONE
        fragment_login_msg.visibility = GONE
        fragment_login_count_btn.visibility = GONE
        fragment_login_btn.visibility = GONE
        fragment_login_text_login.visibility = GONE
    }

    private fun initFieldsLoginPopup() {
        positiveButton = popup.findViewById(R.id.popup_login_login_btn)
        toggleMsg = popup.findViewById(R.id.popup_login_new_count)
        msgWelcome = popup.findViewById(R.id.popup_login_welcome)
        msg = popup.findViewById(R.id.popup_login_msg)
        msg2 = popup.findViewById(R.id.popup_login_msg2)
    }
    //endregion

    private fun setFonts() {
        msgWelcome.typeface = fontSemiBold
        msg.typeface = fontRegular
        msg2.typeface = fontRegular
    }
}
