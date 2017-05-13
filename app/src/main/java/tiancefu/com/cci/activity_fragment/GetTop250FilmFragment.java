package tiancefu.com.cci.activity_fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import tiancefu.com.cci.R;
import tiancefu.com.cci.activity_base.BaseFragment;
import tiancefu.com.cci.adapter.Top250FilmAdapter;
import tiancefu.com.cci.bean.top250.Root;
import tiancefu.com.cci.presenter.DouBanFilmPresenter;
import tiancefu.com.cci.utils.ThemeUtils;
import tiancefu.com.cci.viewinterface.film.GetTop250View;
import tiancefu.com.cci.widget.MyLinearLayoutManager;

/**
 * Created by dsblt on 2017/5/7.
 */

public class GetTop250FilmFragment extends BaseFragment implements GetTop250View,SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerview_filmlive)
    RecyclerView recyclerView;
    @BindView(R.id.swiperefreshlayout_filmlive)
    SwipeRefreshLayout swipeRefreshLayout;

    private DouBanFilmPresenter douBanFilmPresenter;
    private Top250FilmAdapter adapter;

    private MyLinearLayoutManager myLinearLayoutManager;

    private  int lastVisibleItem;
    private  int pageCount;
    private  final int PAGE_SIZE=10;
    private Root mfilmRoot;


    public static GetTop250FilmFragment newInstance(){
        Bundle args=new Bundle();
        GetTop250FilmFragment filmFragment=new GetTop250FilmFragment();
        filmFragment.setArguments(args);
        return filmFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.film_live_fragment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        douBanFilmPresenter =new DouBanFilmPresenter(getActivity());
        //该调用的方法包含igetTopView.getTop250Success(root, isLoadMore)执行
        //该方法传入接口参数IgetTop250View，this代表IgetTop250View接口，因为 FilmTop250Fragment实现（implements)接口该接口
        douBanFilmPresenter.getTop250FilmInfo(this,pageCount*PAGE_SIZE,PAGE_SIZE,false);

        myLinearLayoutManager=new MyLinearLayoutManager(getContext());
        scrollRecycleView();
        recyclerView.setLayoutManager(myLinearLayoutManager);
        swipeRefreshLayout.setColorSchemeColors(ThemeUtils.getThemeColor());
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void getTop250InfoFailed() {

    }

    @Override
    public void getTop250InfoSuccss(Root root, boolean isLoadMore) {
        if(isLoadMore){
            //addAll()将新内容全部放到list队列结尾
            mfilmRoot.getSubjectses().addAll(root.getSubjectses());
            adapter.notifyDataSetChanged();
        }else{
            mfilmRoot= root;
            adapter=new Top250FilmAdapter(getActivity(),mfilmRoot);
            recyclerView.setAdapter(adapter);

        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        douBanFilmPresenter.getTop250FilmInfo(this,pageCount*PAGE_SIZE,PAGE_SIZE,false);
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(swipeRefreshLayout!=null){
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        },2000);
    }

    public void scrollRecycleView(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    lastVisibleItem=myLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                    //表示未加载显示任何信息
                    if(myLinearLayoutManager.getItemCount()==1){
                        if(adapter!=null){
                            adapter.updateLoadStatus(adapter.LOAD_NONE);
                        }
                        return;
                    }

                    if(lastVisibleItem+1==myLinearLayoutManager.getItemCount()){
                        if(adapter!=null){
                            adapter.updateLoadStatus(adapter.LOAD_PULL_TO);
                            adapter.updateLoadStatus(adapter.LOAD_MORE);
                        }

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pageCount++;
                                douBanFilmPresenter.getTop250FilmInfo(GetTop250FilmFragment.this,pageCount*PAGE_SIZE,PAGE_SIZE,true);
                            }
                        },1000);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem=myLinearLayoutManager.findLastCompletelyVisibleItemPosition();
            }
        });
    }



}
