package tiancefu.com.cci.viewinterface.music;

import tiancefu.com.cci.activity_base.BaseViewInterface;
import tiancefu.com.cci.bean.music.MusicRoot;
import tiancefu.com.cci.bean.music.Musics;

/**
 * Created by dsblt on 2017/5/1.
 */

public interface GetMusicViewByTag extends BaseViewInterface {
    void getMusicViewByTagSuccess(MusicRoot musicRoot,boolean isLoadMore);
    void getMusicViewByTagFailed();
}
