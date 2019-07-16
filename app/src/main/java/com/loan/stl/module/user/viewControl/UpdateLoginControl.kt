package com.loan.stl.module.user.viewControl

import android.view.View
import com.loan.stl.R
import com.loan.stl.common.ActivityManage
import com.loan.stl.module.user.dataModel.receive.InfoRec
import com.loan.stl.module.user.dataModel.submit.UpdatePwdSub
import com.loan.stl.module.user.viewModel.UpdateLoginVM
import com.loan.stl.network.HttpClient
import com.loan.stl.network.NetworkUtil
import com.loan.stl.network.ResponseCallback
import com.loan.stl.network.api.MineService
import com.loan.stl.network.entity.HttpResult
import com.loan.stl.utils.DisplayFormat
import com.loan.stl.utils.InputCheck
import com.loan.stl.utils.ToastUtils
import retrofit2.Call
import retrofit2.Response

/**
author: russell
time: 2019-07-15:11:13
describe：
 */
class UpdateLoginControl {
    var loginVM=UpdateLoginVM()

    init {
        val call=HttpClient.getService(MineService::class.java).getInfo()
        call.enqueue(object :ResponseCallback<HttpResult<InfoRec>>(){
            override fun onSuccess(
                call: Call<HttpResult<InfoRec>>?,
                  response: Response<HttpResult<InfoRec>>?)
            {

                val infoRec=response?.body()!!.data
                if(infoRec?.phone!=null){
                    loginVM.phone= DisplayFormat.accountHideFormat(infoRec.phone)
                }
            }

        })
    }

    fun submit(view: View){
        if(!loginVM.enable){
           ToastUtils.toast("请输入密码！")
            return
        }

        if (loginVM.newPass!=loginVM.repeatPass) {
            ToastUtils.toast(R.string.seetings_pwd_tips)
            return
        }
        if (!InputCheck.checkPwd(loginVM.oldPass) ||
            !InputCheck.checkPwd(loginVM.newPass) ||
            !InputCheck.checkPwd(loginVM.repeatPass)) {
            ToastUtils.toast(R.string.settings_pwd_desc)
            return
        }
        val call = HttpClient.getService(MineService::class.java).
            updatePwd(UpdatePwdSub(loginVM.oldPass, loginVM.oldPass))
        NetworkUtil.showCutscenes(call)
        call.enqueue(object : ResponseCallback<HttpResult<Any>>() {
          override  fun onSuccess(call: Call<HttpResult<Any>>, response: Response<HttpResult<Any>>) {
                ToastUtils.toast(response.body()!!.msg)
                ActivityManage.pop()
            }
        })

    }
}