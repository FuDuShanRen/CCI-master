package tiancefu.com.cci.activity_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import tiancefu.com.cci.R;
import tiancefu.com.cci.activity_base.BaseFragment;
import tiancefu.com.cci.adapter.BookViewpagerAdapter;
import tiancefu.com.cci.api.BookApiUtils;
import tiancefu.com.cci.utils.ThemeUtils;

/**
 * Created by dsblt on 2017/5/14.
 */

public class BookFragment extends BaseFragment implements ViewPager.OnPageChangeListener{

    @BindView(R.id.coordinatorlayout_bookfrg)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.appbarlayout_bookfrg)
    AppBarLayout appBarLayout;
    @BindView(R.id.tablayout_bookfrg)
    TabLayout tabLayout;
    @BindView(R.id.viewpager_bookfrg)
    ViewPager viewPager;

    private BookViewpagerAdapter bookViewpagerAdapter;
    private String[] mtitles;

    public static BookFragment newInstance(){
        Bundle bundle=new Bundle();
        BookFragment bookFragment=new BookFragment();
        bookFragment.setArguments(bundle);
        return bookFragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
           View view=inflater.inflate(R.layout.fragment_book,container,false);
           ButterKnife.bind(this,view);
           return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView(){
        mtitles= BookApiUtils.Tag_Titles;
        bookViewpagerAdapter = new BookViewpagerAdapter(getChildFragmentManager(),mtitles);
        viewPager.setAdapter(bookViewpagerAdapter);
        viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(this);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabTextColors(getResources().getColor(R.color.text_gray_6),ThemeUtils.getThemeColor());
        tabLayout.setSelectedTabIndicatorColor(ThemeUtils.getThemeColor());
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(bookViewpagerAdapter);


    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }



}
