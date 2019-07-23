package com.loan.stl.module.user.dataModel.submit

import com.google.gson.annotations.SerializedName
import com.loan.stl.network.annotation.SerializedEncryption
import com.loan.stl.network.annotation.SerializedIgnore

/**
 * Author: TinhoXu
 * E-mail: xth@erongdu.com
 * Date: 2016/11/17 18:43
 *
 *
 * Description: 忘记密码  重置密码需要提交的数据
 */
class ForgotSub {
    /** 手机号  */
    @SerializedName("phone")
    var mobile: String? = null
    /** 密码  */
    @SerializedEncryption(type = "MD5")
    @SerializedName("newPwd")
    var password: String? = null
    /** 确认密码  */
    @SerializedIgnore
    var confirmPassword: String? = null
    /** 验证码  */
    @SerializedName("vcode")
    var verifyCode: String? = null
    /** 签名信息 appKey+newPwd+phone+vcode  */
    var signMsg: String? = null

    constructor(mobile: String, password: String, confirmPassword: String, verifyCode: String) {
        this.mobile = mobile
        this.password = password
        this.confirmPassword = confirmPassword
        this.verifyCode = verifyCode
    }

    constructor() {}
}
