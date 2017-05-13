package tiancefu.com.cci.viewinterface.music;

import tiancefu.com.cci.activity_base.BaseViewInterface;
import tiancefu.com.cci.bean.music.Musics;

/**
 * Created by dsblt on 2017/5/1.
 */

public interface GetMusicByID extends BaseViewInterface {
    void getMusicByIDSuccess(Musics musics);
    void getMusicByIDFailed();
}
