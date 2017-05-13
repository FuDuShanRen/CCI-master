package tiancefu.com.cci.bean.top250;

/**Rating 评分
 * Created by dsblt on 2017/4/29.
 */

public class Rating {
    private int max;//最高分
    private int min;//最低分
    private double average;//平均评分
    private int stars;//评星数

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
