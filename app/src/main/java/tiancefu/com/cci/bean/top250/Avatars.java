package tiancefu.com.cci.bean.top250;

/**
 * avatars	影人头像，分别提供420px x 600px(大)，140px x 200px(中) 70px x 100px(小)尺寸
 * Created by dsblt on 2017/4/27.
 */

public class Avatars {
    private String large;//大图像
    private String medium;//中图像
    private String small;//小图像

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }
}
