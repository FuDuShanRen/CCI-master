package tiancefu.com.cci.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by dsblt on 2017/5/11.
 */

public class MyViewpagerAdapter extends FragmentStatePagerAdapter {

    private String[] mTitles;
    private List<Fragment> mFragments;


    public MyViewpagerAdapter(FragmentManager fragmentManager,String[] titles,List<Fragment> fragmentList){
        super(fragmentManager);
        this.mTitles=titles;
        this.mFragments=fragmentList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
