package com.android.jj.pmt;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.List;

/**
 * Created by JessonJJ on 2018/3/14.
 */

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {
    //private FragmentManager mfragmentManager;
    private List<Fragment> mlistFragment;

    public TabFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        mlistFragment = list;
    }

    public Fragment getItem(int position) {
        return mlistFragment.get(position);
    }

    public int getCount() {
        return mlistFragment.size();
    }
}
