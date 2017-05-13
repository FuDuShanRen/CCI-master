package tiancefu.com.cci.viewinterface.book;

import tiancefu.com.cci.activity_base.BaseViewInterface;
import tiancefu.com.cci.bean.book.Books;

/**
 * Created by dsblt on 2017/5/1.
 */

public interface GetBookDetailView extends BaseViewInterface {
    void getBookDetailView(Books books);
    void getBookDetailFailed();

}
