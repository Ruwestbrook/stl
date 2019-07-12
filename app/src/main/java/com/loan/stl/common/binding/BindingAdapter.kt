package com.loan.stl.common.binding

import android.view.View
import com.loan.stl.common.NoDoubleClick
import com.loan.stl.module.home.viewControl.MineControl

/**
 * author: russell
 * time: 2019-07-12:11:36
 * describe：
 */
class BindingAdapter{
    companion object{
        open fun click(view: View,mineControl: MineControl){
            view.setOnClickListener(object : NoDoubleClick() {
                override fun onMultiClick(v: View) {
                    mineControl.openSetting(v)
                }
            })


        }

    }
}
