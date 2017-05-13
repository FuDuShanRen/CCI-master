package tiancefu.com.cci.activity_base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import tiancefu.com.cci.R;
import tiancefu.com.cci.utils.ThemeUtils;

/**
 * Created by dsblt on 2017/4/10.
 */

public  abstract class BaseActivity extends BaseAppActivity implements BaseViewInterface {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 弹出短时长Toast
     * @param msg
     */
    public void toast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT);
    }


    /**
     * 根据传入的Activity启动
     * @param cls
     */
    protected void startTheActivity(Class<?> cls){
        Intent intent=new Intent(this,cls);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_in_anim,R.anim.activity_out_anim);
    }

    /**
     * 根据Intent启动Activity
     * @param intent
     */
    protected void startActivityByIntent(Intent intent){
         startActivity(intent);
        overridePendingTransition(R.anim.activity_in_anim,R.anim.activity_out_anim);
    }

    /**
     * 关闭界面
     */
    public void backActivity(){
        finish();
        overridePendingTransition(R.anim.activity_back_in,R.anim.activity_back_out);
    }

    @TargetApi(19)//高低版本兼容问题
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        Log.i("BIT",bits+"/"+winParams.flags);
        if (on) {
            winParams.flags |= bits;
            Log.i("BIT",bits+"/"+winParams.flags);
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 沉浸式状态栏设置
     */
    public void applyKitKatTranslucency(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            setTranslucentStatus(true);
            SystemBarTintManager systemBarTintManager=new SystemBarTintManager(this);
            systemBarTintManager.setStatusBarTintEnabled(true);
            systemBarTintManager.setStatusBarTintColor(ThemeUtils.getThemeColor());

        }
    }

    /**
     * 根据资源文件设置沉浸式状态栏
     * @param res
     */
    public void applyKitKatTranslucency(int res){
       if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
           setTranslucentStatus(true);
           SystemBarTintManager systemBarTintManager=new SystemBarTintManager(this);
           systemBarTintManager.setStatusBarTintEnabled(true);
           systemBarTintManager.setStatusBarTintColor(res);
       }
    }



    @Override
    public void showProgress(String message) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void cancelProgress() {

    }

    @Override
    public void showTheToast(int resID) {

    }

    @Override
    public void showTheToast(String message) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void onServerFail(String msg) {

    }


}
