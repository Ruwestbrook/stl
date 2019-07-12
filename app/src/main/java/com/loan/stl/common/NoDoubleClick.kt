package com.loan.stl.common

import android.view.View
import com.loan.stl.utils.LogUtils

/**
author: russell
time: 2019-07-12:11:39
describe：
 */
abstract class NoDoubleClick : View.OnClickListener {

    abstract fun onMultiClick(v: View)

    override fun onClick(v: View) {
        val curClickTime = System.currentTimeMillis()
        if (curClickTime - lastClickTime >= MIN_CLICK_DELAY_TIME) {
            // 超过点击间隔后再将lastClickTime重置为当前点击时间
            lastClickTime = curClickTime
            onMultiClick(v)
        }else{
            LogUtils.d("重复点击")
        }
    }

    companion object {
        // 两次点击按钮之间的点击间隔不能少于1500毫秒
        private val MIN_CLICK_DELAY_TIME = 1500
        private var lastClickTime: Long = 0
    }
}