package com.loan.stl.module.user.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.loan.stl.R
import com.loan.stl.common.BaseActivity
import com.loan.stl.databinding.ActivityUpdateLoginBinding
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
        DataBindingUtil.setContentView<ActivityUpdateLoginBinding>(this,R.layout.activity_update_login)

    }


    fun finishPage(view: View){
        finish()
    }

    fun forget(view: View){
        ARouter.getInstance().build(RouterUrl.FORGET_LOGIN_PASSWORD).navigation()
    }
}
