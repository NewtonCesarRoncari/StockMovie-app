package br.com.mov.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.mov.R
import kotlinx.android.synthetic.main.fragment_user_data.*

class UserDataFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragment_user_data_name_InputEditText.setText("Your Name")
        fragment_user_data_email_InputEditText.setText("example@email.com")
        fragment_user_data_password_InputEditText.setText("123")
    }
}
