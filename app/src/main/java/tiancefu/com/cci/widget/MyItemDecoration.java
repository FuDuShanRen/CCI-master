package tiancefu.com.cci.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Item 装饰
 * Created by dsblt on 2017/5/4.
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration {
    private int left;
    private int right;
    private int top;
    private int buttom;

    public MyItemDecoration(int padding){
        this.left=padding;
        this.right=padding;
        this.top=padding;
        this.buttom=padding;
    }

    public MyItemDecoration(int left,int right,int top,int buttom){
        this.left=left;
        this.right=right;
        this.top=top;
        this.buttom=buttom;
    }

    /**
     * 实现功能效果，与padding相似
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=left;
        outRect.right=right;
        outRect.top=top;
        outRect.bottom=buttom;
    }

}
