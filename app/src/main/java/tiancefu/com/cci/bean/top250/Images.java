package tiancefu.com.cci.bean.top250;

import java.io.Serializable;

/**Images 电影海报图，分别提供288px x 465px(大)，96px x 155px(中) 64px x 103px(小)尺寸
 * Created by dsblt on 2017/4/29.
 */

public class Images implements Serializable{
        private String large="";
        private String small;
        private String medium;

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}
