package com.loan.stl.common

/**
author: russell
time: 2019-07-15:19:00
describe：
 */
class DicKey {
    companion object{
        /** 联系人关系，type=CONTACT_RELATION  */
        const val CONTACT = "CONTACT_RELATION"
        /** 直系联系人关系，type=CONTACT_RELATION  */
        const val KINSFOLK = "KINSFOLK_RELATION"
        /** 教育程度，type=EDUCATIONAL_STATE  */
        const val EDUCATION = "EDUCATIONAL_STATE"
        /** 居住时长，type=LIVE_TIME  */
        const val LIVETIME = "LIVE_TIME"
        /** 婚姻状况，type=MARITAL_STATE  */
        const val MARITAL = "MARITAL_STATE"
        /** 月薪范围，type=SALARY_RANGE  */
        const val SALARYRANGE = "SALARY_RANGE"
        /** 工作时长，type=WORK_TIME  */
        const val WORKTIME = "WORK_TIME"
        /** 银行列表，type=BANK_TYPE  */
        const val BANKTYPE = "BANK_TYPE"
    }
}