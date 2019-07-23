package com.loan.stl.module.user.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.loan.stl.R
import com.loan.stl.common.BaseActivity
import com.loan.stl.common.BundleKeys
import com.loan.stl.databinding.ActivityForgetPayBinding
import com.loan.stl.module.user.viewControl.ForgetPayControl
import com.loan.stl.router.RouterUrl

/**
author: russell
time: 2019-07-15:10:06
describe：忘记交易密码
 */
@Route(path = RouterUrl.FORGET_PASSWORD)
class ForgetPayActivity : BaseActivity() {

    @Autowired(name = BundleKeys.TYPE)
    @JvmField
    var type:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        val dataBinding=DataBindingUtil.setContentView<ActivityForgetPayBinding>(this,R.layout.activity_forget_pay)
        dataBinding.ctrl= ForgetPayControl(dataBinding.timeButton,type)
        setPageTitle(R.string.settings_forgot_pay_title,false)
    }
}
