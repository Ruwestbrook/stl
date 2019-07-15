package com.loan.stl.module.home.viewControl

import android.view.View
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.loan.stl.module.user.dataModel.receive.OauthTokenMo
import com.loan.stl.router.RouterUrl
import com.loan.stl.utils.DisplayFormat
import com.loan.stl.utils.LogUtils
import com.loan.stl.utils.SPreferences.SharedInfo

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

        ARouter.getInstance().build(RouterUrl.USER_SETTING_PAGE).navigation()
    }

    /*
        借款记录
     */
    fun loanRecord(view: View){
        ARouter.getInstance().build(RouterUrl.LOAN_RECORD).navigation()

    }
    /*
        完善资料
     */
    fun improveData(view: View){
        ARouter.getInstance().build(RouterUrl.IMPROVE_DATA).navigation()

    }
    /*
        银行卡
     */
    fun bankCard(view: View){
        ARouter.getInstance().build(RouterUrl.BANK_CARD).navigation()

    }

    /*
        帮助中心
     */
    fun help(view: View){
        ARouter.getInstance().build(RouterUrl.HELP_USER).navigation()
    }
    /*
        还款计划
     */
    fun replayPlan(view: View){
        ARouter.getInstance().build(RouterUrl.REPALY_PLAN).navigation()

    }

    fun login(textView: TextView) {
        val oauthTokenMo=SharedInfo.getInstance().getEntity(OauthTokenMo::class.java)
        if(oauthTokenMo?.username != null){
            textView.text=DisplayFormat.accountHideFormat(oauthTokenMo.username)
        }

    }


}