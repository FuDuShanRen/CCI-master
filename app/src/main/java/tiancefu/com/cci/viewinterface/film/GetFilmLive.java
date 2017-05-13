package tiancefu.com.cci.viewinterface.film;

import tiancefu.com.cci.activity_base.BaseViewInterface;
import tiancefu.com.cci.bean.filmlive.FlimLive;

/**
 * Created by dsblt on 2017/5/1.
 */

public interface GetFilmLive extends BaseViewInterface {
     void getFilmLiveSuccess(FlimLive flimLive);

     void getFilmLiveFailed();

}
