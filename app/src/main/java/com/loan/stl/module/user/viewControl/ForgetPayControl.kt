package com.loan.stl.module.user.viewControl

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.loan.stl.common.BundleKeys
import com.loan.stl.module.user.viewModel.ForgetLoginVM
import com.loan.stl.module.user.viewModel.ForgetPayVM
import com.loan.stl.router.RouterUrl

/**
author: russell
time: 2019-07-15:14:21
describe：忘记交易密码逻辑
 */
class ForgetPayControl {
    var forgetPayVM=ForgetPayVM()



    fun submit(view: View){
        ARouter.getInstance().build(RouterUrl.UPDATE_PASSWORD)
            .withString(BundleKeys.TYPE,"1").navigation()
    }
}