package com.loan.stl.module.mine.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import com.loan.stl.R
import com.loan.stl.common.BaseFragment
import com.loan.stl.module.mine.viewControl.GdSearchCtrl

/**
author: russell
time: 2019-07-16:16:49
describe：
 */
class GdSearchFrag:BaseFragment() {
    private var fragment: GdSearchFrag? = null
    private var cityCode = ""
    var viewCtrl: GdSearchCtrl?=null
    fun getInstance(cityCode: String): GdSearchFrag {
        if (fragment == null) {
            fragment = GdSearchFrag()
            fragment!!.cityCode = cityCode
        }
        return fragment as GdSearchFrag
    }



  override  fun onDestroyView() {
        super.onDestroyView()
        fragment = null
    }
}