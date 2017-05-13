package tiancefu.com.cci.bean.top250;

/**Directors 导演，数据结构为影人的简化描述
 * Created by dsblt on 2017/4/29.
 */

public class Directors {
    private String alt;//	条目页URL
    private String name;//中文名
    private String id;//条目id
    private Avatars avatars;//影人图像

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
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

    public Avatars getAvatars() {
        return avatars;
    }

    public void setAvatars(Avatars avatars) {
        this.avatars = avatars;
    }
}
