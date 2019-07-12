package com.loan.stl.utils;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

/**
 * @author mumuji
 * @version 1.0.0
 * @date 2017/10/16
 * @description
 */
public class JavaScriptInterface {
//    private HtmlFragment mHtmlFragment;

    public  static final int TYPE_FIRST_FRAGMENT = 100;
    public  static final int TYPE_HOT_FRAGMENT = 101;

    Context mContext;
    private int mType;


    public JavaScriptInterface(Context c, int type) {
        mContext = c;
        mType = type;
    }


    @JavascriptInterface
    public void toFirstLoad(String url) {
        Intent intent;
//        if(mType == TYPE_FIRST_FRAGMENT){
//             intent= new Intent(mContext, FirstLoadingThreeAct.class);
//        }else{
//            intent = new Intent(mContext, HotLoadingActivity.class);
//        }
//        intent.putExtra(BundleKeys.URL,url);
//        mContext.startActivity(intent);
//        mHtmlFragment.startActivity(CommonalityActivity.FRAGMENT_REGISTER);
    }

    /**
     * 去登录界面
     */
    @JavascriptInterface
    public void toLogin() {
//        mHtmlFragment.startActivity(CommonalityActivity.FRAGMENT_LOGIN);
    }

}
