package br.com.mov.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import br.com.mov.R
import br.com.mov.views.tabs.adapter.TabsAdapter
import com.google.android.material.tabs.TabLayout

class UserFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_user, container, false)
        activity!!.title = "User"

        val tabsAdapter = TabsAdapter(activity!!.supportFragmentManager)
        val viewPager = view.findViewById<ViewPager>(R.id.fragment_user_view_pager)
        viewPager.adapter = tabsAdapter

        val tabLayout = view.findViewById<TabLayout>(R.id.fragment_user_tabLayout)
        tabLayout.setupWithViewPager(viewPager)

        return view
    }
}

