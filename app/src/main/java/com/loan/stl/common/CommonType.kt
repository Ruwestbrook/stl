package com.loan.stl.common

/**
author: russell
time: 2019-07-14:19:30
describe：
 */
class CommonType {
    companion object{

        const val BANKREMARK = "remark_bank_card"
        const val PROTOCOL_REGISTER = "protocol_register"       //注册协议
        const val REPAY_TYPE = "h5_repay_type"       //还款方式
        const val INVITE_RULE = "h5_invite_rule"       //邀请规则
        const val ABOUNT_US = "h5_about_us"       //关于我们
        const val HELP_REPAY = "h5_repay_help"       //还款帮助
        const val HELP = "h5_help"       //帮助
        const val HOME_REMARK = "remark_findIndex"
        const val HOME_REMARK2 = "remark_findIndex_2"
        //-------------验证码获取类型code-------------
        const val REGISTER_CODE = "register"//注册获取
        const val FORGOT_CODE = "findReg"//找回登陆密码获取
        const val BIND_CARD_CODE = "bindCard"//绑定银行卡
        const val PAY_CODE = "findPay"//找回交易密码

        fun getUrl(url: String?): String {
            return if (AppConfig.IS_DEBUG) {
                AppConfig.URI_AUTHORITY_BETA
            } else {
                AppConfig.URI_AUTHORITY_RELEASE
            }+url
        }
    }
}