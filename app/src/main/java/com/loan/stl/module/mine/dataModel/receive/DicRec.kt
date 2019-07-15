package com.loan.stl.module.mine.dataModel.receive

/**
author: russell
time: 2019-07-15:18:59
describe：
 */
class DicRec (
    /** 联系人关系，type=BANK_TYPE  */
     val bankTypeList: List<KeyValueRec>? = null,
    /** 联系人关系，type=CONTACT_RELATION  */
     val contactRelationList: List<KeyValueRec>? = null,
    /** 紧急联系人关系，type=KINSFOLK_RELATION  */
     val kinsfolkRelationList: List<KeyValueRec>? = null,
    /** 教育程度，type=EDUCATIONAL_STATE  */
     val educationalStateList: List<KeyValueRec>? = null,
    /** 居住时长，type=LIVE_TIME  */
     val liveTimeList: List<KeyValueRec>? = null,
    /** 婚姻状况，type=MARITAL_STATE  */
     val maritalList: List<KeyValueRec>? = null,
    /** 月薪范围，type=SALARY_RANGE  */
     val salaryRangeList: List<KeyValueRec>? = null,
    /** 工作时长，type=WORK_TIME  */
     val workTimeList: List<KeyValueRec>? = null
    )