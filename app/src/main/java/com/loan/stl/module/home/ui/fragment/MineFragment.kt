package com.loan.stl.module.home.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.loan.stl.R
import com.loan.stl.common.BaseFragment
import com.loan.stl.databinding.FragmentMineBinding
import kotlinx.android.synthetic.main.activity_credit_person.*

/**
author: russell
time: 2019-07-11:13:41
describe：
 */
class MineFragment :BaseFragment() {
    private lateinit var mineBinding: FragmentMineBinding
    companion object {
        fun getInstance():MineFragment{
            return MineFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mineBinding=DataBindingUtil.inflate(layoutInflater, R.layout.fragment_mine,container,false)
        return mineBinding.root
    }
}