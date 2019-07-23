package com.loan.stl.module.user.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.loan.stl.R
import com.loan.stl.common.BaseActivity
import com.loan.stl.common.RequestResultCode
import com.loan.stl.databinding.ActivityUpdateLoginBinding
import com.loan.stl.module.user.viewControl.UpdateLoginControl
import com.loan.stl.router.RouterUrl

/**
 * author: russell
 * time: 2019-07-11:11:53
 * describe：修改登录密码
 */
@Route(path = RouterUrl.UPDATE_LOGIN_PASSWORD)
class UpdateLoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val binding= DataBindingUtil.setContentView<ActivityUpdateLoginBinding>(this,R.layout.activity_update_login)
        binding.ctrl= UpdateLoginControl()
    }


    @Suppress("UNUSED_PARAMETER")
    fun finishPage(view: View){
        finish()
    }

    /*
    忘记密码
     */
    @Suppress("UNUSED_PARAMETER")
    fun forget(view: View){
        ARouter.getInstance().build(RouterUrl.FORGET_LOGIN_PASSWORD)
            .navigation(this,RequestResultCode.REQ_FORGOT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==RequestResultCode.REQ_FORGOT && resultCode== Activity.RESULT_OK){
            finish()
        }

    }
}
