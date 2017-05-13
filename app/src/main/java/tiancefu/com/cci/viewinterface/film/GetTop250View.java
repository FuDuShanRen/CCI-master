package tiancefu.com.cci.viewinterface.film;

import tiancefu.com.cci.bean.top250.Root;

/**
 * Created by dsblt on 2017/4/27.
 */

public interface GetTop250View {
    //成功获取前250名电影信息
    void getTop250InfoSuccss(Root root, boolean isLoadMore);

    //获取前250名电影失败
    void getTop250InfoFailed();

}
