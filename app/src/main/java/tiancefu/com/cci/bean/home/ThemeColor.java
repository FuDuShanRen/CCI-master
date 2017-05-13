package tiancefu.com.cci.bean.home;

/**ThemeColor 主题颜色选择
 * Created by dsblt on 2017/4/29.
 */

public class ThemeColor {
    private int color;
    private boolean isChosed;

    public ThemeColor(int color){
         this.color=color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isChosed() {
        return isChosed;
    }

    public void setChosed(boolean chosed) {
        isChosed = chosed;
    }
}
