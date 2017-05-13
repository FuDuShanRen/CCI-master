package tiancefu.com.cci.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import forezp.com.douyalibrary.utils.ScreenUtils;
import tiancefu.com.cci.R;
import tiancefu.com.cci.activity_base.EasyRecyclerViewAdapter;
import tiancefu.com.cci.bean.top250.Subjects;
import tiancefu.com.cci.utils.DisplayImgUtils;

/**
 * Created by dsblt on 2017/5/2.
 */

public class DouBanFilmLiveAdapter extends EasyRecyclerViewAdapter<Subjects>{

    Context context;

    public DouBanFilmLiveAdapter(Context context){
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film_live,parent,false);
        return new FilmLiveViewHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, Subjects data) {
        FilmLiveViewHolder filmLiveViewHolder=(FilmLiveViewHolder)viewHolder;
        filmLiveViewHolder.tvFilmName.setText(data.getTitle());
        ViewGroup.LayoutParams layoutParams=filmLiveViewHolder.ivFilm.getLayoutParams();
        int width= ScreenUtils.getScreenWidthDp(context);
        int ivwidth=(width-ScreenUtils.dipToPx(context,80))/3;
        double ivheight=((420.)/300.0)*ivwidth;
        layoutParams.width=ivwidth;
        layoutParams.height=(int)ivheight;
        filmLiveViewHolder.ivFilm.setLayoutParams(layoutParams);
        if(data.getImages()!=null && !TextUtils.isEmpty(data.getImages().getLarge())){
            DisplayImgUtils.getInstance().displayImg(context,data.getImages().getLarge(),filmLiveViewHolder.ivFilm);
        }
        if(!TextUtils.isEmpty(""+data.getRating().getAverage())){
            filmLiveViewHolder.tvFilmGrage.setText("评分："+String.valueOf(data.getRating().getAverage()));
        }else{
            filmLiveViewHolder.tvFilmGrage.setText("暂无 评分");
        }


    }

    class FilmLiveViewHolder extends EasyViewHolder{
       @BindView(R.id.iv_film)
       ImageView ivFilm;
       @BindView(R.id.tv_film_name)
       TextView tvFilmName;
       @BindView(R.id.tv_film_grade)
       TextView tvFilmGrage;

       public  FilmLiveViewHolder(View view){
           super(view);
           ButterKnife.bind(this,view);
        }
    }


}
