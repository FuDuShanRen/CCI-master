package tiancefu.com.cci.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tiancefu.com.cci.R;
import tiancefu.com.cci.activity_base.EasyRecyclerViewAdapter;
import tiancefu.com.cci.bean.filmdetail.FilmPeople;
import tiancefu.com.cci.utils.DisplayImgUtils;

/**
 * Created by dsblt on 2017/5/5.
 */

public class CastAdapter extends EasyRecyclerViewAdapter<FilmPeople> {

    Context context;

    public CastAdapter(Context context){
        this.context=context;
    }




    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film_people,parent,false);
        return new FilmCastViewHolder(view);
    }


    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, FilmPeople data) {
        FilmCastViewHolder filmCastViewHolder=(FilmCastViewHolder)viewHolder;
        DisplayImgUtils.getInstance().displayImg(context,data.getAvatars().getLarge(),((FilmCastViewHolder) viewHolder).ivAvatar);
        filmCastViewHolder.tvChnName.setText(data.getName());
        if(data.getType()==1){
            filmCastViewHolder.tvAvatarType.setText("[导演]");
        }else{
            filmCastViewHolder.tvAvatarType.setText("[演员]");
        }
    }

    class FilmCastViewHolder extends EasyViewHolder{
        @BindView(R.id.iv_avatar)
        ImageView ivAvatar;
        @BindView(R.id.tv_chn_name)
        TextView tvChnName;
        @BindView(R.id.tv_avatar_type)
        TextView tvAvatarType;

        public FilmCastViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }


}
