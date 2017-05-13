package tiancefu.com.cci.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**自定义自动适应高度与宽度的LinearLayoutManager
 * ????????
 * Created by dsblt on 2017/5/7.
 */

public class MyLinearLayoutManager extends LinearLayoutManager{
    private static final String TAG=MyLinearLayoutManager.class.getSimpleName();

    private int[] mMeasureDimension = new int[2];

    public MyLinearLayoutManager(Context context){
        super(context);
    }

    public  MyLinearLayoutManager(Context context,int oriention,boolean reverseLayout){
        super(context,oriention,reverseLayout);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        final int widthMode = View.MeasureSpec.getMode(widthSpec);
        final int heightMode = View.MeasureSpec.getMode(heightSpec);
        final int widthSize = View.MeasureSpec.getSize(widthSpec);
        final int heightSize = View.MeasureSpec.getSize(heightSpec);

        Log.i(TAG, "onMeasure called. \nwidthMode " + widthMode
                + " \nheightMode " + heightSpec
                + " \nwidthSize " + widthSize
                + " \nheightSize " + heightSize
                + " \ngetItemCount() " + getItemCount());

        int width = 0;
        int height = 0;

        for (int i = 0; i < getItemCount(); i++) {
            measureScrapChild(recycler, i,
                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.UNSPECIFIED),
                    mMeasureDimension);
            if(getOrientation()==HORIZONTAL){
                width=width+mMeasureDimension[0];
                if(i==0){
                    height=mMeasureDimension[1];
                }
            }else{
                height=height+mMeasureDimension[1];
                if(i==0){
                    width=mMeasureDimension[0];
                }
            }
        }

        switch (widthMode){
            case View.MeasureSpec.EXACTLY:
                width=widthSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }

        switch (heightMode){
            case View.MeasureSpec.EXACTLY:
                height=heightSize;
            case View.MeasureSpec.AT_MOST:
            case View.MeasureSpec.UNSPECIFIED:
        }

        setMeasuredDimension(width,height);



    }





    private void measureScrapChild (RecyclerView.Recycler recycler,int position,int widthSpec,
                                        int heightSpec, int[] measureDimension ){
        try {
            View view=recycler.getViewForPosition(0);

            if (view!=null){
                RecyclerView.LayoutParams layoutParams=(RecyclerView.LayoutParams)view.getLayoutParams();
                int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec,getPaddingLeft()+getPaddingRight(),layoutParams.width);
                int childHeightSpec=ViewGroup.getChildMeasureSpec(heightSpec,getPaddingTop()+getPaddingBottom(),layoutParams.hashCode());
                view.measure(childWidthSpec,childHeightSpec);
                measureDimension[0]=view.getMeasuredWidth()+ layoutParams.leftMargin+layoutParams.rightMargin;
                measureDimension[1]=view.getMeasuredHeight()+layoutParams.topMargin+layoutParams.bottomMargin;
                recycler.recycleView(view);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }

    }



    }




