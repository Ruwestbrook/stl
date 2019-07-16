package com.loan.stl.module.user.viewControl

import android.view.View
import com.loan.stl.R
import com.loan.stl.module.user.dataModel.receive.OauthTokenMo
import com.loan.stl.module.user.dataModel.submit.LoginSub
import com.loan.stl.module.user.ui.activity.UserLogic
import com.loan.stl.module.user.viewModel.LoginVM
import com.loan.stl.network.HttpClient
import com.loan.stl.network.NetworkUtil
import com.loan.stl.network.ResponseCallback
import com.loan.stl.network.api.UserService
import com.loan.stl.network.entity.HttpResult
import com.loan.stl.utils.LogUtils
import com.loan.stl.utils.RegularUtil
import com.loan.stl.utils.SPreferences.SharedInfo
import com.loan.stl.utils.ToastUtils
import com.loan.stl.utils.Util
import retrofit2.Call
import retrofit2.Response

/**
author: russell
time: 2019-07-12:13:53
describe：登录逻辑
 */
class LoginControl {
     var loginVM:LoginVM = LoginVM()

    init {
        loginVM.phone="15757071829"
        loginVM.code="123456"
    }

    /*
     用户协议
     */
    fun protocol(view: View){

    }
    /*
    用户隐私
     */
    fun privacy(view: View){

    }
    /*
     登录
     */
    fun doLogin(view: View){
        if (!RegularUtil.isPhone(loginVM.phone)) {
            ToastUtils.toast(R.string.forgot_phone_hint_step_1_error)
            return
        }
        if (loginVM.code == null || loginVM.code == "") {
            ToastUtils.toast(R.string.forgot_code_hint_step_1_error)
            return
        }
        if(!loginVM.protocol){
            ToastUtils.toast("请先同意用户协议！")
            return
        }

        val sub= LoginSub()
        sub.id=loginVM.phone
        sub.pwd="qwer123456"
        //"userId":4074,"token":"0caed270a77343a2a6a7ffcbbbd14a9f",
        // "refreshToken":"c6be32c9564c4a339565a367ea4d3c56"
        val loginCall=HttpClient.getService(UserService::class.java).doLogin(sub)
        loginCall.enqueue(object :ResponseCallback<HttpResult<OauthTokenMo>>(){
            override fun onSuccess(
                call: Call<HttpResult<OauthTokenMo>>?,
                response: Response<HttpResult<OauthTokenMo>>?
            ) {
                val oauthTokenMo=response?.body()!!.data
                if(oauthTokenMo!=null){
                   UserLogic.login(Util.getActivity(view),oauthTokenMo)
                }
            }

        })

    }
    /*
     手机号码清除
     */
    fun clear(view: View){
        loginVM.phone=null
    }
    /*
    是否同意协议
     */
    fun agree(view: View){
        LogUtils.d("点击了是否同意")
        loginVM.protocol=!loginVM.protocol
    }





}