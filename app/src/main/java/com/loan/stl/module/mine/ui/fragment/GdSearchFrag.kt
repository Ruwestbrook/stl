package com.loan.stl.module.mine.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.loan.stl.R
import com.loan.stl.common.BaseFragment
import com.loan.stl.databinding.CommonViewPagerRecyclerBinding
import com.loan.stl.module.mine.viewControl.GdSearchCtrl

/**
author: russell
time: 2019-07-16:16:49
describe：
 */
class GdSearchFrag:BaseFragment() {

    private var cityCode = ""
    var viewCtrl: GdSearchCtrl?=null
   companion object{
       var fragment: GdSearchFrag? = null
       fun getInstance(cityCode: String): GdSearchFrag {

           if (fragment == null) {
               fragment = GdSearchFrag()
               fragment!!.cityCode = cityCode
           }
           return fragment as GdSearchFrag
       }
   }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<CommonViewPagerRecyclerBinding>(inflater,R.layout.common_view_pager_recycler,container,false)
        viewCtrl = GdSearchCtrl(binding.root, cityCode)
        binding.viewCtrl = viewCtrl
        return binding.root
    }




  override  fun onDestroyView() {
        super.onDestroyView()
        fragment = null
    }
}