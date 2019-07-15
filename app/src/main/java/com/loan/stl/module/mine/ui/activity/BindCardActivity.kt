package com.loan.stl.module.mine.ui.activity

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.loan.stl.common.BaseActivity
import com.loan.stl.router.RouterUrl

/**
author: russell
time: 2019-07-15:10:08
describe：绑定银行卡
 */
@Route(path = RouterUrl.BIND_CARD)
class BindCardActivity:BaseActivity() {
    @JvmField
    @Autowired
    var type:String = "0"

}