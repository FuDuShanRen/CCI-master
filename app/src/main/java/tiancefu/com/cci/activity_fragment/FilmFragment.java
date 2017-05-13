package tiancefu.com.cci.activity_fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import tiancefu.com.cci.R;
import tiancefu.com.cci.activity_base.BaseFragment;
import tiancefu.com.cci.adapter.MyViewpagerAdapter;
import tiancefu.com.cci.utils.ThemeUtils;

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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsblt on 2017/5/1.
 */

public class FilmFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @BindView(R.id.coordinatorlayout_filmfrg)
    CoordinatorLayout coordinatorLayout_filmfrg;
    @BindView(R.id.appbarlayout_filmfrg)
    AppBarLayout appBarLayout_filmfrg;
    @BindView(R.id.tablayout_filmfrg)
    TabLayout tabLayout_filmfrg;
    @BindView(R.id.viewpager_filmfrg)
    ViewPager viewPager_filmfrg;

    private List<Fragment> mFragments;
    private FilmLiveFragment filmLiveFragment;
    private GetTop250FilmFragment getTop250FilmFragment;
    private MyViewpagerAdapter myViewpagerAdapter;

    private String[] titles;

    public static FilmFragment newInstance(){
        Bundle bundle=new Bundle();
        FilmFragment filmFragment=new FilmFragment();
        filmFragment.setArguments(bundle);
        return filmFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.film_fragment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initsViews();
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

    private void initsViews(){
        titles=getResources().getStringArray(R.array.tab_film);
        //初始化Fragment集合
        mFragments=new ArrayList<>();
        filmLiveFragment=FilmLiveFragment.newInstance();
        getTop250FilmFragment=GetTop250FilmFragment.newInstance();
        mFragments.add(filmLiveFragment);
        mFragments.add(getTop250FilmFragment);

        myViewpagerAdapter=new MyViewpagerAdapter(getChildFragmentManager(),titles,mFragments);
        viewPager_filmfrg.setAdapter(myViewpagerAdapter);
        //设置ViewPager最大缓存的页面个数
        viewPager_filmfrg.setOffscreenPageLimit(3);
        //为了让Toolbar中的title可以变化相应的Tab标题，给ViewPager添加页面动态监听器
        viewPager_filmfrg.addOnPageChangeListener(this);

        tabLayout_filmfrg.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout_filmfrg.setTabMode(TabLayout.MODE_FIXED);
        tabLayout_filmfrg.setSelectedTabIndicatorColor(ThemeUtils.getThemeColor());
        tabLayout_filmfrg.setTabTextColors(getResources().getColor(R.color.text_gray_6),ThemeUtils.getThemeColor());
        //将TabLayout和ViewPager进行关联，让两者联动起来
        tabLayout_filmfrg.setupWithViewPager(viewPager_filmfrg);
        //设置Tablayout的Tab显示ViewPager的适配器中的getPageTitle函数获取到的标题
        tabLayout_filmfrg.setTabsFromPagerAdapter(myViewpagerAdapter);
    }
}
