package tiancefu.com.cci.activity_fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import tiancefu.com.cci.R;
import tiancefu.com.cci.activity_base.BaseActivity;
import tiancefu.com.cci.bean.top250.Root;
import tiancefu.com.cci.utils.ThemeUtils;
import tiancefu.com.cci.viewinterface.film.GetTop250View;

public class MainActivity extends BaseActivity implements GetTop250View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbarlayout)
    AppBarLayout appBarLayout;
    @BindView(R.id.radiogroup)
    RadioGroup radioGroup;
    @BindView(R.id.coordinatorlayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.drawerlayout_home)
    DrawerLayout drawerLayout_home;
    @BindView(R.id.navigation_view)
    NavigationView navigation_view;

    private int currentFrament;

    private  FilmFragment filmFrament;
    Fragment musicFragment;
    Fragment bookFrament;
    Fragment newsFragment;
    private List<Fragment> listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucentForDrawerLayout(MainActivity.this,drawerLayout_home, ThemeUtils.getThemeColor());
        //applyKitKatTranslucency();
        //setTranslucentStatus(true);
        initView();
        initViewpagerAndFragment();
        initListener();
        initChangeTheme();
    }

    private void initView(){
        toolbar.setBackgroundColor(ThemeUtils.getThemeColor());
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout_home,toolbar,R.string.open,R.string.close);
        actionBarDrawerToggle.syncState();
        drawerLayout_home.setDrawerListener(actionBarDrawerToggle);
        navigation_view.inflateHeaderView(R.layout.header_nav);
        View headView=navigation_view.getHeaderView(0);
        CircleImageView circleImageView= (CircleImageView)headView.findViewById(R.id.sdv_avatar);
        circleImageView.setBackgroundResource(R.drawable.icon_avatar);
        navigation_view.inflateMenu(R.menu.navi_layout);
        navigation_view.setItemIconTintList(ThemeUtils.getNaviItemIconColorList());
        // 自己写的方法，设置NavigationView中menu的item被选中后要执行的操作
        onNavgationViewMenuItemSelected(navigation_view);
    }

    private void onNavgationViewMenuItemSelected(NavigationView navigationView){

    }

    private void initViewpagerAndFragment(){
        filmFrament=FilmFragment.newInstance();
        listFragment=new ArrayList<>();
        listFragment.add(filmFrament);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setOnPageChangeListener(opcListener);


    }

    private void initListener(){
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.film:
                        currentFrament=0;
                        break;
                    case R.id.book:
                        currentFrament=1;
                        break;
                    case R.id.music:
                        currentFrament=2;
                        break;
                    case R.id.news:
                        currentFrament=3;
                        break;
                }
                viewPager.setCurrentItem(currentFrament,false);
            }
        });

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return listFragment.get(position);
            }

            @Override
            public int getCount() {
                return listFragment.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
            }
        });


    }

    private void initChangeTheme(){

    }


    private ViewPager.OnPageChangeListener opcListener=new ViewPager.OnPageChangeListener(){
        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0:
                    radioGroup.check(R.id.film);
                    break;
                case 1:
                    radioGroup.check(R.id.book);
                    break;
                case 2:
                    radioGroup.check(R.id.music);
                    break;
                case 3:
                    radioGroup.check(R.id.news);
                    break;
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };






    @Override
    public void getTop250InfoSuccss(Root root, boolean isLoadMore) {

    }

    @Override
    public void getTop250InfoFailed() {

    }

    @Override
    public String setActName() {
        return null;
    }



}
