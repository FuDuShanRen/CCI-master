package tiancefu.com.cci;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import tiancefu.com.cci.activity_base.BaseActivity;
import tiancefu.com.cci.activity_fragment.MainActivity;


/**
 * Created by dsblt on 2017/4/9.
 */

public class Entry_Activity extends BaseActivity {

    @BindView(R.id.entry_image)
    ImageView entryImage;

    private static final int ANIMATION_TIME=2000;
    private static final float SCALE_END=1.13f;
    private static final int[] IMAGES={
            R.drawable.ic_screen_default,
            R.drawable.splash0,
            R.drawable.splash1,
            R.drawable.splash2,
            R.drawable.splash3,
            R.drawable.splash4,
            R.drawable.splash5,
            R.drawable.splash6,
            R.drawable.splash7,
            R.drawable.splash8,
            R.drawable.splash9,
            R.drawable.splash10,
            R.drawable.splash11,
            R.drawable.splash12,
            R.drawable.splash13,
            R.drawable.splash14,
            R.drawable.splash15,
            R.drawable.splash16
         };

    @Override
    public String setActName() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_main);
        ButterKnife.bind(this);
        //applyKitKatTranslucency();
        setTranslucentStatus(true);//???可以不要
        Random random=new Random(SystemClock.elapsedRealtime());
        entryImage.setImageResource(IMAGES[random.nextInt(IMAGES.length)]);//随机产生一个IMAGES长度以类的数，用该数位置的图片
        Observable.timer(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        startAnim();
                    }
                });
    }


    private void startAnim(){
        ObjectAnimator animatorX=ObjectAnimator.ofFloat(entryImage,"scaleX",1f,SCALE_END);
        ObjectAnimator animatorY=ObjectAnimator.ofFloat(entryImage,"scaleY",1f,SCALE_END);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setDuration(ANIMATION_TIME).play(animatorX).with(animatorY);
        animatorSet.start();

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                   startActivity(new Intent(Entry_Activity.this,MainActivity.class));
                   Entry_Activity.this.finish();
                   overridePendingTransition(R.anim.activity_in_anim,R.anim.activity_out_anim);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

}
