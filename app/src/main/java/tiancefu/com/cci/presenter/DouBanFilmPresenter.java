package tiancefu.com.cci.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import rx.android.schedulers.AndroidSchedulers;

import rx.schedulers.Schedulers;
import tiancefu.com.cci.activity_base.BasePresenter;
import tiancefu.com.cci.bean.filmdetail.FilmDetail;
import tiancefu.com.cci.bean.filmlive.FlimLive;

import tiancefu.com.cci.bean.top250.Root;
import tiancefu.com.cci.viewinterface.film.GetFilmDetail;
import tiancefu.com.cci.viewinterface.film.GetFilmLive;
import tiancefu.com.cci.viewinterface.film.GetTop250View;


/**
 * Created by dsblt on 2017/5/2.
 */

public class DouBanFilmPresenter extends BasePresenter {

    public DouBanFilmPresenter(Context context){
        super(context);
    }

    private void loadError(Throwable throwable){
        throwable.printStackTrace();
        Toast.makeText(mcontext,"网络连接失败...",Toast.LENGTH_SHORT).show();
        Log.i("Failed","获取信息失败！");
    }

    /**
     * 获取正在热映信息列表
     * @param gfl
     * @param flimLive
     * @param context
     */
    private void disPlayFilmLiveList(GetFilmLive gfl, FlimLive flimLive,Context context){
        if(flimLive==null){
            gfl.getFilmLiveFailed();
        }else {
            gfl.getFilmLiveSuccess(flimLive);
            Log.e("热映信息列表",flimLive.toString());
        }
    }


    /**
     * 通过RxJava异步实现Retrofit网络请求---获取正在热映
     * @param gfl
     */
    public void getFilmLive(final GetFilmLive gfl){

           douBanApi.getLiveFlim()
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(filmLive ->{
                       disPlayFilmLiveList(gfl,filmLive,mcontext);
                   },this::loadError);
    }

    /**
     * 获取影片条目详细信息
     * @param getFilmDetail
     * @param filmDetail
     * @param context
     */
    private void disPlayFilmDetail(GetFilmDetail getFilmDetail, FilmDetail filmDetail,Context context){
        if(filmDetail==null){
            getFilmDetail.getFilmDetailFailed();
        }else {
            getFilmDetail.getFilmDetailSuccess(filmDetail);
            Log.e("FilmLive", filmDetail.toString());
        }
    }

    /**
     * 通过RxJava异步实现Retrofit网络请求---获取影片条目详细信息
     * @param getFilmDetail
     */
    public void getFilmDetailInfo(GetFilmDetail getFilmDetail, String id){
        douBanApi.getFilmDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(filmDetail -> {
                    disPlayFilmDetail(getFilmDetail,filmDetail,mcontext);
                },this::loadError);
    }

    /**
     * 获取前排行前250的电影
     * @param getTop250View
     * @param root
     * @param isLoadmore
     */
    private void disPlayTop250FilmList(GetTop250View getTop250View, Root root, Boolean isLoadmore){
        Log.e("T250", root.toString());
        getTop250View.getTop250InfoSuccss(root,isLoadmore);


    }

    /**
     * 通过RxJava异步实现Retrofit网络请求---获取前排行前250的电影
     * @param getTop250View
     * @param start
     * @param count
     * @param isLoadmore
     */
    public void getTop250FilmInfo(GetTop250View getTop250View, int start, int count,Boolean isLoadmore){
        douBanApi.getTop250info(start,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(root -> {
                    disPlayTop250FilmList(getTop250View,root,isLoadmore);
                },this::loadError);
    }

    /**
     * 获取欧美榜电影信息
     * @param getUsBox
     * @param filmUsBox

    private void disPlayFilmUsBox(GetUsBox getUsBox, FilmUsBox filmUsBox) {
        if (filmUsBox==null) {
            getUsBox.getUSBoxFailed();
        }else{
            getUsBox.getUSBoxSuccess(filmUsBox);
        }
    }

    public void getFilmUsBoxInfo(GetUsBox getUsBox){
       Observable<FilmUsBox> filmUsBoxObservable=douBanApi.getFilmUsBox();
        filmUsBoxObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<FilmUsBox>() {
                    @Override
                    public void call(FilmUsBox filmUsBox) {
                        disPlayFilmUsBox(getUsBox,filmUsBox);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        loadError(throwable);
                    }
                });

        douBanApi.getFilmUsBox()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(filmUsBox -> {
                    disPlayFilmUsBox(getUsBox,filmUsBox);
                },this::loadError);
    }
     */
}
