package com.loan.stl.module.home.viewControl

import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.loan.stl.router.RouterUrl
import com.loan.stl.utils.LogUtils

/**
author: russell
time: 2019-07-12:10:57
describe：
 */
class MineControl {

    /*
        打开设置界面
     */
    fun openSetting(view: View){
        LogUtils.d("打开设置界面")
        ARouter.getInstance().build(RouterUrl.USER_SETTING_PAGE).navigation()
    }
}