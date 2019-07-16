package com.loan.stl.module.user.dataModel.submit

import com.google.gson.annotations.SerializedName
import com.loan.stl.network.annotation.SerializedEncryption

/**
author: russell
time: 2019-07-16:14:11
describe：
 */
data class LoginSub(@SerializedName("loginName") var id: String?,
                    @SerializedEncryption(type = "MD5")
                    @SerializedName("loginPwd")  var pwd: String?,
                    @SerializedName("blackBox") var box: String?){
    constructor(id: String?,pwd: String?):this(id,pwd,null)
    constructor():this(null,null,null)

}