package br.com.mov.views.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.viewpager.widget.ViewPager
import br.com.mov.R
import br.com.mov.views.tabs.adapter.TabsAdapter
import br.com.mov.views.viewmodel.LoginViewModel
import com.google.android.material.tabs.TabLayout
import org.koin.android.viewmodel.ext.android.viewModel

class UserFragment : Fragment() {

    private val navController by lazy { findNavController(this) }
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)

        setHasOptionsMenu(true)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabsAdapter = TabsAdapter(activity!!.supportFragmentManager)
        val viewPager = view.findViewById<ViewPager>(R.id.fragment_user_view_pager)
        viewPager.adapter = tabsAdapter

        val tabLayout = view.findViewById<TabLayout>(R.id.fragment_user_tabLayout)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_user_logout) {

            loginViewModel.logout()
            goToLoginFragment()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToLoginFragment() {
        val direction = UserFragmentDirections.actionUserFragmentToLoginFragment()
        navController.navigate(direction)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_user, menu)
    }
}

