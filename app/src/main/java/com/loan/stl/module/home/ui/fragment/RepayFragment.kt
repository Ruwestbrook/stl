package com.loan.stl.module.home.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.loan.stl.R
import com.loan.stl.common.BaseFragment
import com.loan.stl.databinding.FragmentRepayBinding

/**
author: russell
time: 2019-07-11:12:01
describe：还款界面
 */
class RepayFragment :BaseFragment() {
    private lateinit var fragmentRepayBinding: FragmentRepayBinding
    companion object{
        fun getInstance():RepayFragment{
            return RepayFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentRepayBinding=DataBindingUtil.inflate(layoutInflater,R.layout.fragment_repay,container,false)
        return fragmentRepayBinding.root
    }
}