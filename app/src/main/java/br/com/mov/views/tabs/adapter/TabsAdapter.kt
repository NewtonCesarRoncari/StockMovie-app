package br.com.mov.views.tabs.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

import br.com.mov.views.fragment.UserDataFragment
import br.com.mov.views.fragment.WalletFragment

class TabsAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            WalletFragment()
        } else {
            UserDataFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Wallet"
            1 -> return "My Data"
        }
        return null
    }
}
