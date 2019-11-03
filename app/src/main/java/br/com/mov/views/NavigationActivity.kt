package br.com.mov.views

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI
import br.com.mov.R
import br.com.mov.views.viewmodel.StateAppViewModel
import kotlinx.android.synthetic.main.frame_navigation.*
import org.koin.android.viewmodel.ext.android.viewModel

class NavigationActivity : AppCompatActivity() {

    private val viewModel: StateAppViewModel by viewModel()
    private val navController by lazy {
        findNavController(this, R.id.frame_navigation)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.frame_navigation)

        NavigationUI.setupWithNavController(bottom_nav, navController)

        navController.addOnDestinationChangedListener { _,
                                                        destination,
                                                        _ ->
            title = destination.label
            when(destination.id){
                R.id.homeFragment -> supportActionBar?.show()
                R.id.loginFragment -> supportActionBar?.hide()
            }
        }

        viewModel.components.observe(this, Observer {
            it?.also { havComponent ->
                run {
                    if (havComponent.bottomNavigation) {
                        bottom_nav.visibility = VISIBLE
                    } else {
                        bottom_nav.visibility = GONE
                    }
                }
            }
        })
    }
}
