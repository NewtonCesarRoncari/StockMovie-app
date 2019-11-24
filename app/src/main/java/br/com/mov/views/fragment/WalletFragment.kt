package br.com.mov.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.mov.R
import br.com.mov.extensions.formatForUSACoin
import br.com.mov.models.User
import br.com.mov.views.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_wallet.*
import org.koin.android.viewmodel.ext.android.viewModel

class WalletFragment : Fragment() {

    private val userViewModel: UserViewModel by viewModel()
    private var user: User? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        findUser()
        return inflater.inflate(R.layout.fragment_user_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (this.user != null){
            fragment_user_wallet_user_balance.text = user!!.balance.formatForUSACoin()
        }
    }

    private fun findUser() {
        userViewModel.findUserInDatabase().observe(viewLifecycleOwner, Observer {
            it.let {userReturned ->
                userReturned.id!!.let { userId -> userViewModel.getUser(userId) }
                this.user = userViewModel.getUser(userReturned.id!!)
            }
        })
    }


}
