package com.loan.stl

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.loan.stl.common.ActivityManage
import com.loan.stl.common.AppConfig
import com.loan.stl.common.BaseParams
import com.loan.stl.utils.LogUtils
import com.loan.stl.utils.SPreferences.SharedInfo

/**
author: russell
time: 2019-07-12:10:38
describe：
 */
class LoanApplication :Application() {



    companion object{
        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        lateinit var context:Context

    }
    override fun onCreate() {
        super.onCreate()

        context=this.applicationContext


        closeAndroidPDialog()
        registerActivity()
        basicInit()
    }

    private fun basicInit() {

        SharedInfo.init(BaseParams.SP_NAME)

        if(AppConfig.IS_DEBUG){
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)

    }

    private var count = 0
    private fun registerActivity() {
        registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
                ActivityManage.push(activity)
            }

            override fun onActivityStarted(activity: Activity) {
                ActivityManage.setTopActivity(activity)
                if (count++ == 0) {
                    // Logger.i(TAG, ">>>>>>>>>>>>>>>>>>> 切到前台 <<<<<<<<<<<<<<<<<<<");
                    //  gestureCheck(activity);
                }
            }

            override fun onActivityResumed(activity: Activity) {}

            override fun onActivityPaused(activity: Activity) {}

            override fun onActivityStopped(activity: Activity) {
                count--
                if (count == 0) {
                    //                    GestureLogic.getInstance().start();
                    //Logger.i(TAG, ">>>>>>>>>>>>>>>>>>> 切到后台 <<<<<<<<<<<<<<<<<<<");
                }
            }

            override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}

            override fun onActivityDestroyed(activity: Activity) {
                ActivityManage.remove(activity)
            }
        })
    }

    private fun closeAndroidPDialog() {
        try {
            @SuppressLint("PrivateApi") val aClass = Class.forName("android.content.pm.PackageParser\$Package")
            val declaredConstructor = aClass.getDeclaredConstructor(String::class.java)
            declaredConstructor.setAccessible(true)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            @SuppressLint("PrivateApi") val cls = Class.forName("android.app.ActivityThread")
            val declaredMethod = cls.getDeclaredMethod("currentActivityThread")
            declaredMethod.isAccessible = true
            val activityThread = declaredMethod.invoke(null)
            val mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown")
            mHiddenApiWarningShown.isAccessible = true
            mHiddenApiWarningShown.setBoolean(activityThread, true)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}