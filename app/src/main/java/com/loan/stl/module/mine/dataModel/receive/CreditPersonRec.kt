package com.loan.stl.module.mine.dataModel.receive

/**
author: russell
time: 2019-07-15:18:39
describe：
 */
data class CreditPersonRec (
    /** 纬度  */
     val latitude: String? = null,
    /** 经度  */
     val longitude: String? = null,
    /** 身份证背面  */
     val backImg: String? = null,
    /** 详细地址  */
     val liveDetailAddr: String? = null,
    /** 学历  */
     val education: String? = null,
    /** 身份证正面  */
     val frontImg: String? = null,
    /** 身份证号  */
     val idNo: String? = null,
    /** 现居住地址  */
     val liveAddr: String? = null,
    /** 自拍  */
     val livingImg: String? = null,
    /** 身份证上照片  */
     val ocrImg: String? = null,
    /** 姓名  */
     val realName: String? = null,
    /** 婚姻状态  */
     val marital: String? = null
)