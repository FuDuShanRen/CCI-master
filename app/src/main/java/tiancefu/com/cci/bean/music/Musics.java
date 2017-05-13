package tiancefu.com.cci.bean.music;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsblt on 2017/4/29.
 */

public class Musics {
    private MusicRating rating=new MusicRating();

    private List<MusicAuthor> author=new ArrayList<>() ;

    private String alt_title="";

    private String image="";

    private List<MusicTags> tags =new ArrayList<>();

    private String mobile_link="";

    private MusicAttrs attrs=new MusicAttrs();

    private String title="";
    private String summary="";

    private String alt="";

    private String id;

    public MusicRating getRating() {
        return rating;
    }

    public void setRating(MusicRating rating) {
        this.rating = rating;
    }

    public List<MusicAuthor> getAuthor() {
        return author;
    }

    public void setAuthor(List<MusicAuthor> author) {
        this.author = author;
    }

    public String getAlt_title() {
        return alt_title;
    }

    public void setAlt_title(String alt_title) {
        this.alt_title = alt_title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<MusicTags> getTags() {
        return tags;
    }

    public void setTags(List<MusicTags> tags) {
        this.tags = tags;
    }

    public String getMobile_link() {
        return mobile_link;
    }

    public void setMobile_link(String mobile_link) {
        this.mobile_link = mobile_link;
    }

    public MusicAttrs getAttrs() {
        return attrs;
    }

    public void setAttrs(MusicAttrs attrs) {
        this.attrs = attrs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
