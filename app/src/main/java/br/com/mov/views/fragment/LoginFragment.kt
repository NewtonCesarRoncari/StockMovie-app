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
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import br.com.mov.R
import br.com.mov.models.User
import br.com.mov.models.constant.UserSituation
import br.com.mov.views.viewmodel.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.popup_count.*
import kotlinx.android.synthetic.main.popup_login.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {


    private lateinit var popup: Dialog
    private lateinit var fontSemiBold: Typeface
    private lateinit var fontRegular: Typeface
    private lateinit var msgWelcome: TextView
    private lateinit var msg: TextView
    private lateinit var msg2: TextView
    private lateinit var toggleMsg: TextView
    private lateinit var positiveButton: Button
    private lateinit var progressBar: ProgressBar
    private val appComponentsViewModel: StateAppComponentsViewModel by sharedViewModel()
    private val userViewModel: UserViewModel by viewModel()
    private val loginViewModel: LoginViewModel by viewModel()
    private val stateUserViewModel: StateUserViewModel by sharedViewModel()
    private val navController by lazy { findNavController(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        (activity as AppCompatActivity).supportActionBar!!.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            requireActivity().window.statusBarColor = Color.BLACK
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appComponentsViewModel.havCoponent = VisualComponents()

        fontSemiBold = Typeface.createFromAsset(activity!!.assets, "fonts/OpenSans-Semibold.ttf")
        fontRegular = Typeface.createFromAsset(activity!!.assets, "fonts/OpenSans-Regular.ttf")
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
        positiveButton.setOnClickListener {
            if (checkFieldsCountIsEmpty()){
                val user = initUserCount()
                userViewModel.postUser(user)
                showProgressBar(true)
                checkUserReturned()
            }
        }
        toggleMsg.setOnClickListener {
            popup.dismiss()
            showLoginPopup()
        }
        popup.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popup.show()
    }

    private fun initUserLogin(): User {
        return User(
                null,
                "",
                popup.popup_login_email_InputEditText.text.toString().trim(),
                popup.popup_login_password_InputEditText.text.toString().trim())
    }

    private fun initUserCount(): User {
        return User(
                null,
                popup.popup_count_name_InputEditText.text.toString().trim(),
                popup.popup_count_email_InputEditText.text.toString().trim(),
                popup.popup_count_password_InputEditText.text.toString().trim())
    }

    private fun checkFieldsCountIsEmpty(): Boolean {
        var somethingIsNull = true
        if (popup.popup_count_name_InputEditText.text.toString().trim().isEmpty()) {
            popup.popup_count_name_InputEditText.error = "Campo Obrigatório"
            somethingIsNull = false
        }
        if (popup.popup_count_email_InputEditText.text.toString().trim().isEmpty()) {
            popup.popup_count_email_InputEditText.error = "Campo Obrigatório"
            somethingIsNull = false
        }
        if (popup.popup_count_password_InputEditText.text.toString().trim().isEmpty()) {
            popup.popup_count_password_InputEditText.error = "Campo Obrigatório"
            somethingIsNull = false
        }
        if (!somethingIsNull){
            return false
        }
        return true
    }

    private fun initFieldsCountPopup() {
        positiveButton = popup.findViewById(R.id.popup_count_new_count_btn)
        progressBar = popup.findViewById(R.id.popup_count_progressBar)
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
            if (checkFieldsLoginIsEmpty()){
                val user = initUserLogin()
                userViewModel.postUserAuth(user)
                showProgressBar(true)
                checkUserReturned()
            }
        }
        toggleMsg.setOnClickListener {
            popup.dismiss()
            showCountPopup()
        }
        popup.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popup.show()
    }

    private fun checkFieldsLoginIsEmpty(): Boolean {
        var somethingIsNull = true
        if (popup.popup_login_email_InputEditText.text.toString().trim().isEmpty()) {
            popup.popup_login_email_InputEditText.error = "Campo Obrigatório"
            somethingIsNull = false
        }
        if (popup.popup_login_password_InputEditText.text.toString().trim().isEmpty()) {
            popup.popup_login_password_InputEditText.error = "Campo Obrigatório"
            somethingIsNull = false
        }
        if (!somethingIsNull){
            return false
        }
        return true
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
        progressBar = popup.findViewById(R.id.popup_login_progressBar)
        toggleMsg = popup.findViewById(R.id.popup_login_new_count)
        msgWelcome = popup.findViewById(R.id.popup_login_welcome)
        msg = popup.findViewById(R.id.popup_login_msg)
        msg2 = popup.findViewById(R.id.popup_login_msg2)
    }
    //endregion

    private fun goToHomeFragment() {
        val direction = LoginFragmentDirections
                .actionLoginFragmentToHomeFragment()
        navController.navigate(direction)
    }

    private fun setFonts() {
        msgWelcome.typeface = fontSemiBold
        msg.typeface = fontRegular
        msg2.typeface = fontRegular
    }

    private fun showProgressBar(show: Boolean) {
        if (!show) {
            this.positiveButton.text = "Tentar Novamente"
            this.positiveButton.isClickable = true
            this.positiveButton.isFocusable = true
            this.progressBar.visibility = GONE
        } else {
            this.positiveButton.text = ""
            this.positiveButton.isClickable = false
            this.positiveButton.isFocusable = false
            this.progressBar.visibility = VISIBLE
        }
    }

    private fun checkUserReturned() {
        stateUserViewModel.userReturned.observe(viewLifecycleOwner, Observer {
            it?.also { userReturned ->
                if (userReturned.userReturned == UserSituation.UNRETURNED) {
                    showProgressBar(false)
                    stateUserViewModel.clearUserSituation()
                    Toast.makeText(context,
                            "Falha de comunicação", Toast.LENGTH_SHORT).show()
                } else if (userReturned.userReturned == UserSituation.RETURNED) {
                    showProgressBar(false)
                    popup.dismiss()
                    animation.visibility = VISIBLE
                    animation.setAnimation("anim/logo_animated.json")
                    animation.playAnimation()
                    animation.addAnimatorListener(animatorListener())
                }
            }
        })
    }

    private fun animatorListener(): Animator.AnimatorListener {
        return object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                Log.e("Animation:", "start")
                hiddenFields()
            }

            override fun onAnimationEnd(animation: Animator) {
                try {
                    loginViewModel.login()
                    goToHomeFragment()
                } catch (ex: Exception) {
                    ex.toString()
                }
            }

            override fun onAnimationCancel(animation: Animator) {
                Log.e("Animation:", "cancel")
            }

            override fun onAnimationRepeat(animation: Animator) {
                Log.e("Animation:", "repeat")
            }
        }
    }

}
