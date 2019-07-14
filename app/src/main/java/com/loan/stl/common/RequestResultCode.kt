package com.loan.stl.common

/**
author: russell
time: 2019-07-14:10:40
describe：
 */
class RequestResultCode {
 companion object{
    

    /** 可转让列表跳转转让  */
    const val REQ_CAN_TRANSFER_TO_TRANSFER = 1001
    const val RES_CAN_TRANSFER_TO_TRANSFER = 1001
    /** 重新绑定银行卡  */
    const val REQ_AGAIN_BIND = 1002
    const val RES_AGAIN_BIND = 1002
    const val RES_AUTH_BANK_OK = 10021
    const val RES_AUTH_BANK_OK_REPLAY = 10022
    const val RES_AUTH_BANK_NEXT = 10022
    /** 高德地图  */
    const val REQ_GD_MAP = 1003
    const val RES_GD_MAP = 1003
    /** 手机运营商认证  */
    const val REQ_PHONE = 1004
    const val RES_PHONE = 1004
    /** 找回交易密码  */
    const val REQ_FORGOT_PAY = 1004
    const val RES_FORGOT_PAY = 1004
    /** 找回密码  */
    const val REQ_FORGOT = 1005
    const val RES_FORGOT = 1005
    /** 芝麻信用认证  */
    const val REQ_ZMXY = 1006
    const val RES_ZMXY = 1006
    /** 工作照片  */
    const val REQ_WORK_IMG = 1007
    const val RES_WORK_IMG = 1007
    /** 公积金认证  */
    const val REQ_ACCUMULATION_FUND = 1008
    const val RES_ACCUMULATION_FUND = 1008
    /** 提交借款设置交易密码  */
    const val REQ_PAY_SETTING_PWD = 1009
    const val RES_PAY_SETTING_PWD = 1009

    /**续借或主动还款 */
    const val REQ_RENEW_REQUEST = 1010
    const val RES_RENEW_SUCCESS = 1010
 }
}