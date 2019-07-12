package com.loan.stl.module.user.dataModel

import com.google.gson.annotations.SerializedName
import com.loan.stl.network.annotation.SerializedEncryption

/**
author: russell
time: 2019-07-12:17:43
describe：
 */

data class LoginSub( @SerializedName("loginName") val id: String?,
                     @SerializedEncryption(type = "MD5")
                     @SerializedName("loginPwd")  var pwd: String?,
                     @SerializedName("blackBox") var box: String?){
    constructor(id: String?,pwd: String?):this(id,pwd,null)

}