package tiancefu.com.cci.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import tiancefu.com.cci.activity_base.BasePresenter;
import tiancefu.com.cci.bean.book.BookRoot;
import tiancefu.com.cci.bean.book.Books;
import tiancefu.com.cci.viewinterface.book.GetBookDetailView;
import tiancefu.com.cci.viewinterface.book.GetBookView;

/**
 * Created by dsblt on 2017/5/13.
 */

public class DouBanBookPresenter extends BasePresenter {

    public DouBanBookPresenter(Context context){
        super(context);
    }

    private void loadError(Throwable throwable){
        throwable.printStackTrace();
        Toast.makeText(mcontext,"网络连接失败...",Toast.LENGTH_SHORT).show();
    }


    private void getFromSearchedBook(GetBookView getBookView, BookRoot bookRoot,boolean isLoadMore){
        Log.e( "getFromSearchedBook: ", bookRoot.toString());
        getBookView.getBookView(bookRoot,isLoadMore);
    }

    private void getBookDetail(GetBookDetailView getBookDetail, Books books){
        getBookDetail.getBookDetailView(books);
        Log.e( "getBookDetail: ",books.toString() );
    }

    public void searchBook(GetBookView getBookView,String Tag,boolean isLoadMore){
        douBanApi.searchBookByTag(Tag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( bookRoot ->{getFromSearchedBook(getBookView,bookRoot,isLoadMore);},this::loadError);
    }

    public void searchBookById(GetBookDetailView getBookDetail,String id){
        douBanApi.getBookDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(books ->{
                    getBookDetail(getBookDetail,books);
                },this::loadError);
    }
}
