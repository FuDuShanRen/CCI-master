package tiancefu.com.cci.activity_base;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * Created by dsblt on 2017/4/13.
 * Activity类管理器
 */

public class ActivityManager {

    private static Stack<Activity> activityStack;
    private static ActivityManager singleInstance;

    public ActivityManager(){

    }

    /**
     * 单例模式
     * @return
     */
    public static ActivityManager getSingleInstance(){
        if(singleInstance==null){
            singleInstance=new ActivityManager();
        }
        return singleInstance;
    }

    /**
     * 刷新所有Activity
     */
    public void refreshActivity(){
        for (Activity activity:activityStack){
            activity.recreate();
        }

    }

    /**
     * 添加Activity到堆栈
     */
    public  void addActivity(Activity activity){
        if(activityStack==null){
            activityStack=new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 从堆栈移除Activity
     */
    public void removeActivity(Activity activity){
        activityStack.remove(activity);
    }

    /**
     * 获取当前Activity
     */
    public Activity currentActivity(){
       Activity activity=activityStack.lastElement();
        return activity;
    }

    /**
     * 获取指定类名的Activity
     * @param cls
     */
    public static Activity getActivity(Class<?> cls){
         if(activityStack!=null){
             for(Activity activity:activityStack){
                 if(activity.equals(cls)){
                     return activity;
                 }
             }
         }
         return null;
    }

    /**
     * 结束当前Activity
     */
    public void finishCurrentActivity(){
        Activity activity=activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     * @param activity
     */
    public void finishActivity(Activity activity){
        if(activity!=null&&!activity.isFinishing()){
            activityStack.remove(activity);
            activity.finish();
            activity=null;
        }

    }

    /**
     * 结束指定类名Activity
     * @param cls
     */
    public void finishActivity(Class<?> cls){
        if(activityStack!=null){
            for(Activity activity:activityStack){
                if(activity.equals(cls)){
                    finishActivity(activity);
                }
            }
        }
        activityStack.clear();
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity(){
        for(int i=0;i<activityStack.size();i++){
            if(activityStack.get(i)!=null){
                finishActivity(activityStack.get(i));
                break;
            }
        }

    }


    /**
     * 退出应用程序
     */
    public void exitApp(Context context){
          finishAllActivity();
        /**
         * 杀死该应用的进程
         */
          android.os.Process.killProcess(android.os.Process.myPid());
          System.exit(0);
    }


}
