package tiancefu.com.cci.bean.top250;

import java.util.List;

/**Subjects 搜索结果列表
 * Created by dsblt on 2017/4/29.
 */

public class Subjects {

    private Rating rating;//影评评分

    private List<String> genres;//影片类型，最多三个

    private String title;//排行榜名称

    private List<Casts> casts;//主演

    private int collect_count;//看过人数

    private String original_title;//原名

    private String subtype;//条目分类, movie或者tv

    private List<Directors> directors;//导演

    private String year;//年代

    private Images images;//电影海报图，分别提供288px x 465px(大)，96px x 155px(中) 64px x 103px(小)尺寸

    private String alt;//条目页URL

    private String id;//条目id

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Casts> getCasts() {
        return casts;
    }

    public void setCasts(List<Casts> casts) {
        this.casts = casts;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public List<Directors> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Directors> directors) {
        this.directors = directors;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
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
