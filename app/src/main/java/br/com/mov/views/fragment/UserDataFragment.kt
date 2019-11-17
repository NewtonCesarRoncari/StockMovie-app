package br.com.mov.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.mov.R
import br.com.mov.models.User
import br.com.mov.views.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_data.*
import org.koin.android.viewmodel.ext.android.viewModel

class UserDataFragment : Fragment() {

    private val userViewModel: UserViewModel by viewModel()
    private lateinit var user: User

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        findUser()
    }

    private fun findUser() {
        userViewModel.findUserInDatabase().observe(viewLifecycleOwner, Observer {
            it.let{user ->
                if (user != null)
                this.user = user
                fragment_user_data_name_InputEditText.setText(this.user.name)
                fragment_user_data_email_InputEditText.setText(this.user.email)
                fragment_user_data_password_InputEditText.setText("123456")
        } })
    }
}
