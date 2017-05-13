package tiancefu.com.cci.bean.filmusbox;

import java.util.List;

import tiancefu.com.cci.bean.top250.Subjects;

/**FilmUsBox 北美票房榜
 * Created by dsblt on 2017/4/29.
 */

   public class FilmUsBox {

    private String date;//排行榜日期范围

    private List<Subjects> subjects ;

    private String title;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
