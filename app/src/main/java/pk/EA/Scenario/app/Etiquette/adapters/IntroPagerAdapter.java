package pk.EA.Scenario.app.Etiquette.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import pk.EA.Scenario.app.Etiquette.fragments.IntroSliderFragment;

/**
 * Created by Mian on 8/15/2015.
 */


public class IntroPagerAdapter extends FragmentPagerAdapter {

    private int pagerCount = 3;

    //private Random random = new Random();

    public IntroPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override public Fragment getItem(int i) {
        return IntroSliderFragment.getInstance(i);
    }

    @Override public int getCount() {
        return pagerCount;
    }
}