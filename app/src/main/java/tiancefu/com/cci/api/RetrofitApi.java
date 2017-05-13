package tiancefu.com.cci.api;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import forezp.com.douyalibrary.utils.NetUtils;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import tiancefu.com.cci.utils.MyAPP;

/**Retrofit2框架相关配置，如缓存，retrofit2不支持缓存配置，需要借助OKhttp,这里借用Okhttp3
 * Created by dsblt on 2017/5/2.
 */

public class RetrofitApi {
    public DouBanApi douBanApiService;
    public static final String douBanBaseURL="https://api.douban.com/";

    public DouBanApi getDouBanApiService(){
        return  douBanApiService;
    }

    RetrofitApi(){
        File httpCacheDir=new File(MyAPP.mcontext.getCacheDir(),"responses");
        int cacheSize=10*1024*1024;
        Cache cache=new Cache(httpCacheDir,cacheSize);

        //使用OKhttp作为缓存
        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .cache(cache)
                .build();

        Retrofit retrofit_douban=new Retrofit.Builder()
                .baseUrl(douBanBaseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        douBanApiService=retrofit_douban.create(DouBanApi.class);

    }

    //用于打印测试请求URL是否正确
    private HttpLoggingInterceptor getLogging() {
        //日志显示级别
        HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("zcb","OkHttp====Message:"+message);
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }



     Interceptor  REWRITE_CACHE_CONTROL_INTERCEPTOR=chain ->{
            //通过 CacheControl 控制缓存数据
            CacheControl.Builder cacheBuilder=new CacheControl.Builder();
            cacheBuilder.maxAge(0, TimeUnit.SECONDS);
            cacheBuilder.maxStale(365,TimeUnit.DAYS);
            CacheControl cacheControl=cacheBuilder.build();
            //设置拦截器
            Request request=chain.request();
            if(!NetUtils.checkNetWorkIsAvailable(MyAPP.mcontext)){
                request=request.newBuilder()
                        .cacheControl(cacheControl)
                        .build();
            }
            Response response=chain.proceed(request);
            if(NetUtils.checkNetWorkIsAvailable(MyAPP.mcontext)){
                int maxAge=0;
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control","public ,max-age="+maxAge)
                        .build();
            }else{
                int maxStale=60*60*24*28;
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale="+maxStale)
                        .build();
            }
    };

}
