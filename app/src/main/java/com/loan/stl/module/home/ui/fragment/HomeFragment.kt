package com.loan.stl.module.home.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.loan.stl.R
import com.loan.stl.common.BaseFragment
import com.loan.stl.databinding.FragmentHomeBinding

/**
 * author: russell
 * time: 2019-07-11:11:53
 * describe：
 */
class HomeFragment:BaseFragment(){
    private lateinit var homeBinding: FragmentHomeBinding
    companion object{
        fun getInstance():HomeFragment{
            return HomeFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeBinding=DataBindingUtil.inflate(layoutInflater,R.layout.fragment_home,container,false)
        return homeBinding.root
    }
}
