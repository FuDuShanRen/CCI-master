package tiancefu.com.cci.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by dsblt on 2017/4/26.
 */

public class MyAPP extends Application {
    public static Context mcontext;

    @Override
    public void onCreate() {
        super.onCreate();
        mcontext=getApplicationContext();
    }
}
