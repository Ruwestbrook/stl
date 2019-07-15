package com.loan.stl.module.user.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.loan.stl.R
import com.loan.stl.common.BaseActivity
import com.loan.stl.router.RouterUrl

/**
author: russell
time: 2019-07-15:10:06
describe：修改交易密码
 */
@Route(path = RouterUrl.UPDATE_PASSWORD)
class UpdatePayActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_pay)
    }

    fun finishPage(view: View){
        finish()
    }

    fun forget(view: View){
        ARouter.getInstance().build(RouterUrl.FORGET_LOGIN_PASSWORD).navigation()
    }
}
