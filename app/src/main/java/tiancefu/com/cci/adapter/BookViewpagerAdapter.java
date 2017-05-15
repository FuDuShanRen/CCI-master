package tiancefu.com.cci.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import tiancefu.com.cci.activity_fragment.BookSingleTagFragment;

/**
 * Created by dsblt on 2017/5/14.
 */

public class BookViewpagerAdapter extends FragmentStatePagerAdapter {

    private String[] titles;

    public BookViewpagerAdapter(FragmentManager fragmentManager,String[] titles){
            super(fragmentManager);
            this.titles=titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return BookSingleTagFragment.newInstance(position,titles[position]);
    }

    @Override
    public int getCount() {
        return titles.length;
    }
}
