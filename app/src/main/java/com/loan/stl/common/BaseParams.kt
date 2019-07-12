package com.loan.stl.common

import android.os.Environment

import java.io.File

/**
 * Author: TinhoXu
 * E-mail: xth@erongdu.com
 * Date: 2016/2/23 15:22
 *
 *
 * Description:
 */
class BaseParams {
     companion object{
    //public static final  PaymentType paymentType           = PaymentType.PNR;
    /** ios传“1”，安卓传“2”  */
     const val MOBILE_TYPE = "2"
    /** 转让专区uuid  */
     const val TRANSFER_ZONE = "a31bd335e12ac0dced8849a16fd4a894"
    /** 变现uuid  */
     const val REALIZATION = "090d5d939784fe33aceff143ba1c198c"
    /**
 * 加密是需要使用的密钥
 * DES加解密时KEY必须是16进制字符串,不可小于8位
 * E.G.    6C4E60E55552386C
 * 
 * 
 * 3DES加解密时KEY必须是6进制字符串,不可小于24位
 * E.G.    6C4E60E55552386C759569836DC0F83869836DC0F838C0F7
 */
     const val SECRET_KEY = "6C4E60E55552386C759569836DC0F83869836DC0F838C0F7"
    /** 根路径  */
      val ROOT_PATH = getSDPath() + "/xjmd"
    /** 照片文件文件保存路径  */
      val PHOTO_PATH = "$ROOT_PATH/photo"
    /** 照片-活体识别  */
     var PHOTO_ALIVE = ""
    /** 照片-活体识别1  */
     var PHOTO_ALIVE1 = ""
    /** 照片-活体识别2  */
     var PHOTO_ALIVE2 = ""
    /** 照片-活体识别3  */
     var PHOTO_ALIVE3 = ""
    /** 照片-活体识别byte文件  */
     var PHOTO_ALIVE_BYTE = ""
    /** 照片-正面  */
     var PHOTO_FRONT = ""
    /** 照片-正面byte文件  */
     var PHOTO_FRONT_BYTE = ""
    /** 照片-反面  */
     var PHOTO_BACK = ""
    /** 照片-反面byte文件  */
     var PHOTO_BACK_BYTE = ""
    /** 照片-头像  */
     var PHOTO_AVATAR = ""
    /** SP文件名  */
     const val SP_NAME = "basic_params"
    /** 数据库名称  */
     const val DATABASE_NAME = "stanley_db"
    /** 发送验证码的短信平台号  */
     const val SMS_SENDER = ""
    /** 活体识别  */
     const val ALIVE_APPID = "57c8176b8f1d4819b0e1c1c3f09fb8f9"
     const val ALIVE_SECRET = "d208b9248b034387a017231cca030682" 
         //是否为运营商人证
     var isPhoneState = false
     var phoneStateUrl = ""
    //连连签约接口
     var authSign = "act/mine/bankCard/authSign.htm"

    //有盾共钥
     var YOUDUN_KEY = "90631b5f-5307-4466-bfdc-04bc8eb7c81f"

    //魔蝎公钥
     var MOXIE_KEY = "63d79de797184ad78a4b936497e56117"


        /**
     * 获取SD卡的根目录
     */
        private fun getSDPath():String {
    var sdDir:File? = null
     // 判断sd卡是否存在
             val sdCardExist = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    if (sdCardExist)
    {
     // 获取跟目录
                sdDir = Environment.getExternalStorageDirectory()
    }
            return if (sdDir == null) {
                ""
            } else {
                sdDir!!.toString()
            }
}
}
}
