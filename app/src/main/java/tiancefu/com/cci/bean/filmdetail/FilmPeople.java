package tiancefu.com.cci.bean.filmdetail;

import tiancefu.com.cci.bean.top250.Avatars;

/**FilmPeople 电影人简介
 * Created by dsblt on 2017/4/29.
 */

public class FilmPeople {
    private String alt;

    private Avatars avatars;

    private String name;

    private String id;
    private int type;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
