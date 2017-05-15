package tiancefu.com.cci.bean.book;

import java.io.Serializable;

/**
 * Created by dsblt on 2017/4/29.
 */

public class Tags implements Serializable{
     private int count;

     private String title;

     private String name;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
