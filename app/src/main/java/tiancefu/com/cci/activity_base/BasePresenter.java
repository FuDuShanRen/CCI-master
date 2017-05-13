package tiancefu.com.cci.activity_base;

import android.content.Context;

import tiancefu.com.cci.api.ApiFactory;
import tiancefu.com.cci.api.DouBanApi;

/**公共Presenter,所有Presenter继承自此类
 * 因为Presenter层一般用于校验数据正确性,故该类用于封装常用的数据校验方法
 * 将网络请求移动到本层处理
 * Created by dsblt on 2017/5/1.
 */

public abstract class BasePresenter {
    public Context mcontext;
    public static final DouBanApi douBanApi= ApiFactory.getDouBanApiSingleInstance();

    public BasePresenter(Context context){
        mcontext=context;
    }


}
