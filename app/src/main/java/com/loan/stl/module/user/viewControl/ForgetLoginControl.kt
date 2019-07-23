package com.loan.stl.module.user.viewControl

import android.app.Activity
import android.text.TextUtils
import android.view.View
import com.cazaea.sweetalert.SweetAlertDialog
import com.loan.custom.TimeButton
import com.loan.stl.R
import com.loan.stl.common.AppConfig
import com.loan.stl.common.CommonType
import com.loan.stl.common.Constant
import com.loan.stl.module.user.dataModel.receive.ProbeSmsRec
import com.loan.stl.module.user.dataModel.sixSMS.SMSSixToken
import com.loan.stl.module.user.dataModel.submit.ForgotSub
import com.loan.stl.module.user.dataModel.submit.ValidateCodeSub
import com.loan.stl.module.user.viewModel.ForgetLoginVM
import com.loan.stl.network.HttpClient
import com.loan.stl.network.ResponseCallback
import com.loan.stl.network.SIXClient
import com.loan.stl.network.api.UserService
import com.loan.stl.network.entity.HttpResult
import com.loan.stl.network.entity.HttpSixResult
import com.loan.stl.network.tool.MDUtil
import com.loan.stl.utils.*
import retrofit2.Call
import retrofit2.Response

/**
author: russell
time: 2019-07-15:13:46
describe：忘记登录密码
 */
class ForgetLoginControl(val timeButton: TimeButton) {
    var forgetLoginVM=ForgetLoginVM()
    private var token: String? = null

    init {
        forgetLoginVM.first = true
        forgetLoginVM.second = false
    }
    
    

    fun getCodeClick(view: View){
        val context=view.context
        if (TextUtils.isEmpty(forgetLoginVM.phone)) {
            ToastUtils.toast(context.getString(R.string.input) + context.getString(
                    R.string.forgot_phone_hint_step_1
                )
            )
            return
        }
        if (!RegularUtil.isPhoneLength(forgetLoginVM.phone)) {
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
                       .getCodeSixSend(forgetLoginVM.phone!!, token!!)
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

    fun nextStep( view: View){
        val input =view.context.getString(R.string.input)
        if (TextUtils.isEmpty(forgetLoginVM.phone)) {
            ToastUtils.toast(input +view.context.getString(R.string.forgot_phone_hint_step_1))
            return
        }
        if (!RegularUtil.isPhoneLength(forgetLoginVM.phone)) {
            ToastUtils.toast(view.context.getString(R.string.forgot_phone_hint_step_1_error))
            return
        }
        if (TextUtils.isEmpty(forgetLoginVM.code)) {
            ToastUtils.toast(input +view.context.getString(R.string.forgot_pwd_code_step_1))
            return
        }

        val callCode = SIXClient.getService(UserService::class.java)
            .getCodeSixVerify(forgetLoginVM.phone!!, token!!, forgetLoginVM.code!!)
        callCode.enqueue(object : ResponseCallback<HttpSixResult<Any>>() {

            override fun onFailed(call: Call<HttpSixResult<Any>>, response: Response<HttpSixResult<Any>>) {
                super.onFailed(call, response)
                ToastUtils.toast("验证码错误")
            }

            override fun onFailure(call: Call<HttpSixResult<Any>>, t: Throwable) {
                super.onFailure(call, t)
                ToastUtils.toast("验证码错误")
            }

            override fun onSuccess(
                call: Call<HttpSixResult<Any>>,
                response: Response<HttpSixResult<Any>>
            ) {
                val sub = ValidateCodeSub()
                sub.phone=forgetLoginVM.phone
                sub.vcode="0000"
                sub.type= CommonType.FORGOT_CODE
                sub.signMsg=MDUtil.encode(MDUtil.TYPE.MD5, AppConfig.APP_KEY + "+" + AppConfig.APP_SECRET)
                val callre = HttpClient.getService(UserService::class.java).checkCode(sub)
                callre.enqueue(object : ResponseCallback<HttpResult<ProbeSmsRec>>() {
                    override   fun onSuccess(call: Call<HttpResult<ProbeSmsRec>>, response: Response<HttpResult<ProbeSmsRec>>) {
                        if (Constant.STATUS_10 != response.body()!!.data.state) {
                            ToastUtils.toast(response.body()!!.data.message)
                        } else {
                            forgetLoginVM.first=false
                            forgetLoginVM.second=true
                            forgetLoginVM.title=view.context.resources.getString(R.string.forgot_pwd_title_step_2)

                        }
                    }
                })
            }
        })


    }
    @Suppress("UNUSED_PARAMETER")
    fun submit(view: View){
        val input =view.context.getString(R.string.input)
        if (TextUtils.isEmpty(forgetLoginVM.pwd)) {
            ToastUtils.toast(view.context.getString(R.string.forgot_pwd_new_hint_step_2))
            return
        }
        if (TextUtils.isEmpty(forgetLoginVM.confirmPwd)) {
            ToastUtils.toast(input +view.context.getString(R.string.forgot_pwd_confirm_hint_step_2))
            return
        }
        if (!forgetLoginVM.confirmPwd.equals(forgetLoginVM.pwd)) {
            ToastUtils.toast(R.string.pwd_no_confirm)
            return
        }
        if (!InputCheck.checkPwd(forgetLoginVM.pwd) || !InputCheck.checkPwd(forgetLoginVM.confirmPwd)) {
            ToastUtils.toast(view.context.getString(R.string.settings_pwd_desc))
            return
        }


        val signMsg = MDUtil.encode(MDUtil.TYPE.MD5, AppConfig.APP_KEY + "+" + AppConfig.APP_SECRET)
        val sub = ForgotSub()
        sub.signMsg=signMsg
        sub.mobile=forgetLoginVM.phone
        sub.password=forgetLoginVM.pwd
        sub.verifyCode="0000"
        sub.confirmPassword=forgetLoginVM.confirmPwd
        val call = HttpClient.getService(UserService::class.java).forgetPwd(sub)
        call.enqueue(object : ResponseCallback<HttpResult<Any>>() {
            override fun onSuccess(call: Call<HttpResult<Any>>, response: Response<HttpResult<Any>>) {
                DialogUtils.showDialog(
                    Util.getActivity(view),
                    SweetAlertDialog.NORMAL_TYPE,
                    "密码修改成功!",
                    {
                        Util.getActivity(view).setResult(Activity.RESULT_OK)
                        Util.getActivity(view).finish()
                    },
                    false
                )
            }
        })


    }



}