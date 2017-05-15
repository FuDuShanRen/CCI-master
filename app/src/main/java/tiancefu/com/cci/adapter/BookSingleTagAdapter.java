package tiancefu.com.cci.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import forezp.com.douyalibrary.utils.ScreenUtils;
import tiancefu.com.cci.R;
import tiancefu.com.cci.activity_fragment.BookDetailActivity;
import tiancefu.com.cci.bean.book.Books;
import tiancefu.com.cci.utils.DisplayImgUtils;

/**
 * Created by dsblt on 2017/5/13.
 */

public class BookSingleTagAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private int status =1;

    public static final int LOAD_MORE=0;
    public static final int LOAD_PULL_TO=1;
    public static final int LOAD_NONE=2;
    public static final int LOAD_END=3;
    public static final int TYPE_FOOTER=-2;

    private List<Books> list;

    public BookSingleTagAdapter(Context context){
        this.context=context;
        list=new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(getItemCount()==position+1){
            return  TYPE_FOOTER;
        }else{
            return position;
        }

    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_FOOTER){
            View view = View.inflate(parent.getContext(), R.layout.activity_view_footer,null);
            return new FooterViewHolder(view);
        }else{
            View view=View.inflate(parent.getContext(),R.layout.item_book_view,null);
            return new BookItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
          if(holder instanceof FooterViewHolder){
              FooterViewHolder footerViewHolder = (FooterViewHolder)holder;
              footerViewHolder.bindItem();
          }else if(holder instanceof BookItemViewHolder){
              BookItemViewHolder bookItemViewHolder = (BookItemViewHolder)holder;
              bookItemViewHolder.bindItem(list.get(position),position);
          }
    }


    class FooterViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.progress)
        ProgressBar progressBar;
        @BindView(R.id.tc_load_promt)
        TextView loadPrompt;

        public FooterViewHolder(View itemView){
            super(itemView);
           ButterKnife.bind(this,itemView);
           LinearLayoutCompat.LayoutParams params=new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dipToPx(context,40));
           itemView.setLayoutParams(params);
        }

        private void bindItem(){
            switch (status){
                case LOAD_MORE:
                    progressBar.setVisibility(View.VISIBLE);
                    loadPrompt.setText("正在加载...");
                    itemView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_PULL_TO:
                    progressBar.setVisibility(View.GONE);
                    loadPrompt.setText("上拉加载更多...");
                    itemView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_NONE:
                    progressBar.setVisibility(View.GONE);
                    loadPrompt.setText("已经到底了...");
                    break;
                case LOAD_END:
                    itemView.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }


    }


    class BookItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_book_pic_item)
        ImageView iv_book;
        @BindView(R.id.tv_book_name_item)
        TextView tv_bookName;
        @BindView(R.id.tv_book_grade_item)
        TextView tv_bookGrade;
        @BindView(R.id.ll_book_singletag)
        LinearLayout llBook;

        public BookItemViewHolder(View viewItem){
            super(viewItem);
            ButterKnife.bind(this,viewItem);
        }

        private void bindItem(Books books,int postion){
            ViewGroup.LayoutParams params=iv_book.getLayoutParams();
            int width=ScreenUtils.getScreenWidthDp(context);
            int ivWidth=(width-ScreenUtils.dipToPx(context,80))/3;
            params.width=ivWidth;
            double ivHeight=(420.0/300.0)*ivWidth;
            params.height=(int)ivHeight;
            iv_book.setLayoutParams(params);

            if(!TextUtils.isEmpty(books.getImages().getLarge())){
                DisplayImgUtils.getInstance().displayImg(context,books.getImages().getLarge(),iv_book);
            }

            if(!TextUtils.isEmpty(books.getRating().getAverage())){
                tv_bookGrade.setText("评分："+books.getRating().getAverage());
            }else{
                tv_bookGrade.setText("暂无评分！");
            }

            if(!TextUtils.isEmpty(books.getTitle())){
                tv_bookName.setText(books.getTitle());
            }


            llBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BookDetailActivity.class);
                    intent.putExtra("id",books.getId());
                    context.startActivity(intent);
                }
            });


        }


    }



    public void updateLoadStatus(int status){
         this.status=status;
        notifyDataSetChanged();
    }

    public List<Books> getList() {
        return list;
    }

    public void setList(List<Books> list) {
        this.list = list;
    }
}
