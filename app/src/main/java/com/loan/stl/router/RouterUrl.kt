package com.loan.stl.router

/**
 * author: russell
 * time: 2019-07-12:10:45
 * describe：
 */
class RouterUrl{
    companion object{



        /*打开网页*/
       const val WEBVIWE_FACE="/activity/webview"


        /* 完善资料*/
        const val LOAN_RECORD="/activity/recode"

        /* 完善资料*/
        const val IMPROVE_DATA="/activity/improve/data"

        /* 银行卡*/
        const val BANK_CARD="/activity/bank/card"

            /*去绑定银行卡*/
            const val BIND_CARD="activity/bind/card"


        /* 客服中心*/
        const val HELP_USER="/activity/help"

        /* 还款计划*/
        const val REPALY_PLAN="/activity/replay/plan"

        /* 设置界面 */
        const val USER_SETTING_PAGE="/activity/setting"

            /* 修改交易密码 */
            const val UPDATE_PASSWORD="/activity/forget/password"
                /* 忘记交易密码 */
                const val FORGET_PASSWORD="/activity/forget/password"
            /*意见反馈*/
            const val FEEDBACK_PAGE="/activity/feedback"

            /* 修改登录密码*/
            const val UPDATE_LOGIN_PASSWORD="/activity/login/password"
                const val FORGET_LOGIN_PASSWORD="/activity/login/forget"

        /***********************************************/
        /************* 货款流程 *****************/
        /***********************************************/



        /* 紧急联系人 */
        const val ACTIVITY_LINKER="/activity/linker"
        /* 验证身份 */
        const val ACTIVITY_IDENTITY="/activity/identity"

        /* 定位 */
        const val ACTIVITY_MAP="/activity/map"




    }

}
