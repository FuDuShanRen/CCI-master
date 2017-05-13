package tiancefu.com.cci.activity_base;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsblt on 2017/5/2.
 */

public abstract class EasyRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    final private static int TYPE_HEAD=0;
    final private static int TYPE_BODY=1;
    final private static int TYPE_FOOT=2;

    private View mHeaderView;
    private View mFooterView;

    private List<T> mDatas=new ArrayList<>();

    private OnItemClickListener mItemClickListener;
    private OnItemLongClickListener mItemLongClickListener;

    public EasyRecyclerViewAdapter(){

    }

    public EasyRecyclerViewAdapter(List<T> mDatas){
        this.mDatas=mDatas;
    }

    public void setmHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public void setmFooterView(View footerView) {
        mFooterView = footerView;
        notifyDataSetChanged();
    }

    public void setmItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setmItemLongClickListener(OnItemLongClickListener listener) {
        mItemLongClickListener = listener;
    }

    public void setmDatas(List<T> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public List<T> getmDatas(){
        return mDatas;
    }

    /**
     * 自定义一个ViewHolder
     */
    public class EasyViewHolder extends RecyclerView.ViewHolder{
        public EasyViewHolder(View itemView){
            super(itemView);
        }
    }

    public abstract RecyclerView.ViewHolder onCreate(ViewGroup parent,final int viewType);

    public abstract void onBind(RecyclerView.ViewHolder viewHolder,int RealPosition,T data);

    //子视图单击事件监听接口
    public interface OnItemClickListener<T>{
        public void OnItemClick(View view,int position,T data);
    }

    //子视图长按事件监听接口
    public interface OnItemLongClickListener<T>{
        public void OnItemLongClick(View view,int position,T data);
    }


    @Override
    public int getItemViewType(int position){
        if(mHeaderView==null && mFooterView==null){
            return TYPE_BODY;
        }
        if(mHeaderView!=null && position==0){
            return TYPE_HEAD;
        }
        if(mFooterView!=null && mHeaderView==null && position==mDatas.size()){
            return TYPE_FOOT;
        }
        if(mFooterView!=null && mHeaderView!=null && position==mDatas.size()+1){
            return TYPE_FOOT;
        }

        return TYPE_BODY;
    }

    /**
     * 创建新View，用于被LayoutManager所调用
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_HEAD){
            return new EasyViewHolder(mHeaderView);
        }
        if(viewType==TYPE_FOOT){
            return new EasyViewHolder(mFooterView);
        }
        return onCreate(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(getItemViewType(position)==TYPE_HEAD||getItemViewType(position)==TYPE_FOOT){
            return ;
        }

        final int dataPosition =getDataPosition(holder);
        final T data=mDatas.get(dataPosition);
        onBind(holder,dataPosition,data);

        if(mItemClickListener!=null){
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.OnItemClick(v,dataPosition,data);
            }
        });
        }

        if(mItemLongClickListener!=null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mItemLongClickListener.OnItemLongClick(v,position,data);
                    return true;
                }
            });
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager=recyclerView.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            ((GridLayoutManager)layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
                @Override
                public int getSpanSize(int position) {
                    int type=getItemViewType(position);
                    if(type==TYPE_HEAD||type==TYPE_FOOT)
                        return ((GridLayoutManager)layoutManager).getSpanCount();
                    return 1;
                }
            });
        }
    }

    /**
     * 当列表项出现在可视界面时调用
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams layoutParams=holder.itemView.getLayoutParams();
        if(layoutParams instanceof StaggeredGridLayoutManager.LayoutParams){
            StaggeredGridLayoutManager.LayoutParams params=(StaggeredGridLayoutManager.LayoutParams)layoutParams;
            if(mHeaderView!=null && holder.getLayoutPosition()==0){
                params.setFullSpan(true);
            }else if(mFooterView!=null && holder.getLayoutPosition()==getFooterPosition()){//????
                params.setFullSpan(true);
            }else{
                params.setFullSpan(false);
            }
        }
    }

    /**
     * 根据视图获取数据所处位置
     * @param holder
     * @return
     */
    public int getDataPosition(RecyclerView.ViewHolder holder){
       int position=holder.getLayoutPosition();
        return mHeaderView==null?position:position-1;
    }

    /**
     * 获取底部视图的位置
     * @return
     */
    private int getFooterPosition(){
        if(mFooterView==null){
            return -1;
        }
        if(mHeaderView==null){
            return mDatas.size();
        }
        if(mHeaderView!=null){
            return mDatas.size()+1;
        }
        return -1;

    }

    /**
     * 获取子视图数量
     * @return
     */
    @Override
    public int getItemCount() {
        if(mFooterView!=null && mHeaderView !=null){
            return mDatas.size()+2;
        }else if(mFooterView!=null || mHeaderView!=null){
            return mDatas.size()+1;
        }
        return mDatas.size();
    }
}
