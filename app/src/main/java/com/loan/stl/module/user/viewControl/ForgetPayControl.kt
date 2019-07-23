package com.loan.stl.module.user.viewControl

import android.app.Activity
import android.text.TextUtils
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.loan.custom.TimeButton
import com.loan.stl.R
import com.loan.stl.common.AppConfig
import com.loan.stl.common.BundleKeys
import com.loan.stl.common.Constant
import com.loan.stl.common.RequestResultCode
import com.loan.stl.module.user.dataModel.receive.PassRec
import com.loan.stl.module.user.dataModel.sixSMS.SMSSixToken
import com.loan.stl.module.user.dataModel.submit.ForgotPaySub
import com.loan.stl.module.user.viewModel.ForgetPayVM
import com.loan.stl.network.HttpClient
import com.loan.stl.network.ResponseCallback
import com.loan.stl.network.SIXClient
import com.loan.stl.network.api.MineService
import com.loan.stl.network.api.UserService
import com.loan.stl.network.entity.HttpResult
import com.loan.stl.network.entity.HttpSixResult
import com.loan.stl.router.RouterUrl
import com.loan.stl.utils.RegularUtil
import com.loan.stl.utils.ToastUtils
import com.loan.stl.utils.Util
import retrofit2.Call
import retrofit2.Response

/**
author: russell
time: 2019-07-15:14:21
describe：忘记交易密码逻辑
 */
class ForgetPayControl(val timeButton:TimeButton,val type:Int) {
    var forgetPayVM=ForgetPayVM()

    private  var token:String?=null





    fun getCodeClick(view: View){
        val context=view.context
        if (TextUtils.isEmpty(forgetPayVM.phone)) {
            ToastUtils.toast(context.getString(R.string.input) + context.getString(
                R.string.forgot_phone_hint_step_1
            )
            )
            return
        }
        if (!RegularUtil.isPhoneLength(forgetPayVM.phone)) {
            ToastUtils.toast(R.string.forgot_phone_hint_step_1_error)
            return
        }


        val callCode = SIXClient.getService(UserService::class.java)
            .getCodeSixToken(AppConfig.SIX_APP_KEY, AppConfig.SIX_APP_SECRET)
        callCode.enqueue(object : ResponseCallback<HttpSixResult<SMSSixToken>>() {
            override fun onSuccess(
                call: Call<HttpSixResult<SMSSixToken>>,
                response: Response<HttpSixResult<SMSSixToken>>
            ) {
                token = response.body()!!.data.token
                @Suppress("NAME_SHADOWING")
                val callCode = SIXClient.getService(UserService::class.java)
                    .getCodeSixSend(forgetPayVM.phone!!, token!!)
                callCode.enqueue(object : ResponseCallback<HttpSixResult<Any>>() {
                    override fun onSuccess(
                        call: Call<HttpSixResult<Any>>,
                        response: Response<HttpSixResult<Any>>
                    ) {
                        timeButton.runTimer()
                    }
                })

            }
        })

    }




    fun submit(view: View){
        if (TextUtils.isEmpty(forgetPayVM.name)) {
            ToastUtils.toast(R.string.settings_forgot_pay_name_tip)
            return
        }
        if (TextUtils.isEmpty(forgetPayVM.no)) {
            ToastUtils.toast(R.string.settings_forgot_pay_no_tip)
            return
        }
        if (TextUtils.isEmpty(forgetPayVM.code)) {
            ToastUtils.toast(R.string.settings_forgot_pay_code_tip)
            return
        }
        val callCode = SIXClient.getService(UserService::class.java)
            .getCodeSixVerify(forgetPayVM.phone!!, token!!, forgetPayVM.code!!)
        callCode.enqueue(object : ResponseCallback<HttpSixResult<Any>>() {

            override fun onFailed(call: Call<HttpSixResult<Any>>, response: Response<HttpSixResult<Any>>) {
                //super.onFailed(call, response)
                ToastUtils.toast("验证码错误")
            }

            override fun onFailure(call: Call<HttpSixResult<Any>>, t: Throwable) {
               // super.onFailure(call, t)
                ToastUtils.toast("验证码错误")
            }

            override fun onSuccess(
                call: Call<HttpSixResult<Any>>,
                response: Response<HttpSixResult<Any>>
            ) {

                val sub = ForgotPaySub()
                sub.idNo=forgetPayVM.no
                sub.realName=forgetPayVM.name
                sub.vcode="0000"
                val callre = HttpClient.getService(MineService::class.java).validateUser(sub)
                callre.enqueue(object : ResponseCallback<HttpResult<PassRec>>() {
                    override   fun onSuccess(call: Call<HttpResult<PassRec>>, response: Response<HttpResult<PassRec>>) {
                        if (response.body()!!.data.pass==true) {
                            val activity = Util.getActivity(view)
                            if (Constant.NUMBER_1 == type) {
                                ARouter.getInstance().build(RouterUrl.UPDATE_PASSWORD)
                                    .withInt(BundleKeys.TYPE,Constant.NUMBER_3)
                                    .navigation()
                                activity.finish()
                            } else {
                                activity.setResult(Activity.RESULT_OK)
                                activity.finish()
                            }
                        } else {
                            ToastUtils.toast(response.body()!!.msg)
                        }
                    }
                })
            }
        })

    }
}