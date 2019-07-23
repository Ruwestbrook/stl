package com.loan.stl.common

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout
import com.loan.stl.common.binding.BaseRecyclerViewAdapter
import com.loan.stl.network.entity.PageMo

/**
author: russell
time: 2019-07-17:18:50
describe：
 */
open class CommonControl {

    //******************公共控件*************************
    /** 下拉刷新事件  */
    var listener: ObservableField<SwipeListener> = ObservableField()
    /** 下拉刷新控件  */
    private var swipeLayout: SwipeToLoadLayout? = null

    fun getSwipeLayout(): SwipeToLoadLayout? {
        return swipeLayout
    }

    fun setSwipeLayout(swipeToLoadLayout: SwipeToLoadLayout) {
        this.swipeLayout = swipeToLoadLayout
    }
}