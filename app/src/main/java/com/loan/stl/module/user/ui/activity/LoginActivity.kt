package com.loan.stl.module.user.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import com.loan.stl.R
import com.loan.stl.common.BaseActivity
import com.loan.stl.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.view.*

/**
author: russell
time: 2019-07-12:10:06
describe：登录页面（取消了密码，所以登录和注册都是同一处了）
 */
class LoginActivity :BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        val activityLoginBinding=DataBindingUtil.
            setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
    }
}