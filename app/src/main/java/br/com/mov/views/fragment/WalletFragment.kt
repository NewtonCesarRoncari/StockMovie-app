package br.com.mov.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import br.com.mov.R
import br.com.mov.extensions.formatForUSACoin
import br.com.mov.models.dto.UserRequest
import br.com.mov.views.recyclerview.adapter.UserMovieAdapter
import br.com.mov.views.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_user_wallet.*
import org.koin.android.viewmodel.ext.android.viewModel

class WalletFragment : Fragment() {

    private lateinit var adapter: UserMovieAdapter
    private val userViewModel: UserViewModel by viewModel()
    private var request: UserRequest? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        findUser()
        checkUserReturned()
    }

    private fun findUser() {
        userViewModel.findUserInDatabase().observe(viewLifecycleOwner, Observer {
            it.let { userReturned ->
                if (userReturned != null){
                    userReturned.id!!.let { userId -> userViewModel.getUser(userId) }
                }
            }
        })
    }

    private fun checkUserReturned() {
        userViewModel.checkUserReturned().observe(this, Observer { userRequest ->
            this.request = userRequest
            if (this.request != null) {
                fragment_user_wallet_user_balance.text = request!!.balance.formatForUSACoin()
                initUserMovieAdapter(userRequest)
            }
        })
    }

    private fun initUserMovieAdapter(userRequest: UserRequest) {
        if (userRequest.buyOrders.isNotEmpty()) {
            this.adapter = context?.let { context ->
                UserMovieAdapter(context, userRequest.buyOrders)
            }!!
            fragment_user_wallet_rv.adapter = adapter
        }
    }

}
