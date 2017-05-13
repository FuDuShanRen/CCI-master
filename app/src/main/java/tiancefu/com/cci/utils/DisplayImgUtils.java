package tiancefu.com.cci.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by dsblt on 2017/5/4.
 */

public class DisplayImgUtils {
    private static DisplayImgUtils instance;

    //空构造方法
    private DisplayImgUtils(){

    }

    /**
     * 单例模式
     * @return
     */
    public static DisplayImgUtils getInstance(){
        if(instance==null){
            instance=new DisplayImgUtils();
        }
        return  instance;
    }

    /**
     * 根据图片ur为ImageView加载图片
     * @param context
     * @param url
     * @param imageView
     */
    public void displayImg(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).into(imageView);
    }


}
