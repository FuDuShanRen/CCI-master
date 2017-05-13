package tiancefu.com.cci.bean.book;

import java.io.Serializable;

/**
 * Created by dsblt on 2017/4/29.
 */

public class BookRating implements Serializable{

    private int max;

    private int numRaters;//评分人数

    private String average;//平均分

    private int min;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getNumRaters() {
        return numRaters;
    }

    public void setNumRaters(int numRaters) {
        this.numRaters = numRaters;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
