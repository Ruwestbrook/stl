package com.loan.stl.module.mine.dataModel.submit

import com.google.gson.annotations.SerializedName

/**
author: russell
time: 2019-07-15:16:37
describe：
 */
data class CreditBankSub (
    /** 开户行  */
    private var name: String? = null,
    /** 开户行  */
    private var bank: String? = null,
    /** 绑定时间  */
    private var bindTime: String? = null,
    /** 卡号  */
    private var cardNo: String? = null,
    /**   */
    private var id: String? = null,
    /** 预留手机号  */
    private var phone: String? = null,
    /** 预留手机号  */
    @SerializedName("captcha")
    private var code: String? = null,
    /** 用户id  */
    private var userId: String? = null,

    private var bankCode: String? = null)