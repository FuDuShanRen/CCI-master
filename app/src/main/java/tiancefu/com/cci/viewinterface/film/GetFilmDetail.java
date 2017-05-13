package tiancefu.com.cci.viewinterface.film;

import tiancefu.com.cci.activity_base.BaseViewInterface;
import tiancefu.com.cci.bean.filmdetail.FilmDetail;

/**
 * Created by dsblt on 2017/5/1.
 */

public interface GetFilmDetail extends BaseViewInterface {
     void getFilmDetailSuccess(FilmDetail filmDetail);

     void getFilmDetailFailed();

}
