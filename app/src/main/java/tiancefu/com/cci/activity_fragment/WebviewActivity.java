package tiancefu.com.cci.activity_fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tiancefu.com.cci.R;
import tiancefu.com.cci.activity_base.BaseActivity;
import tiancefu.com.cci.utils.ThemeUtils;

/**
 * Created by dsblt on 2017/5/7.
 */

public class WebviewActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webview)
    WebView webView;
    private String url;
    @BindView(R.id.tv_title)
    TextView toolbar_title;
    public static String EXTRA_URL;


    @Override
    public String setActName() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        applyKitKatTranslucency();
        initData();
        initView();

    }



    private void initData(){
        Intent intent=getIntent();
        if(intent!=null){
            url=intent.getStringExtra(EXTRA_URL);
        }
    }

    private void initView() {
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setBackgroundColor(ThemeUtils.getThemeColor());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivity();
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                toolbar.setTitle(title);
                toolbar_title.setText("");
                super.onReceivedTitle(view, title);
            }
        });
        webView.loadUrl(url);
    }
}
