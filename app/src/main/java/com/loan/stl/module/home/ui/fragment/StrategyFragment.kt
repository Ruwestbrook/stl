package com.loan.stl.module.home.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.loan.stl.R
import com.loan.stl.common.BaseFragment
import com.loan.stl.databinding.FragmentStrategyBinding
import kotlinx.android.synthetic.main.activity_credit_person.*

/**
author: russell
time: 2019-07-11:13:39
describe：攻略界面
 */
class StrategyFragment:BaseFragment() {
    private lateinit var fragmentStrategyBinding:FragmentStrategyBinding

    companion object {
        fun getInstance():StrategyFragment{
            return StrategyFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentStrategyBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_strategy,container,false)

        return fragmentStrategyBinding.root
    }
}