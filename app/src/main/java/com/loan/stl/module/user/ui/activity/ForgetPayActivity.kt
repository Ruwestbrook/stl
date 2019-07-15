package com.loan.stl.module.user.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.loan.stl.R
import com.loan.stl.common.BaseActivity
import com.loan.stl.router.RouterUrl

/**
author: russell
time: 2019-07-15:10:06
describe：忘记交易密码
 */
@Route(path = RouterUrl.FORGET_PASSWORD)
class ForgetPayActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pay)
    }
}
