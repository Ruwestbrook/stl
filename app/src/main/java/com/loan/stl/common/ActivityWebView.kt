package com.loan.stl.common

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.webkit.*
import androidx.annotation.RequiresApi
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.loan.stl.R
import com.loan.stl.network.UrlUtils
import com.loan.stl.router.RouterUrl
import com.loan.stl.utils.LogUtils
import com.loan.stl.utils.SPreferences.SharedInfo
import java.net.URISyntaxException
import java.util.HashMap

/**
author: russell
time: 2019-07-14:10:04
describe：
 */
@Route(path = RouterUrl.WEBVIWE_FACE)
class ActivityWebView:BaseActivity() {


   private val mHandler = Handler()

    /** 网页标题 */
    @JvmField
    @Autowired(name = BundleKeys.TITLE)
    var webTitle: String? = null

    @JvmField
    @Autowired(name = BundleKeys.URL)
    var webUrl:String? = null

    @JvmField
    @Autowired(name = BundleKeys.DATA)
    var webData :String?=null

    @JvmField
    @Autowired(name = BundleKeys.HTML)
    var webHtml :String?=null

    var webview:WebView?=null

    @SuppressLint("SetJavaScriptEnabled", "AddJavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        setContentView(R.layout.activity_web_view)
        webview=findViewById(R.id.webView)
        setPageTitle(webTitle)
//        val urlTemp = SharedInfo.getInstance().getValue(BundleKeys.URL, "") as String
//        if (!TextUtils.isEmpty(urlTemp)) {
//            webUrl = urlTemp
//            SharedInfo.getInstance().saveValue(BundleKeys.URL, "")
//        }

        if(!TextUtils.isEmpty(webUrl) && !TextUtils.isEmpty(webData))
            webview?.postUrl(webUrl,webData?.toByteArray())
        else{
            val sign = SharedInfo.getInstance().getValue(BundleKeys.SIGN, "") as String
            if (!TextUtils.isEmpty(sign)) {
                SharedInfo.getInstance().saveValue(BundleKeys.SIGN, "")
                val map = HashMap<String, String>()
                map[Constant.TOKEN] = UrlUtils.getInstance().getToken()
                map[Constant.SIGNA] = sign
                webview?.loadUrl(webUrl, map)
            } else {
                webview?.loadUrl(webUrl)
            }
        }


        //添加网页设置

        val webSettings=webview?.settings
        //不支持缩放
        webSettings?.setSupportZoom(false)
        webSettings?.useWideViewPort = false
        webSettings?.loadWithOverviewMode = true
        // 设置WebView属性，能够执行Javascript脚本
        webSettings?.javaScriptEnabled = true
        webSettings?.savePassword = false
        webSettings?.domStorageEnabled = true
        webSettings?.defaultTextEncodingName = "utf-8"


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings?.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        webview?.addJavascriptInterface(WebReturn(), "webReturn")
        webview?.webViewClient = MyWebViewClient()
        webview?.webChromeClient = MyWebChromeClient()

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            setResult(RESULT_OK)
            this.finish()
        }
    }

    override fun onBackPressed() {
        setResult(RESULT_OK)
        super.onBackPressed()
    }


    private inner class WebReturn {
        @JavascriptInterface
        fun webReturn(resCode: String, resMsg: String) {
            mHandler.post {
                // 开户  register_success
                // 授权  auth_success
                // 充值成功 recharge_success
                // 提现成功 cash_success
                // 普通投资 invest_success
                // 债权转让投资处理成功 bond_success
                // 变现投资处理成功 realize_success
                // 解绑 delBindBank_success
                println("****************************")
                println("resCode = $resCode")
                println("resMsg = $resMsg")
                println("****************************")
                //ToastUtil.toast(resMsg);

                /*if ("register_success".equals(resCode)) {
                Routers.openForResult(activity, RouterUrl.getRouterUrl(RouterUrl.Mine_RealnameSucceed), 0);
            }*/
                val intent = Intent()
                intent.putExtra(BundleKeys.CODE, resCode)
                intent.putExtra(BundleKeys.MSG, resMsg)
                setResult(RequestResultCode.RES_ZMXY, intent)
                finish()
            }
        }
    }

    private inner class MyWebViewClient : WebViewClient() {
        override fun onReceivedSslError(
            view: WebView,
            handler: SslErrorHandler, error: SslError
        ) {
            // 　　//handler.cancel(); 默认的处理方式，WebView变成空白页
            handler.proceed() // 接受证书
            // handleMessage(Message msg); 其他处理

        }

        @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if (url.contains("api/operatorReturnback.htm")) {
                setResult(RequestResultCode.RES_PHONE)
                finish()
            }
            if (url.startsWith("tel:")) {
                val intent = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse(url)
                )
                startActivity(intent)
            } else if (url.startsWith("sms:")) {
                val intent = Intent(Intent.ACTION_SENDTO)
                val sms = url.split("\\?".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                intent.type = "vnd.android-dir/mms-sms"
                intent.data = Uri.parse(sms[0])
                if (sms.size >= 2) {
                    val body = sms[1].split("=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    intent.putExtra("sms_body", body[1])
                }
                startActivity(intent)
            } else if (url.startsWith("intent://")) {
                val intent: Intent
                try {
                    intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
                    // forbid launching activities without BROWSABLE
                    // category
                    intent.addCategory("android.intent.category.BROWSABLE")
                    // forbid explicit call
                    intent.component = null
                    // forbid intent with selector intent
                    intent.selector = null
                    // start the activity by the intent
                    startActivityIfNeeded(intent, -1)
                } catch (e: URISyntaxException) {
                    e.printStackTrace()
                }

                return true
            } else {
                println("url--$url")
                view.loadUrl(url)
            }
            return false
        }

    }

    private inner class MyWebChromeClient : WebChromeClient() {
        // JS的提示框
        override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
            // 构建一个Builder来显示网页中的alert对话框
            val builder = AlertDialog.Builder(view.context)
            builder.setTitle(view.context.getString(R.string.dialog_title))
            builder.setMessage(message)
            builder.setPositiveButton(android.R.string.ok) { _, _ -> result.confirm() }

            builder.setCancelable(false)
            builder.create()
            builder.show()
            return true
        }

        // JS调用和反调用
        override fun onJsPrompt(
            view: WebView,
            url: String,
            message: String,
            defaultValue: String,
            result: JsPromptResult
        ): Boolean {
            if (message == "1") {
                // 解析参数defaultValue
                // 调用java方法并得到结果
            }
            // 返回结果
            result.confirm("result")

            /*
            function showHtmlCallJava() {
                var ret = prompt( "1", "param1;param2" );
                // ret值即为java传回的”result”
                // 根据返回内容作相应处理
            }
            */
            return true
        }


    }
}