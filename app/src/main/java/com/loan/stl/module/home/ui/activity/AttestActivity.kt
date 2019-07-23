package com.loan.stl.module.home.ui.activity

import android.os.Bundle
import android.view.View
import com.loan.stl.R
import com.loan.stl.common.BaseActivity

/**
author: russell
time: 2019-07-17:16:11
describe：
 */
class AttestActivity :BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attest)
        setPageTitle("认证完成")
    }

    @Suppress("UNUSED_PARAMETER")
    fun finishPage(view: View){
        finish()
    }

}