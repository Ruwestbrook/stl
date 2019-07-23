package com.loan.stl.module.mine.viewControl

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.authreal.api.AuthBuilder
import com.authreal.api.OnResultCallListener
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.loan.stl.LoanApplication
import com.loan.stl.R
import com.loan.stl.common.BaseParams
import com.loan.stl.common.Constant
import com.loan.stl.common.DicKey
import com.loan.stl.common.RequestResultCode
import com.loan.stl.module.mine.dataModel.receive.*
import com.loan.stl.module.mine.ui.activity.CreditPersonActivity
import com.loan.stl.module.mine.viewModel.CreditPersonVM
import com.loan.stl.network.HttpClient
import com.loan.stl.network.NetworkUtil
import com.loan.stl.network.ResponseCallback
import com.loan.stl.network.api.MineService
import com.loan.stl.network.entity.HttpResult
import com.loan.stl.router.RouterUrl
import com.loan.stl.utils.ToastUtils
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import com.loan.stl.utils.FileUtil
import com.loan.stl.utils.Util
import retrofit2.Call
import retrofit2.Response



/**
author: russell
time: 2019-07-15:18:20
describe：
 */
class CreditControl(var activity:CreditPersonActivity) {
    var permissions= arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
        Manifest.permission.BLUETOOTH_ADMIN,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.BLUETOOTH)
    var creditPersonVM=CreditPersonVM()
    init {
           reqData()



    }

    private val education = ArrayList<String?>()


    private var ocrFlag=false
    private var faceTime=0

    fun openOrc(view: View){

        if(faceTime>0){
            if(ocrFlag){
                return
            }
            ocrFlag=true
            val id = "and-" + UUID.randomUUID().toString()
            val mAuthBuilder = AuthBuilder(id, BaseParams.YOUDUN_KEY,
                "", OnResultCallListener { s, jsonObject ->
                    Log.d("youdun", s)
                    ocrFlag=false
                    checkData(jsonObject)
                })
            mAuthBuilder.faceAuth(view.context)
        }else{
            ToastUtils.toast(R.string.credit_scan_error_tips)
        }


    }


    /** 个人信息  */
    private fun reqInfo() {
        val callInit = HttpClient.getService(MineService::class.java).getUserInfo()
        NetworkUtil.showCutscenes(callInit)
        callInit.enqueue(object : ResponseCallback<HttpResult<CreditPersonRec>>() {
           override fun onSuccess(call: Call<HttpResult<CreditPersonRec>>, response: Response<HttpResult<CreditPersonRec>>) {
                if (response.body() != null && response.body()!!.data != null) {
                  /*
                    加载个人信息
                   */
                    val rec=response.body()!!.data

                }
            }
        })
    }

    private fun checkData(jsonObject: JSONObject) {
        try {

            val code = jsonObject.getInt("ret_code")
            when (code) {
                //成功
                0 -> {
                    val auth = jsonObject.getString("result_auth")
                    if (auth != "T") {
                        ToastUtils.toast("不能确定是否为同一人")
                        return
                    }
                    val riskTag = jsonObject.getJSONObject("risk_tag")
                    val tag = riskTag.getString("living_attack")
                    if (tag == "1") {
                        ToastUtils.toast("认证存在风险,请重试")
                        return
                    }
                    ocrFlag=true
                    val url_frontcard = jsonObject.opt("sdk_url_frontcard") as Bitmap
                    val url_backcard = jsonObject.opt("sdk_url_backcard") as Bitmap
                    val url_photoliving = jsonObject.opt("sdk_url_photoliving") as Bitmap
                    val url_photoget = jsonObject.opt("sdk_url_photoget") as Bitmap

                    val name = jsonObject.getString("id_name")
                    val id = jsonObject.getString("id_no")
//                    viewModel.setName(name)
//                    viewModel.setCardNo(id)
//                    viewModel.setFaceResult(jsonObject.toString())
//                    uploadBitmaps = ArrayList<Bitmap>()
//                    uploadBitmaps.add(url_backcard)
//                    uploadBitmaps.add(url_frontcard)
//                    uploadBitmaps.add(url_photoliving)
//                    uploadBitmaps.add(url_photoget)
                    BaseParams.PHOTO_ALIVE = (System.currentTimeMillis() / 1000).toString() + "alive.jpg"
                    FileUtil.saveFile(
                        LoanApplication.context,
                        BaseParams.PHOTO_PATH,
                        BaseParams.PHOTO_ALIVE,
                        url_photoliving
                    )
                 //   viewModel.setFaceImg(BaseParams.PHOTO_PATH + "/" + BaseParams.PHOTO_ALIVE)
                }
                //取消操作
                900001 -> ToastUtils.toast("您取消了认证")
                //网络异常
                900002 -> ToastUtils.toast("网络异常,请稍后再试")
                //姓名规范
                900004 -> {
                }
                //参数校验错误
                900006 -> ToastUtils.toast("参数校验错误,请稍后再试")
                //	接口异常，请联系客服
                900007 -> ToastUtils.toast("请联系客服或稍后再试")
                //身份证姓名和身份证号查询无结果
                500006 -> ToastUtils.toast("身份证姓名和身份证号查询无结果")
                //身份证姓名和身份证号码不一致
                500007 -> ToastUtils.toast("身份证姓名和身份证号码不一致")
                //不能确定是否为同一人
                500020 -> ToastUtils.toast("不能确定是否为同一人")
                //系统无法比对
                500021 -> ToastUtils.toast("系统无法比对")
                //对比度过低
                500023 -> ToastUtils.toast("对比度过低")
                //库中无照片
                500024 -> ToastUtils.toast("库中无照片")
                //系统判断为不同人
                500025 -> ToastUtils.toast("系统判断为不同人")
                //身份证国徽面面识别出错
                410003 -> ToastUtils.toast("身份证国徽面面识别出错")
                //身份证人像面识别出错
                410004 -> ToastUtils.toast("身份证人像面识别出错")
                //身份证已过期，请更换证件
                410006 -> ToastUtils.toast("身份证已过期，请更换证件")
                //	接口异常
                500003 -> ToastUtils.toast("请联系客服或稍后再试")
                //人脸特征提取失败
                500015 -> ToastUtils.toast("人脸特征提取失败")
                //700001	文件下载处理出错，请重试	文件下载处理出错，请重试
                //700003	对应文件不存在	对应文件不存在
                //700005	文件下载地址已过期（下载地址7天内有效）	文件下载地址已失效
                //700006
                700001, 700003, 700005, 700006 -> ToastUtils.toast("请联系客服或稍后再试")
            }
        } catch (e: JSONException) {
            //Toast.makeText(CreditGuideActivity.this,"请联系客服或稍后再试",Toast.LENGTH_LONG).show();
            e.printStackTrace()
        }

    }


    /** 获取可扫描次数  */
    private fun reqTimeData() {
        val callInit = HttpClient.getService(MineService::class.java).idCardCreditTime()
        NetworkUtil.showCutscenes(callInit)
        callInit.enqueue(object : ResponseCallback<HttpResult<IdCardTimeRec>>() {
           override fun onSuccess(call: Call<HttpResult<IdCardTimeRec>>, response: Response<HttpResult<IdCardTimeRec>>) {
               val timeRec = response.body()!!.data
               faceTime = if(timeRec.faceTime==null) 0 else timeRec.faceTime.toInt()

            }
        })
    }


    fun reqData() {
        val call = HttpClient.getService(MineService::class.java).getUserAuth()
        call.enqueue(object : ResponseCallback<HttpResult<CreditStatusRec>>() {
           override fun onSuccess(call: Call<HttpResult<CreditStatusRec>>, response: Response<HttpResult<CreditStatusRec>>) {
               val statusRec = response.body()!!.data
               if(statusRec?.idState==Constant.STATUS_10){
                  reqTimeData()
               }else{
                  reqInfo()
                   ocrFlag=true
               }
            }
        })
    }


    /*
        初始化数据字典
     */

    /**
     * 数据字典请求
     *
     * @param view
     */
    private fun reqDic(view: View) {
        val callInit = HttpClient.getService(MineService::class.java).getDicts(DicKey.EDUCATION + "," + DicKey.MARITAL)
        callInit.enqueue(object : ResponseCallback<HttpResult<DicRec>>() {
           override fun onSuccess(call: Call<HttpResult<DicRec>>, response: Response<HttpResult<DicRec>>) {
                if (response.body() != null && response.body()!!.data != null) {
                    val dic= response.body()!!.data

                        if (dic != null) {//数据字典获取内容
                            val temp: List<KeyValueRec>
                            if (dic.educationalStateList != null) {
                                temp = dic.educationalStateList
                                for (i in temp.indices) {
                                    education.add(temp[i].value)
                                }
                            }

                        }
                }
            }
        })
    }


    fun showEducation(view: View){
        //条件选择器
        val pvOptions = OptionsPickerBuilder(view.context,
            OnOptionsSelectListener { options1, option2, options3, v ->
                ToastUtils.toast("选择了"+education[options1])

            }).build<String?>()
        pvOptions.setPicker(education)
        pvOptions.show()
    }

    /*
    打开高德地图定位
     */
    fun openMap(view: View){

        if(!checkPermission()){
            requestPermissions(true)
            return
        }
        openMap()
    }

    fun openMap(){
        ARouter.getInstance().build(RouterUrl.ACTIVITY_MAP).
            navigation(activity,RequestResultCode.REQ_MAP_CODE)

    }


    fun checkPermission():Boolean{
        var flag=true
        permissions.forEach {
            if( ContextCompat.checkSelfPermission(activity, it) !=
                PackageManager.PERMISSION_GRANTED)
                flag=false
        }

        return flag

    }


    fun requestPermissions(flag:Boolean){
        val code=if(flag) RequestResultCode.REQ_MAP_CODE else RequestResultCode.REQ_LOCATION_CODE
        ActivityCompat.requestPermissions(
            activity,
            permissions, code)
    }

}