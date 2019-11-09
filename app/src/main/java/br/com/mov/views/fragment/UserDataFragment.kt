package br.com.mov.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.mov.R
import br.com.mov.models.User
import br.com.mov.views.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_data.*
import org.koin.android.viewmodel.ext.android.viewModel

class UserDataFragment : Fragment() {

    lateinit var user: User
    private val viewModel: UserViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragment_user_data_name_InputEditText.setText("Your Name")
        fragment_user_data_email_InputEditText.setText("example@email.com")
        fragment_user_data_password_InputEditText.setText("123")
    }

//    fun searchUser(email: String) {
//        viewModel.searchUser(email).observe(this, androidx.lifecycle.Observer { resource ->
//            resource.data?.let { this.user = br.com.mov.models.User(it, popup.popup_count_email_InputEditText.text.toString(), popup.popup_count_password_InputEditText.text.toString()) }
//            resource.error?.let { showMessageError(MENSAGEM_ERRO_RESPOSTA_NAO_SUCEDIDA) }
//        })
//
//    }
}
