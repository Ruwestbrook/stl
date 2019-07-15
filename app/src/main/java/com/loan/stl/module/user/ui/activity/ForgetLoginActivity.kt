package com.loan.stl.module.user.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.loan.stl.R
import com.loan.stl.common.BaseActivity
import com.loan.stl.router.RouterUrl

/**
 * author: russell
 * time: 2019-07-11:11:53
 * describe：忘记登录密码
 */
@Route(path = RouterUrl.FORGET_LOGIN_PASSWORD)
class ForgetLoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_login)
        setPageTitle("忘记登录密码",false)
    }
}
