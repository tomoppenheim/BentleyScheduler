package com.example.tuffy_josh.termproject;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class webviewclasses extends MainActivity implements View.OnClickListener {
    //instance variables
    private String url="http://www.bentley.edu/offices/registrar/undergraduate-courses";
    private Button homeButton;
    private WebView webView;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.weblayout);
        //linking variable names to id names

        homeButton = (Button) findViewById(R.id.home);
        homeButton.setOnClickListener(this);
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            //instead of opening in chrome or web view browser it will open in webview widget in the the app under the edit text and go buttom
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }

        });
    }


    // go button is set to open what is type in the edit text box in a browser assuming website link is correct
    public void onClick(View v) throws SecurityException {
        finish();
    }





    //the back key navigates back to the previous web page
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    //when user closes the weblookup it toast that they have done so


}