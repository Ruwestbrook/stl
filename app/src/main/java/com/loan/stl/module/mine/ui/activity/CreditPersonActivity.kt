package com.loan.stl.module.mine.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.loan.stl.R
import com.loan.stl.common.BaseActivity
import com.loan.stl.databinding.ActivityCreditPersonBinding

/**
author: russell
time: 2019-07-15:18:08
describe：完善资料页面
 */
class CreditPersonActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityCreditPersonBinding=DataBindingUtil.setContentView<ActivityCreditPersonBinding>(this, R.layout.activity_credit_person)
    }
}