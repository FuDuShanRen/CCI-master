package tiancefu.com.cci.viewinterface.film;

import tiancefu.com.cci.activity_base.BaseViewInterface;
import tiancefu.com.cci.bean.filmusbox.FilmUsBox;

/**
 * Created by dsblt on 2017/5/1.
 */

public interface GetUsBox extends BaseViewInterface {

    void getUSBoxSuccess(FilmUsBox filmUsBox);

    void getUSBoxFailed();
}
