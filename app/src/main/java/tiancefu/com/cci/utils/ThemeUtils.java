package tiancefu.com.cci.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.widget.Switch;

import tiancefu.com.cci.R;


/**
 * Created by dsblt on 2017/4/20.
 */

public class ThemeUtils {
    private static int defaultThemeColor= Color.rgb(251,91,129);
    private static Context context=MyAPP.mcontext;

    public static void setThemeColor(int color){
        SharedPreferences.Editor editor=context.getSharedPreferences("ThemeColor",Context.MODE_PRIVATE).edit();
        editor.putInt("color",color);
        editor.commit();
    }

    public static void setThemePosition(int position){
        SharedPreferences.Editor editor=context.getSharedPreferences("ThemeColor",Context.MODE_PRIVATE).edit();
        editor.putInt("positon",position);
        editor.commit();

    }

    public static int getThemeColor(){
        SharedPreferences sharedPreferences=context.getSharedPreferences("ThemeColor",Context.MODE_PRIVATE);
        return sharedPreferences.getInt("color",defaultThemeColor);
    }

    public static int getThemePositon(){
        SharedPreferences sharedPreferences=context.getSharedPreferences("ThemeColor",Context.MODE_PRIVATE);
        return sharedPreferences.getInt("position",0);
    }

    public static int getToolBarColor(){
        return getThemeColor();
    }

    /**
     * 获取导航侧边栏主题颜色设置按钮栏按钮颜色信息
     * @return
     */
    public static ColorStateList getNaviItemIconColorList(){
        int position=getThemePositon();
        Resources resources=(Resources)context.getResources();
        ColorStateList colorStateList;
        switch (position){
            case 0:
                colorStateList=(ColorStateList)resources.getColorStateList(R.color.theme_redbase_nav_menu_icon_selector);
                return colorStateList;
            case 1:
                colorStateList=(ColorStateList)resources.getColorStateList(R.color.theme_blue_nav_menu_icon_selector);
                return colorStateList;
            case 2:
                colorStateList=(ColorStateList)resources.getColorStateList(R.color.theme_lightblue_nav_menu_icon_selector);
                return colorStateList;
            case 3:
                colorStateList=(ColorStateList)resources.getColorStateList(R.color.theme_black_nav_menu_icon_selector);
                return colorStateList;
            case 4:
                colorStateList=(ColorStateList)resources.getColorStateList(R.color.theme_teal_nav_menu_icon_selector);
                return colorStateList;
            case 5:
                colorStateList=(ColorStateList)resources.getColorStateList(R.color.theme_brown_nav_menu_icon_selector);
                return colorStateList;
            case 6:
                colorStateList=(ColorStateList)resources.getColorStateList(R.color.theme_green_nav_menu_icon_selector);
                return colorStateList;
            case 7:
                colorStateList=(ColorStateList)resources.getColorStateList(R.color.theme_red_nav_menu_icon_selector);
                return colorStateList;
        }
        colorStateList=(ColorStateList)resources.getColorStateList(R.color.theme_redbase_tablayout_text_colorlist);
        return colorStateList;
    }

    public static ColorStateList getTabTextColorStateList(){
        ColorStateList colorStateList;
        Resources resources=(Resources)context.getResources();
        int position =getThemePositon();
        switch(position){
            case 0:
                colorStateList=(ColorStateList)resources.getColorStateList(R.color.theme_redbase_tablayout_text_colorlist);
                return colorStateList;
            case 1:
                colorStateList=(ColorStateList)resources.getColorStateList(R.color.theme_blue_tablayout_text_colorlist);
                return colorStateList;
            case 2:
                colorStateList=(ColorStateList)resources.getColorStateList(R.color.theme_lightblue_tablayout_text_colorlist);
                return colorStateList;
            case 3:
                colorStateList=(ColorStateList)resources.getColorStateList(R.color.theme_black_tablayout_text_colorlist);
                return colorStateList;
            case 4:
                colorStateList=(ColorStateList)resources.getColorStateList(R.color.theme_teal_tablayout_text_colorlist);
                return colorStateList;
            case 5:
                colorStateList=(ColorStateList)resources.getColorStateList(R.color.theme_brown_tablayout_text_colorlist);
                return colorStateList;
            case 6:
                colorStateList=(ColorStateList)resources.getColorStateList(R.color.theme_green_tablayout_text_colorlist);
                return colorStateList;
            case 7:
                colorStateList=(ColorStateList)resources.getColorStateList(R.color.theme_red_tablayout_text_colorlist);
                return colorStateList;

           }
           colorStateList=(ColorStateList)resources.getColorStateList(R.color.theme_redbase_tablayout_text_colorlist);
        return  colorStateList;
    }


}
