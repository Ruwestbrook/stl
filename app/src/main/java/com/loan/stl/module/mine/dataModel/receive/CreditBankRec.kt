package com.loan.stl.module.mine.dataModel.receive

/**
author: russell
time: 2019-07-15:14:38
describe：
 */
class CreditBankRec (


    /** 开户行  */
     val bank: String? = null,
    /** 绑定时间  */
     val bindTime: String? = null,
    /** 卡号  */
     val cardNo: String? = null,
    /**   */
     val id: String? = null,
    /** 预留手机号  */
     val phone: String? = null,
    /** 用户id  */
     val userId: String? = null,
    /**能否重新绑卡 10代表能换，20代表不能换 */
     val changeAble: String? = null

)