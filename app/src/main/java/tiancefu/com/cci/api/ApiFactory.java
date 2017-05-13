package tiancefu.com.cci.api;

/**DouBanApi单例模式
 * Created by dsblt on 2017/5/2.
 */

public class ApiFactory {

    protected static final  Object monitor=new Object();
    static DouBanApi douBanApiSingleInstance=null;

   public static DouBanApi getDouBanApiSingleInstance(){
       synchronized(monitor){
           if(douBanApiSingleInstance==null){
               douBanApiSingleInstance=new RetrofitApi().getDouBanApiService();
           }
           return douBanApiSingleInstance;
       }

    }
}

