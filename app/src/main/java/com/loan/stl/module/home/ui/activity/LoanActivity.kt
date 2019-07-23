package com.loan.stl.module.home.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.loan.stl.R
import com.loan.stl.common.BaseActivity
import com.loan.stl.databinding.ActivityLoanBinding
import com.loan.stl.module.home.viewControl.LoanControl

/**
author: russell
time: 2019-07-17:09:29
describe：借款页面
 */
class LoanActivity:BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
            .setContentView<ActivityLoanBinding>(this,R.layout.activity_loan)
        binding.ctrl= LoanControl(this,binding)
    }
}