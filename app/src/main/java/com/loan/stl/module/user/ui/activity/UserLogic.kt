package com.loan.stl.module.user.ui.activity

import android.app.Activity
import android.text.TextUtils
import com.cazaea.sweetalert.SweetAlertDialog
import com.loan.stl.LoanApplication.Companion.context
import com.loan.stl.R
import com.loan.stl.common.Constant
import com.loan.stl.module.user.dataModel.receive.OauthTokenMo
import com.loan.stl.utils.SPreferences.SharedInfo

/**
author: russell
time: 2019-07-16:19:02
describe：
 */
class UserLogic {


    companion object{

    /**
     * 用户登录
     */
    @JvmStatic
    fun login(activity: Activity, oauthTokenMo: OauthTokenMo) {
        /** 登录逻辑处理  */
        SharedInfo.getInstance().saveValue(Constant.IS_LAND, true)
        SharedInfo.getInstance().saveEntity(oauthTokenMo)

            //打开首页

        activity.setResult(Activity.RESULT_OK)
        activity.finish()
    }

    /**
     * 登出必须执行的操作
     */
    @JvmStatic
    fun signOut() {
        val tokenMo = SharedInfo.getInstance().getEntity(OauthTokenMo::class.java)
        if (null != tokenMo) {
            // 删除保存的手势密码信息
            //GestureLogic.getInstance().removeEntity(tokenMo.getUserId());
        }
        // 标记未启用手势密码
        SharedInfo.getInstance().remove(Constant.IS_GESTURE_OPENED)
        // 标记未登录
        SharedInfo.getInstance().remove(Constant.IS_LAND)
        // 删除保存的OauthToken信息

    }

    /**
     * 用户被动登出
     */
    @JvmStatic
    fun signOutForcibly(activity: Activity) {
        signOut()
        //打开首页
    }

    /**
     * 用户主动登出(到主界面)
     */
    @JvmStatic
    fun signOutToMain(activity: Activity) {
        val sweetAlertDialog = SweetAlertDialog(activity)
            .setContentText(context.getString(R.string.user_login_out))
            .setConfirmText("确定")
            .setCancelClickListener {
                it.dismiss()
                signOutForcibly(activity)
            }
        sweetAlertDialog.setCancelable(false)
        sweetAlertDialog.show()
    }

    /**
     * 用户主动登出(到登录界面)
     */
    @JvmStatic
    fun signOutToLogin(activity: Activity) {


        val sweetAlertDialog = SweetAlertDialog(activity)
            .setContentText(context.getString(R.string.user_login_out))
            .setConfirmText("确定")
            .setCancelClickListener {
                signOut()
                //到登录页面
                activity.finish()
            }
        sweetAlertDialog.setCancelable(false)
        sweetAlertDialog.show()

    }

    /**
     * 是否已经登录
     */
    @JvmStatic
    fun isLand(): Boolean {
        val isLand = SharedInfo.getInstance().getValue(Constant.IS_LAND, false) as Boolean
        val tokenRec = SharedInfo.getInstance().getEntity(OauthTokenMo::class.java)
        // 是否已经登录
        return isLand && null != tokenRec && !TextUtils.isEmpty(tokenRec.userId)
    }
    }
}