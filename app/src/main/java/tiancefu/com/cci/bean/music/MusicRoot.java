package tiancefu.com.cci.bean.music;

import java.util.List;

/**
 * Created by dsblt on 2017/4/29.
 */

public class MusicRoot {
    private int count;

    private int start;

    private int total;

    private List<Musics> musics ;

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

    public List<Musics> getMusics() {
        return musics;
    }

    public void setMusics(List<Musics> musics) {
        this.musics = musics;
    }
}
