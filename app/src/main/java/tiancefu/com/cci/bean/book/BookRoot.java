package tiancefu.com.cci.bean.book;

import java.util.List;

/**BookRoot 图书条目信息
 * Created by dsblt on 2017/4/29.
 */

public class BookRoot {
    private int count;//取结果的条数,默认为20，最大为100

    private int total;

    private int start;//取结果的offset,默认为0

    private List<Books> books;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }
}
