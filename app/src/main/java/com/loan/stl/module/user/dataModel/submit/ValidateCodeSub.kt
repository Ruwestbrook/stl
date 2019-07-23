package com.loan.stl.module.user.dataModel.submit

/**
 * Author: Chenming
 * E-mail: cm1@erongdu.com
 * Date: 2017/2/21 下午7:43
 *
 *
 * Description: 验证短信验证码提交累
 */
 data class ValidateCodeSub (
    var phone: String? = null,
    /** 签名md5(appkey+phone＋type+vcode)  */
    var signMsg: String? = null,
    var vcode: String? = null,
    var type: String? = null
)
