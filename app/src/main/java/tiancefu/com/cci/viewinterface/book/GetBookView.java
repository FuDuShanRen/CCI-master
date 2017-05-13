package tiancefu.com.cci.viewinterface.book;

import tiancefu.com.cci.activity_base.BaseViewInterface;
import tiancefu.com.cci.bean.book.BookRoot;

/**
 * Created by dsblt on 2017/5/1.
 */

public interface GetBookView extends BaseViewInterface {
    void getBookView(BookRoot bookRoot,boolean isLoadMore);
    void getBookFailed();
}
