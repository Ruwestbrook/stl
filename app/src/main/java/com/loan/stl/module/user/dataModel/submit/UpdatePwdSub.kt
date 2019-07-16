package com.loan.stl.module.user.dataModel.submit

import com.loan.stl.network.annotation.SerializedEncryption

/**
 * Author: JinFu
 * E-mail: jf@erongdu.com
 * Date: 2017/2/22 11:28
 *
 *
 * Description:
 */
data class UpdatePwdSub (
    @SerializedEncryption(type = "MD5")
    var newPwd: String? = null,
    @SerializedEncryption(type = "MD5")
    var tradePwd: String? = null,
    @SerializedEncryption(type = "MD5")
    var oldPwd: String? = null,
    @SerializedEncryption(type = "MD5")
    var pwd: String? = null){
    constructor(newPwd: String, oldPwd: String):this(newPwd,null,oldPwd,null)
}
