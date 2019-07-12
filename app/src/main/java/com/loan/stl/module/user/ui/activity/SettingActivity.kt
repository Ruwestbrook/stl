package com.loan.stl.module.user.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.loan.stl.R
import com.loan.stl.router.RouterUrl

/**
 * author: russell
 * time: 2019-07-11:11:53
 * describe：设置页面
 */
@Route(path = RouterUrl.USER_SETTING_PAGE)
class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        requestData()
    }



    /*
     修改或者设置交易密码
     */
    fun setPayPwd(view: View){

    }
    /*
     关于我们
     */
    fun aboutUs(view: View){

    }

    /*
     意见反馈
     */
    fun idea(view: View){
        ARouter.getInstance().build(RouterUrl.FEEDBACK_PAGE).navigation()
    }

    /*
      用户退出
     */
    fun exit(view: View){

    }

    fun requestData(){

    }

}
