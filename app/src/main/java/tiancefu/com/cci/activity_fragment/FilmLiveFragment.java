package tiancefu.com.cci.activity_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import forezp.com.douyalibrary.utils.ScreenUtils;
import tiancefu.com.cci.R;
import tiancefu.com.cci.activity_base.BaseFragment;
import tiancefu.com.cci.activity_base.EasyRecyclerViewAdapter;
import tiancefu.com.cci.adapter.DouBanFilmLiveAdapter;
import tiancefu.com.cci.bean.filmlive.FlimLive;
import tiancefu.com.cci.presenter.DouBanFilmPresenter;
import tiancefu.com.cci.utils.ThemeUtils;
import tiancefu.com.cci.viewinterface.film.GetFilmLive;
import tiancefu.com.cci.widget.MyItemDecoration;

/**
 * Created by dsblt on 2017/5/2.
 */

public class FilmLiveFragment extends BaseFragment implements GetFilmLive,SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.recyclerview_filmlive)
    RecyclerView recyclerView;
    @BindView(R.id.swiperefreshlayout_filmlive)
    SwipeRefreshLayout swipeRefreshLayout;

    private DouBanFilmPresenter douBanFilmPresenter;
    private DouBanFilmLiveAdapter douBanFilmLiveAdapter;

    public static FilmLiveFragment newInstance(){
        Bundle bundle=new Bundle();
        FilmLiveFragment fragment=new FilmLiveFragment();
        fragment.setArguments(bundle);//使用Bundle来实现参数的传递
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.film_live_fragment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        douBanFilmLiveAdapter = new DouBanFilmLiveAdapter(getActivity());
        douBanFilmPresenter = new DouBanFilmPresenter(getActivity());
        douBanFilmPresenter.getFilmLive(this);
        swipeRefreshLayout.setColorSchemeColors(ThemeUtils.getThemeColor());
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        MyItemDecoration myItemDecoration = new MyItemDecoration(ScreenUtils.dipToPx(getActivity(),10),ScreenUtils.dipToPx(getActivity(),10),ScreenUtils.dipToPx(getActivity(),10),ScreenUtils.dipToPx(getActivity(),0));
        recyclerView.addItemDecoration(myItemDecoration);
        recyclerView.setAdapter(douBanFilmLiveAdapter);
    }

    @Override
    public void getFilmLiveSuccess(final FlimLive flimLive) {
        douBanFilmLiveAdapter.setmDatas(flimLive.getSubjects());
        douBanFilmLiveAdapter.notifyDataSetChanged();
        douBanFilmLiveAdapter.setmItemClickListener(new EasyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, Object data) {
                Intent intent=new Intent(getActivity(),FilmDetailActivity.class);
                intent.putExtra(FilmDetailActivity.EXTRA_ID,flimLive.getSubjects().get(position).getId());
                startTheActivityByIntent(intent);
            }
        });

    }

    @Override
    public void getFilmLiveFailed() {

    }

    @Override
    public void onRefresh() {
        douBanFilmPresenter.getFilmLive(this);
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(swipeRefreshLayout!=null){
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        },2000);

    }
}
