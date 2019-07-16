package com.loan.stl.module.mine.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.loan.stl.R
import com.loan.stl.common.BaseActivity
import com.loan.stl.databinding.ActivityIdentityBinding
import com.loan.stl.module.mine.viewControl.IdentityControl
import com.loan.stl.router.RouterUrl

/**
author: russell
time: 2019-07-15:23:31
describe：验证身份 ：主要是验证工作
 */
@Route(path = RouterUrl.ACTIVITY_IDENTITY)
class IdentityActivity :BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= DataBindingUtil.setContentView<ActivityIdentityBinding>(this, R.layout.activity_identity)
        binding.ctrl= IdentityControl()
        setPageTitle("验证身份")
    }
}