package com.loan.stl.module.mine.dataModel.receive

/**
author: russell
time: 2019-07-15:18:53
describe：
 */
class CreditStatusRec (
    /** 银行卡状态(10未认证/未完善，20认证中/完善中，30已认证/已完善)  */
     var bankCardState: String? = null,
    /** 紧急联系人状态  */
     var contactState: String? = null,
    /** 详细资料状态  */
     var detailState: String? = null,
    /** 人脸识别状态  */
     var faceState: String? = null,
    /** 个人信息认证状态  */
     var idState: String? = null,
    /** 手机运营商认证状态  */
     var phoneState: String? = null,
    /** 芝麻授信状态  */
     var zhimaState: String? = null,
    /** 工作状态  */
     var workInfoState: String? = null,
    /** 公积金状态  */
     val accFundState: String? = null,
    /** 更多状态 *//*
     String otherInfoState;*/
    /** 更多状态  */
     var otherInfoState: String? = null)