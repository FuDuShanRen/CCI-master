package tiancefu.com.cci.bean.top250;

/**Casts 主演 ，最多可获得4个，数据结构为影人的简化描述
 * Created by dsblt on 2017/4/29.
 */

public class Casts {
    private String alt;//	条目页URL
    private Avatars avatars;//影人图像
    private String name;//中文名
    private String id;//条目id

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Avatars getAvatars() {
        return avatars;
    }

    public void setAvatars(Avatars avatars) {
        this.avatars = avatars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
