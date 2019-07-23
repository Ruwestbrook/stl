package com.loan.stl.common

import com.aspsine.swipetoloadlayout.OnLoadMoreListener
import com.aspsine.swipetoloadlayout.OnRefreshListener
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout

/**
 * Author: Chenming
 * E-mail: cm1@erongdu.com
 * Date: 16/11/7 下午5:46
 *
 *
 * Description:下拉刷新
 */
abstract class SwipeListener : OnRefreshListener, OnLoadMoreListener {
    override fun onLoadMore() {
        loadMore()
    }

    override fun onRefresh() {
        refresh()
    }

    abstract fun swipeInit(swipeLayout: SwipeToLoadLayout)

    abstract fun refresh()

    abstract fun loadMore()
}
