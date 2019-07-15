package com.loan.stl.module.user.dataModel.receive

/**
 * created by russell on 2019/3/28
 * email:igruwestbrook@gmail.com
 * Description:
 */
data class InfoRec (
    /**
     * creditTotal : 1000
     * creditUnused : 800
     * creditUsed : 200
     * invitationCode : a1bd2c
     * phone : 15911111111
     */
    var creditTotal: Double = 0.toDouble(),
    var creditUnused: Double = 0.toDouble(),
    var creditUsed: Double = 0.toDouble(),
    var invitationCode: String? = null,
    var phone: String? = null,
    /** 邀请好友獲得费率  */
    val profitRate: String? = null,
    /** 个人信息提交状态  */
    val idState: String? = null,
    /** =银行卡绑定状态  */
    val bankCardState: String? = null,
    /** 紧急联系人状态（现在为个人信息状态）  */
    var contactState: String? = null

)
