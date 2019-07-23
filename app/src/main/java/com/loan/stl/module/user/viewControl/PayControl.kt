package com.loan.stl.module.user.viewControl

import android.content.Context
import android.content.Intent
import android.text.Editable
import androidx.databinding.ObservableField
import com.cazaea.sweetalert.SweetAlertDialog
import com.loan.stl.R
import com.loan.stl.common.ActivityManage
import com.loan.stl.common.BundleKeys
import com.loan.stl.common.Constant
import com.loan.stl.common.RequestResultCode
import com.loan.stl.databinding.ActivityUpdatePayBinding
import com.loan.stl.module.user.dataModel.receive.PassRec
import com.loan.stl.module.user.dataModel.submit.UpdatePwdSub
import com.loan.stl.network.HttpClient
import com.loan.stl.network.ResponseCallback
import com.loan.stl.network.api.MineService
import com.loan.stl.network.entity.HttpResult
import com.loan.stl.utils.DialogUtils
import com.loan.stl.utils.SPreferences.SharedInfo
import com.loan.stl.utils.TextUtil
import retrofit2.Call
import retrofit2.Response

/**
author: russell
time: 2019-07-15:14:21
describe：修改交易密码逻辑
 */
class PayControl(val binding: ActivityUpdatePayBinding, types: Int,val context: Context) {
    private var payType: Int = 0 // 0: 设置交易密码 1:修改交易密码 2:修改交易密码(不可忘记密码) 3:重置交易密码
    private var step = Constant.NUMBER_0 //0: 输入旧密码 1: 输入新密码 : 2: 确认新密码
    var title: ObservableField<String> = ObservableField()
    var tips: ObservableField<String> = ObservableField()

    private var oldpwd: String? = null
    private var newpwd: String? = null
    init {
        payType=types
        initView()
    }


    fun setPayType(payType: Int) {
        this.payType = payType
        initView()
    }

    fun input(e: Editable) {
        if (!TextUtil.isEmpty(e.toString()) && e.toString().length == 6) {
            when (step) {
                Constant.NUMBER_0 -> reqCheckPwd(e.toString())
                Constant.NUMBER_1 -> {
                    newpwd = e.toString()
                    step = Constant.NUMBER_2
                    title.set(context.getString(R.string.settints_pay_update_confirm_title))
                    tips.set(context.getString(R.string.settints_pay_update_confirm_tips))
                    e.clear()
                }
                Constant.NUMBER_2 -> if (newpwd == e.toString()) {
                    if (payType == Constant.NUMBER_0) {
                        reqSetPwd(e.toString())
                    } else if (payType == Constant.NUMBER_3) {
                        resetTradePwd(e.toString())
                    } else {
                        reqUpdatePwd()
                    }
                } else {
                    val dialog = SweetAlertDialog(ActivityManage.peek(), SweetAlertDialog.NORMAL_TYPE)
                        .setContentText(context.resources.getString(R.string.seetings_pwd_tips))
                        .setCancelText(context.resources.getString(R.string.seetings_pwd_reset))
                        .setConfirmText(context.resources.getString(R.string.seetings_pwd_again))
                        .setConfirmClickListener { sweetAlertDialog ->
                            binding.pwd.setText("")
                            sweetAlertDialog.dismiss()
                        }
                        .setCancelClickListener { sweetAlertDialog ->
                            binding.pwd.setText("")
                            title.set(context.getString(R.string.mine_settings_set_pwd))
                            tips.set(context.getString(R.string.settints_pay_set_tips))
                            step = Constant.NUMBER_1
                            sweetAlertDialog.dismiss()
                        }
                    dialog.setCancelable(false)
                    dialog.show()
                }
            }
        }
    }

    //设置交易密码
    private fun reqSetPwd(pwd: String) {
        val sub = UpdatePwdSub()
        sub.pwd=pwd
        val call = HttpClient.getService(MineService::class.java).setTradePwd(sub)
        call.enqueue(object : ResponseCallback<HttpResult<Any>>() {
           override fun onSuccess(call: Call<HttpResult<Any>>,
                                  response: Response<HttpResult<Any>>) {
                val payseting = SharedInfo.getInstance().getValue(Constant.IS_PAY_SETTING, 0) as Int
                if (payseting == 1) {
                    val intent = Intent()
                    intent.putExtra(BundleKeys.DATA, pwd)
                    ActivityManage.peek().setResult(RequestResultCode.RES_PAY_SETTING_PWD, intent)
                    ActivityManage.pop()
                } else {

                    DialogUtils.showDialog(
                        ActivityManage.peek(),
                        SweetAlertDialog.SUCCESS_TYPE,
                        context.resources.getString(R.string.settints_pay_success),
                        { sweetAlertDialog ->
                            sweetAlertDialog.dismiss()
                            ActivityManage.pop()
                        },
                        false
                    )

                }
            }
        })
    }

    //修改交易密码
    private fun reqUpdatePwd() {
        val updatePwdSub=UpdatePwdSub()
        updatePwdSub.oldPwd=oldpwd
        updatePwdSub.newPwd=newpwd
        val call = HttpClient.getService(MineService::class.java).updatePayPwd(updatePwdSub)
        call.enqueue(object : ResponseCallback<HttpResult<Any>>() {
           override fun onSuccess(call: Call<HttpResult<Any>>, response: Response<HttpResult<Any>>) {
               DialogUtils.showDialog(
                   ActivityManage.peek(),
                   SweetAlertDialog.SUCCESS_TYPE,
                   context.resources.getString(R.string.settints_pay_success),
                   { sweetAlertDialog ->
                       sweetAlertDialog.dismiss()
                       ActivityManage.pop()
                   },
                   false
               )
            }
        })
    }

    //校验交易密码
    private fun reqCheckPwd(pwd: String) {
        val updatePwdSub=UpdatePwdSub()
        updatePwdSub.tradePwd=pwd
        val call = HttpClient.getService(MineService::class.java).validateTradePwd(updatePwdSub)
        call.enqueue(object : ResponseCallback<HttpResult<PassRec>>() {
            override  fun onSuccess(call: Call<HttpResult<PassRec>>, response: Response<HttpResult<PassRec>>) {
                if (response.body()!!.data.pass==true) {
                    oldpwd = pwd
                    step = Constant.NUMBER_1
                    title.set(context.getString(R.string.settints_pay_update_new_title))
                    tips.set(context.getString(R.string.settints_pay_update_new_tips))
                    binding.pwd.setText("")
                } else {
                    DialogUtils.showDialog(ActivityManage.peek(),
                        SweetAlertDialog.ERROR_TYPE,
                        context.resources.getString(
                            R.string
                                .settints_pay_update_old_pwd_error
                        ),
                        { sweetAlertDialog ->
                            binding.pwd.setText("")
                            sweetAlertDialog.dismiss()
                        },
                        false
                    )
                }
            }
        })
    }

    //重置交易密码
    private fun resetTradePwd(pwd: String) {
        val sub = UpdatePwdSub()
        sub.newPwd=pwd
        val call = HttpClient.getService(MineService::class.java).resetTradePwd(sub)
        call.enqueue(object : ResponseCallback<HttpResult<Any>>() {
            override  fun onSuccess(call: Call<HttpResult<Any>>, response: Response<HttpResult<Any>>) {
                DialogUtils.showDialog(
                    ActivityManage.peek(),
                    SweetAlertDialog.SUCCESS_TYPE,
                    context.resources.getString(R.string.settints_pay_success),
                    { sweetAlertDialog ->
                        sweetAlertDialog.dismiss()
                        ActivityManage.pop()
                    },
                    false
                )
            }

        })
    }

    private fun initView() {
        binding.pwd.setText("")
        step = if (payType == Constant.NUMBER_0 || payType == Constant.NUMBER_3) {
            title.set(context.getString(R.string.mine_settings_set_pwd))
            tips.set(context.getString(R.string.settints_pay_set_tips))
            Constant.NUMBER_1
        } else {
            title.set(context.getString(R.string.mine_settings_update_paypaw))
            tips.set(context.getString(R.string.settints_pay_update_tips))
            Constant.NUMBER_0
        }
    }
}