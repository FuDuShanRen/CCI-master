package tiancefu.com.cci.activity_base;

import android.content.Context;

/**
 * Created by dsblt on 2017/4/10.
 */

public interface BaseViewInterface {

    /**
     * 显示信息的可取消的进度条
     * @param message
     */
    void showProgress(String message);

    /**
     * 不显示信息的可取消的进度条
     */
    void showProgress();

    /**
     * 取消进度条
     */
    void cancelProgress();

    /**
     * 根据资源ID（resID)弹出Toast
     * @param resID
     */
    void showTheToast(int resID);

    /**
     * 根据字符串弹出Toast
     * @param message
     */
    void showTheToast(String message);

    /**
     * 获取上下文
     * @return
     */
    Context getContext();

    void onServerFail(String msg);




}
