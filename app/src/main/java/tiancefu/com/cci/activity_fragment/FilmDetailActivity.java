package tiancefu.com.cci.activity_fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tiancefu.com.cci.R;
import tiancefu.com.cci.activity_base.BaseActivity;
import tiancefu.com.cci.activity_base.EasyRecyclerViewAdapter;
import tiancefu.com.cci.adapter.CastAdapter;
import tiancefu.com.cci.bean.filmdetail.FilmDetail;
import tiancefu.com.cci.bean.filmdetail.FilmPeople;
import tiancefu.com.cci.bean.top250.Casts;
import tiancefu.com.cci.bean.top250.Directors;
import tiancefu.com.cci.presenter.DouBanFilmPresenter;
import tiancefu.com.cci.utils.DisplayImgUtils;
import tiancefu.com.cci.utils.ThemeUtils;
import tiancefu.com.cci.viewinterface.film.GetFilmDetail;
import tiancefu.com.cci.widget.MyLinearLayoutManager;

/**
 * Created by dsblt on 2017/5/4.
 */

public class FilmDetailActivity extends BaseActivity implements GetFilmDetail {

    public static String EXTRA_ID="id";
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_rating)
    TextView tvRating;
    @BindView(R.id.tv_rating_num)
    TextView tvRatingNum;
    @BindView(R.id.tv_film_date_and_film_time)
    TextView tvDateAndFilmTime;
    @BindView(R.id.tv_film_type)
    TextView tvFIlmType;
    @BindView(R.id.tv_film_country)
    TextView tvFilmCountry;
    @BindView(R.id.tv_film_name)
    TextView tvName;
    @BindView(R.id.tv_more_info)
    TextView tvMoreInfo;
    @BindView(R.id.tv_discription)
    TextView tvDiscription;
    @BindView(R.id.iv_film)
    ImageView ivFilm;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private DouBanFilmPresenter douBanFilmPresenter;
    private String id;
    private Context context;
    private String alt;
    private List<FilmPeople> list=new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        ButterKnife.bind(this);
        applyKitKatTranslucency();
        context=this;
        initView();
        initData();
    }

    /**
     * 初始化视图
     */
    private void initView(){
        toolbar.setBackgroundColor(ThemeUtils.getThemeColor());
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivity();
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData(){
        Intent intent=getIntent();
        if(intent!=null){
            id=intent.getStringExtra(EXTRA_ID);
        }
        if(!TextUtils.isEmpty(id)){
            douBanFilmPresenter=new DouBanFilmPresenter(this);
            douBanFilmPresenter.getFilmDetailInfo(this,id);
        }
    }


    @Override
    public void getFilmDetailFailed() {

    }

    @Override
    public void getFilmDetailSuccess(FilmDetail filmDetail) {
        if(filmDetail.getImages()!=null && filmDetail.getImages().getLarge()!=null){
            DisplayImgUtils.getInstance().displayImg(context,filmDetail.getImages().getLarge(),ivFilm);
        }
        if(!TextUtils.isEmpty(filmDetail.getTitle())){
            tvTitle.setText(filmDetail.getTitle());
        }
        if(filmDetail.getRating()!=null){
            tvRating.setText("评分："+filmDetail.getRating().getAverage());
        }
        tvRatingNum.setText(filmDetail.getRatings_count()+"人评分");
        tvDateAndFilmTime.setText(filmDetail.getYear()+"年 出品");
        if(filmDetail.getCountries()!=null && filmDetail.getCountries().size()>0){
            tvFilmCountry.setText(filmDetail.getCountries().get(0));
        }
        if(filmDetail.getGenres()!=null && filmDetail.getGenres().size()>0){
            StringBuilder stringBuilder=new StringBuilder();
            for(String s:filmDetail.getGenres()){
                stringBuilder.append(s+"/");
            }
            tvFIlmType.setText(stringBuilder.toString().substring(0,stringBuilder.toString().length()-1));
        }
        tvDiscription.setText(filmDetail.getSummary());
        tvName.setText(filmDetail.getOriginal_title()+"[原名]");
        initFilmData(filmDetail);
        CastAdapter castAdapter=new CastAdapter(this);
        castAdapter.setmDatas(list);
        MyLinearLayoutManager myLinearLayoutManager=new MyLinearLayoutManager(this);
        recyclerView.setLayoutManager(myLinearLayoutManager);
        recyclerView.setAdapter(castAdapter);
        alt=filmDetail.getAlt();
        castAdapter.setmItemClickListener(new EasyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position, Object data) {
                Intent intent=new Intent(FilmDetailActivity.this,WebviewActivity.class);
                String alt=list.get(position).getAlt();
                intent.putExtra(WebviewActivity.EXTRA_URL,alt);
                startActivityByIntent(intent);
            }
        });




    }

    @Override
    public String setActName() {
        return null;
    }


    private void initFilmData(FilmDetail filmDetail){
        if(filmDetail.getDirectors()!=null&&filmDetail.getDirectors().size()>0){
           for (int i=0;i<filmDetail.getDirectors().size();i++){
               Directors directors=filmDetail.getDirectors().get(i);
               FilmPeople filmPeople=new FilmPeople();
               filmPeople.setAlt(directors.getAlt());
               filmPeople.setAvatars(directors.getAvatars());
               filmPeople.setId(directors.getId());
               filmPeople.setName(directors.getName());
               filmPeople.setType(1);
               list.add(filmPeople);
           }

        if(filmDetail.getCasts()!=null&&filmDetail.getCasts().size()>0){
            for(int i=0;i<filmDetail.getCasts().size();i++){
                Casts casts=filmDetail.getCasts().get(i);
                FilmPeople filmPeople=new FilmPeople();
                filmPeople.setAlt(casts.getAlt());
                filmPeople.setAvatars(casts.getAvatars());
                filmPeople.setId(casts.getId());
                filmPeople.setName(casts.getName());
                filmPeople.setType(2);
                list.add(filmPeople);
            }

        }



        }
    }

    @OnClick({R.id.iv_film,R.id.tv_more_info})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_film:
            case R.id.tv_more_info:
                Intent intent =new Intent(this,WebviewActivity.class);
                intent.putExtra(WebviewActivity.EXTRA_URL,alt);
                startActivityByIntent(intent);
                break;
        }
    }

}
