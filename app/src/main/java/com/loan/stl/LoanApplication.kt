package com.loan.stl

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.loan.stl.common.ActivityManage
import com.loan.stl.common.AppConfig
import com.loan.stl.common.BaseParams
import com.loan.stl.module.user.dataModel.receive.OauthTokenMo
import com.loan.stl.utils.LogUtils
import com.loan.stl.utils.SPreferences.SharedInfo

/**
author: russell
time: 2019-07-12:10:38
describe：
 */
class LoanApplication :Application(), AMapLocationListener {


    override fun onCreate() {
        super.onCreate()

        context=this.applicationContext


        closeAndroidPDialog()
        registerActivity()
        basicInit()
        registerGaoDeLocation()
    }


    override fun onLocationChanged(amapLocation: AMapLocation?) {
        if (amapLocation != null) {
            if (amapLocation.errorCode == 0) {
                district = amapLocation.district
                //                address = amapLocation.getProvince() + "," + amapLocation.getCity() + "," + district + "," + amapLocation.getStreet();
                address = amapLocation.address
                locAddress = amapLocation.city + district + amapLocation.street + amapLocation.streetNum
                locCity = amapLocation.city
                lon = amapLocation.longitude
                lat = amapLocation.latitude
                LogUtils.d("定位成功")

                if (locCity != null && "" != locCity) {
                    if (!openGps)
                        mLocationClient!!.stopLocation()
                    if (onPosChanged != null)
                        onPosChanged!!.changed(amapLocation)
                } else {
                    locCount++
                    if (locCount >= 4) {
                        if (!openGps)
                            mLocationClient!!.stopLocation()
                        if (onPosChanged != null)
                            onPosChanged!!.changed(amapLocation)
                    }
                }
            } else {
                if (onPosChanged != null)
                    onPosChanged!!.changed(amapLocation)

                LogUtils.d("amapLocation.getErrorCode()" + amapLocation.errorCode)
                LogUtils.d("amapLocation.getErrorInfo()" + amapLocation.errorInfo)
            }
        }
    }


    companion object{
        @SuppressLint("StaticFieldLeak")
        @JvmStatic
        lateinit var context:Context

        @JvmStatic
        fun setLocOption(time: Int) {
            val option = AMapLocationClientOption()
            option.locationMode = AMapLocationClientOption.AMapLocationMode.Battery_Saving// 设置定位模式
            option.interval = time.toLong()// 设置发起定位请求的间隔时间为5000ms
            option.isNeedAddress = true// 返回的定位结果包含地址信息
            //		option.setNeedDeviceDirect(true);// 返回的定位结果包含手机机头的方向
            //设置为高精度定位模式
            option.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
            mLocationClient?.setLocationOption(option)
        }

        /*********************** 高德地图定位  */
        @SuppressLint("StaticFieldLeak")
        var mLocationClient: AMapLocationClient? = null
        private var locCity: String? = ""
        private var district = ""
        var address = ""
        var lat = 0.0
        var lon = 0.0
        private var openGps = false
        private var locCount = 0

        /***********
         * 打开GPS
         *
         * @param lisenter
         * @param status
         * 表示是否进行GPS跟踪
         */
        @JvmStatic
        fun openGps(lisenter: OnPosChanged, status: Boolean) {
            locCount = 0
            onPosChanged = lisenter
            openGps = status
            setLocOption(5000)
            mLocationClient!!.startLocation()
        }

        /**
         * 判断GPS是否开启
         */
        @JvmStatic
        fun isOpen(context: Context): Boolean {
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
            // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
            //        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

        }

        /**********
         * 位置回调
         */
        internal var onPosChanged: OnPosChanged? = null

        @JvmStatic
        fun setOnPosChanged(callback: OnPosChanged) {
            locCount = 0
            onPosChanged = callback
        }



        /**
         * 定位成功后回调函数
         */



        private var locAddress = ""

        fun getLocAddress(): String {
            return locAddress
        }

        fun closeGps() {
            mLocationClient!!.stopLocation()
        }

    }


    private fun registerGaoDeLocation() {
        if (mLocationClient == null) {
            mLocationClient = AMapLocationClient(this)
            //设置定位监听
            mLocationClient?.setLocationListener(this)
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            setLocOption(10000)
            mLocationClient?.startLocation()
        }
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
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
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

    interface OnPosChanged {
        fun changed(location: AMapLocation)
    }

}