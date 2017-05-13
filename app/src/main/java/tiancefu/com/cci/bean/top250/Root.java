package tiancefu.com.cci.bean.top250;

import java.util.List;

/**Root 电影简略信息
 * Created by dsblt on 2017/4/29.
 */

public class Root {

    private int count;

    private int start;

    private int total;

    private String title;

    private List<Subjects> subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Subjects> getSubjectses() {
        return subjects;
    }

    public void setSubjectses(List<Subjects> subjectses) {
        this.subjects = subjectses;
    }

    @Override
    public String toString() {
        return "Root{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", filmsubjects=" + subjects +
                ", title='" + title + '\'' +
                '}';
    }
}
