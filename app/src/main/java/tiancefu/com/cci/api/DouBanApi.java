package tiancefu.com.cci.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import tiancefu.com.cci.bean.book.BookRoot;
import tiancefu.com.cci.bean.book.Books;
import tiancefu.com.cci.bean.filmdetail.FilmDetail;
import tiancefu.com.cci.bean.filmlive.FlimLive;
import tiancefu.com.cci.bean.filmusbox.FilmUsBox;
import tiancefu.com.cci.bean.music.MusicRoot;
import tiancefu.com.cci.bean.music.Musics;
import tiancefu.com.cci.bean.top250.Root;

/**豆瓣网络数据请求接口 使用retrofit2实现，此为接口类
 * Created by dsblt on 2017/5/1.
 */

public interface DouBanApi {

    /**
     * 获得热映榜电影信息
     * @return
     */
    @GET("v2/movie/in_theaters")
    Observable<FlimLive> getLiveFlim();

    /**
     * 北美榜单
     * @return
     */
    @GET("v2/movie/us_box")
    Observable<FilmUsBox> getFilmUsBox();

    /**
     * 电影排行榜前250名录
     * @param start
     * @param count
     * @return
     */
    @GET("v2/movie/top250")
    Observable<Root> getTop250info(@Query("start") int start, @Query("count") int count);

    /**
     * 根据条目id查询电影条目详情
     * @param id
     * @return
     */
    @GET("v2/movie/subject/{id}")
    Observable<FilmDetail> getFilmDetail(@Path("id") String id);

    /**
     * 根据tag获取图书
     * @param tag
     * @return
     */

    @GET("v2/book/search")
    Observable<BookRoot> searchBookByTag(@Query("tag")String tag);

    @GET("v2/book/{id}")
    Observable<Books> getBookDetail(@Path("id") String id);

    /**
     * 根据tag获取music｀
     * @param tag
     * @return
     */

    @GET("v2/music/search")
    Observable<MusicRoot> searchMusicByTag(@Query("tag")String tag);

    @GET("v2/music/{id}")
    Observable<Musics> getMusicDetail(@Path("id") String id);


}
