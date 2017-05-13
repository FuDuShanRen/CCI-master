package tiancefu.com.cci.activity_base;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import forezp.com.douyalibrary.utils.T;
import tiancefu.com.cci.R;

/**
 * Created by dsblt on 2017/4/29.
 */

public class BaseFragment extends Fragment implements BaseViewInterface {

    Toast mToast;

    public void Toast(String msg){
        T.show(getActivity(),msg);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void showProgress() {
        showProgress(" ");
    }

    @Override
    public void showProgress(String message) {

    }

    @Override
    public void onServerFail(String msg) {

    }

    @Override
    public void showTheToast(String message) {
          if(mToast==null){
              mToast=Toast.makeText(getActivity(),message,Toast.LENGTH_LONG);
          }else{
              mToast.setText(message);
              mToast.setDuration(Toast.LENGTH_LONG);
          }
          mToast.show();
    }

    @Override
    public void showTheToast(int resID) {
         showTheToast(getString(resID));
    }

    @Override
    public void cancelProgress() {

    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    protected  void startTheActivityByIntent(Intent intent){
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.activity_in_anim,R.anim.activity_out_anim);
    }

}
