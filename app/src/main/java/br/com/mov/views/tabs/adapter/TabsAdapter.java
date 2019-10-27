package br.com.mov.views.tabs.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import br.com.mov.views.fragment.UserDataFragment;
import br.com.mov.views.fragment.WalletFragment;

public class TabsAdapter extends FragmentStatePagerAdapter {

    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new WalletFragment();
                break;
            case 1:
                fragment = new UserDataFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Wallet";
            case 1:
                return "My Data";
        }
        return null;
    }
}
