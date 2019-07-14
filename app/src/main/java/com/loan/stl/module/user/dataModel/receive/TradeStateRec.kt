package com.loan.stl.module.user.dataModel.receive

/**
author: russell
time: 2019-07-14:13:58
describe：
 */
data class TradeStateRec (

    /**
     * changeable : false  是否可修改
     * forgetable : true  是否可以忘记密码操作
     * setable : false  是否可设置
     */
     var changeable: Boolean = false,
     var forgetable: Boolean = false,
     var setable: Boolean = false

)