package tiancefu.com.cci.adapter;

import android.content.Context;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import forezp.com.douyalibrary.utils.ScreenUtils;
import tiancefu.com.cci.R;
import tiancefu.com.cci.activity_fragment.FilmDetailActivity;
import tiancefu.com.cci.bean.top250.Root;
import tiancefu.com.cci.bean.top250.Subjects;
import tiancefu.com.cci.utils.DisplayImgUtils;

/**
 * Created by dsblt on 2017/5/7.
 */

public class Top250FilmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private Root mfilmRoot;
    private int status=1;
    public static final int LOAD_MORE=0;
    public static final int LOAD_PULL_TO=1;
    public static final int LOAD_NONE=2;
    public static final int LOAD_END=3;
    public static final int TYPE_TOP=-1;
    public static final int TYPE_FOOTER=-2;

    public Top250FilmAdapter(Context context,Root root){
        this.context=context;
        this.mfilmRoot= root;
    }

    /**
     * ？？？？？？？？
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if(position+1==getItemCount()){
            return TYPE_FOOTER;
        }else{
            return position;
        }

    }

    @Override
    public int getItemCount() {
        return mfilmRoot.getSubjectses().size()+1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_FOOTER){
            View view= View.inflate(parent.getContext(), R.layout.activity_view_footer,null);
            return new FooterViewHolder(view);
        }else{
            View view = View.inflate(parent.getContext(),R.layout.item_film_us,null);
            return new FilmUSBoxViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof FooterViewHolder){
            FooterViewHolder footerViewHolder=(FooterViewHolder) holder;
            footerViewHolder.bindItem();
        }else if(holder instanceof FilmUSBoxViewHolder){
            FilmUSBoxViewHolder filmUSBoxViewHolder=(FilmUSBoxViewHolder)holder;
            filmUSBoxViewHolder.bindItem(mfilmRoot.getSubjectses().get(position),position);
        }

    }

    class FooterViewHolder extends RecyclerView.ViewHolder{
       @BindView(R.id.tc_load_promt)
       TextView tvLoadPrompt;
       @BindView(R.id.progress)
       ProgressBar progressBar;

        public FooterViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
            LinearLayoutCompat.LayoutParams params =
                    new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ScreenUtils.dipToPx(context,40));
            itemView.setLayoutParams(params);
        }

        private void bindItem(){
            switch(status){
                case LOAD_MORE:
                    progressBar.setVisibility(View.VISIBLE);
                    tvLoadPrompt.setText("正在加载...");
                    itemView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_PULL_TO:
                    progressBar.setVisibility(View.GONE);
                    tvLoadPrompt.setText("上拉加载更多");
                    itemView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_NONE:
                    progressBar.setVisibility(View.GONE);
                    tvLoadPrompt.setText("已无更多加载");
                    break;
                case LOAD_END:
                    itemView.setVisibility(View.GONE);
                default:
                    break;
            }
        }


    }

    class FilmUSBoxViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_film_pic)
        ImageView ivFilm;
        @BindView(R.id.tv_film_chinese)
        TextView tvFilmChnName;
        @BindView(R.id.tv_film_english)
        TextView tvFilmEngName;
        @BindView(R.id.tv_film_grades)
        TextView tvFilmGrade;
        @BindView(R.id.tv_rank)
        TextView tvRank;
        @BindView(R.id.filmlist_item_view)
        LinearLayout filmListItemView;

        public FilmUSBoxViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }

        private void bindItem(final Subjects subjects, int position){
            DisplayImgUtils.getInstance().displayImg(context, subjects.getImages().getLarge(),ivFilm);
            tvFilmChnName.setText(subjects.getTitle());
            tvFilmEngName.setText(subjects.getOriginal_title());
            tvFilmGrade.setText("评分："+ subjects.getRating().getAverage());
            if(position<9){
                tvRank.setText("0"+(position+1));
            }else {
                tvRank.setText(""+(position+1));
            }
            filmListItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FilmDetailActivity.class);
                    intent.putExtra(FilmDetailActivity.EXTRA_ID, subjects.getId());
                    context.startActivity(intent);
                }
            });
        }
    }

    public void updateLoadStatus(int status){
       this.status=status;
        notifyDataSetChanged();
    }

}
