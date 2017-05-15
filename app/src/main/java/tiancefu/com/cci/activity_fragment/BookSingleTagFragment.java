package tiancefu.com.cci.activity_fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import forezp.com.douyalibrary.utils.ScreenUtils;
import tiancefu.com.cci.R;
import tiancefu.com.cci.activity_base.BaseFragment;
import tiancefu.com.cci.adapter.BookSingleTagAdapter;
import tiancefu.com.cci.api.BookApiUtils;
import tiancefu.com.cci.bean.book.BookRoot;
import tiancefu.com.cci.bean.book.Books;
import tiancefu.com.cci.presenter.DouBanBookPresenter;
import tiancefu.com.cci.utils.ThemeUtils;
import tiancefu.com.cci.viewinterface.book.GetBookView;
import tiancefu.com.cci.widget.MyItemDecoration;

/**
 * Created by dsblt on 2017/5/13.
 */

public class BookSingleTagFragment extends BaseFragment implements GetBookView,SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.book_singletag_rec)
    RecyclerView recyclerView;
    @BindView(R.id.book_singletag_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private int position;

    private DouBanBookPresenter douBanBookPresenter;
    private BookSingleTagAdapter bookSingleTagAdapter;
    private List<Books>  booksList;
    private int lastVisableItem;
    private List<String> tagList;
    private GridLayoutManager gridLayoutManager;

    public static BookSingleTagFragment newInstance(int position,String title){
        Bundle bundle =new Bundle();
        bundle.putInt("position",position);
        bundle.putString("title",title);
        BookSingleTagFragment bookSingleTagFragment=new BookSingleTagFragment();
        bookSingleTagFragment.setArguments(bundle);
        return bookSingleTagFragment;
    }






    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_book_sin_tag,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        booksList=new ArrayList<>();
        Bundle bundle=getArguments();
        if(bundle!=null){
            position=bundle.getInt("position");
        }
        String[] tagTiles=BookApiUtils.getApiTag(position);
        tagList= Arrays.asList(tagTiles);
        scrollRecyclerView();
        douBanBookPresenter= new DouBanBookPresenter(getActivity());
        gridLayoutManager =new GridLayoutManager(getActivity(),3);
        swipeRefreshLayout.setColorSchemeColors(ThemeUtils.getThemeColor());
        swipeRefreshLayout.setOnRefreshListener(this);
        String tag=BookApiUtils.getRandomTag(tagList);
        douBanBookPresenter.searchBook(this,tag,false);
        bookSingleTagAdapter=new BookSingleTagAdapter(getActivity());
        recyclerView.setLayoutManager(gridLayoutManager);
        MyItemDecoration myItemDecoration=new MyItemDecoration(ScreenUtils.dipToPx(getActivity(),10),ScreenUtils.dipToPx(getActivity(),10),ScreenUtils.dipToPx(getActivity(),10),ScreenUtils.dipToPx(getActivity(),0));
        recyclerView.addItemDecoration(myItemDecoration);
        recyclerView.setAdapter(bookSingleTagAdapter);
    }


    private void scrollRecyclerView(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                    lastVisableItem=gridLayoutManager.findLastVisibleItemPosition();
                    if(gridLayoutManager.getItemCount()==1){
                        if(bookSingleTagAdapter!=null){
                            bookSingleTagAdapter.updateLoadStatus(BookSingleTagAdapter.LOAD_NONE);
                        }
                        return ;
                    }

                    if(gridLayoutManager.getItemCount()==lastVisableItem+1){
                        if (bookSingleTagAdapter!=null){
                            bookSingleTagAdapter.updateLoadStatus(BookSingleTagAdapter.LOAD_PULL_TO);
                            bookSingleTagAdapter.updateLoadStatus(BookSingleTagAdapter.LOAD_MORE);
                        }

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                String tag=BookApiUtils.getRandomTag(tagList);
                              douBanBookPresenter.searchBook(BookSingleTagFragment.this,tag,true);
                            }
                        },1000);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisableItem=gridLayoutManager.findLastVisibleItemPosition();
            }
        });

    }




    @Override
    public void getBookFailed() {

    }

    @Override
    public void getBookView(BookRoot bookRoot, boolean isLoadMore) {
        if(isLoadMore){
            booksList.addAll(bookRoot.getBooks());
        }else{
            booksList.clear();
            booksList.addAll(bookRoot.getBooks());
        }
        bookSingleTagAdapter.setList(booksList);
        bookSingleTagAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {

            tagList=Arrays.asList(BookApiUtils.getApiTag(position));
            String tag=BookApiUtils.getRandomTag(tagList);
            douBanBookPresenter.searchBook(this,tag,false);
            swipeRefreshLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(false);
                }
            },2000);

    }



}