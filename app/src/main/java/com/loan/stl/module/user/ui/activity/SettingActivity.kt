package com.loan.stl.module.user.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.cazaea.sweetalert.SweetAlertDialog
import com.loan.stl.R
import com.loan.stl.common.BaseActivity
import com.loan.stl.common.BundleKeys
import com.loan.stl.common.CommonType
import com.loan.stl.common.Constant
import com.loan.stl.module.mine.dataModel.CommonRec
import com.loan.stl.module.user.dataModel.receive.OauthTokenMo
import com.loan.stl.module.user.dataModel.receive.TradeStateRec
import com.loan.stl.network.HttpClient
import com.loan.stl.network.NetworkUtil
import com.loan.stl.network.ResponseCallback
import com.loan.stl.network.api.CommonService
import com.loan.stl.network.api.MineService
import com.loan.stl.network.entity.HttpResult
import com.loan.stl.network.entity.ListData
import com.loan.stl.router.RouterUrl
import com.loan.stl.utils.DialogUtils
import com.loan.stl.utils.LogUtils
import com.loan.stl.utils.SPreferences.SharedInfo
import com.loan.stl.utils.device.DeviceInfoUtils
import kotlinx.android.synthetic.main.activity_setting.*
import retrofit2.Call
import retrofit2.Response

/**
 * author: russell
 * time: 2019-07-11:11:53
 * describe：设置页面
 */
@Route(path = RouterUrl.USER_SETTING_PAGE)
class SettingActivity : BaseActivity() {

    var payType = 0 // 0: 设置交易密码 1:修改交易密码 2:修改交易密码(不可忘记密码)
    var commonRec:CommonRec?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setPageTitle("设置",false)
        version.text= DeviceInfoUtils.getVersionName(this)
        requestData()

       val token:OauthTokenMo?=SharedInfo.getInstance().getEntity(OauthTokenMo::class.java)

        DialogUtils.showDialog(
            this, this.resources.getString(R.string.settints_pay_success
            ),SweetAlertDialog.SUCCESS_TYPE,
            { sweetAlertDialog ->
                sweetAlertDialog.dismiss()
            },
            false
        )

    }


    override fun onResume() {
        super.onResume()
        reqPayPassword()
    }

    private fun reqPayPassword() {
        val call=HttpClient.getService(MineService::class.java).getTradeState()
        call.enqueue(object :ResponseCallback<HttpResult<TradeStateRec>>(){
            override fun onSuccess(
                call: Call<HttpResult<TradeStateRec>>?,
                response: Response<HttpResult<TradeStateRec>>?
            ) {
                val rec:TradeStateRec?=response?.body()!!.data
                if(rec?.setable==true){
                    payType=Constant.NUMBER_0
                    pwd_title.text="设置交易密码"
                }else{
                    payType= if(rec?.forgetable==true) Constant.NUMBER_1 else Constant.NUMBER_2
                    pwd_title.text="修改交易密码"

                }
            }
        })
    }
    /*
        修改登录密码
     */
    @Suppress("UNUSED_PARAMETER")
    fun setLoginPwd( view: View){
        ARouter.getInstance().build(RouterUrl.UPDATE_LOGIN_PASSWORD)
            .navigation()
    }

    /*
     修改或者设置交易密码
     */
    @Suppress("UNUSED_PARAMETER")
    fun setPayPwd(view: View){
        ARouter.getInstance().build(RouterUrl.UPDATE_PASSWORD)
            .withInt(BundleKeys.TYPE,payType)
            .navigation()

    }
    /*
     关于我们
     */
    @Suppress("UNUSED_PARAMETER")
    fun aboutUs(view: View){

        if(commonRec==null)
            return

        LogUtils.d(commonRec==null)
        LogUtils.d("URL="+CommonType.getUrl(commonRec?.value))
        ARouter.getInstance().build(RouterUrl.WEBVIWE_FACE)
            .withString(BundleKeys.URL, CommonType.getUrl(commonRec?.value))
            .withString(BundleKeys.TITLE,  commonRec?.name)
            .navigation()
    }

    /*
     意见反馈
     */
    @Suppress("UNUSED_PARAMETER")
    fun idea(view: View){
        ARouter.getInstance().build(RouterUrl.FEEDBACK_PAGE).navigation()
    }

    /*
      用户退出
     */
    @Suppress("UNUSED_PARAMETER")
    fun exit(view: View){
            UserLogic.signOut()
    }

    private fun requestData(){
        val call=HttpClient.getService(CommonService::class.java).h5List()
        NetworkUtil.showCutscenes(call)
        call.enqueue(object :ResponseCallback<HttpResult<ListData<CommonRec>>>(){
            override fun onSuccess(
                call: Call<HttpResult<ListData<CommonRec>>>?,
                response: Response<HttpResult<ListData<CommonRec>>>?
            ) {
                val list=response?.body()!!.data
                list.list.forEach {
                    if(it.code== CommonType.ABOUNT_US)
                        commonRec=it
                }
            }


        })


    }

}
