package br.com.mov.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import br.com.mov.R
import br.com.mov.models.User
import kotlinx.android.synthetic.main.fragment_user_data.*

class UserDataFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val user = User("  User Name", "  email@example.com", "  123456")
        fragment_user_data_name_InputEditText.setText(user.name)
        fragment_user_data_email_InputEditText.setText(user.email)
        fragment_user_data_password_InputEditText.setText(user.password)
    }
}
