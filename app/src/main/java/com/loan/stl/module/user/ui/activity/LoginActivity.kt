package com.loan.stl.module.user.ui.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.loan.stl.R
import com.loan.stl.common.BaseActivity
import com.loan.stl.databinding.ActivityLoginBinding
import com.loan.stl.module.user.viewControl.LoginControl

/**
author: russell
time: 2019-07-12:10:06
describe：登录页面（取消了密码，所以登录和注册都是同一处了）
 */
class LoginActivity :BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityLoginBinding=DataBindingUtil.
            setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        activityLoginBinding.ctrl= LoginControl()
    }

    fun finish(view: View){
        finish()
    }
}