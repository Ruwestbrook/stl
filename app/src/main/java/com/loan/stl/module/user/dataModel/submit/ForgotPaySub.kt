package com.loan.stl.module.user.dataModel.submit

/**
 * Author: Hubert
 * E-mail: hbh@erongdu.com
 * Date: 2017/2/27 下午6:02
 *
 *
 * Description:
 */
data class ForgotPaySub (
    //身份证
     var idNo: String? = null,
    //姓名
     var realName: String? = null,
    //短信验证码
     var vcode: String? = null
)
