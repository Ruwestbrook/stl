package com.loan.stl.common.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.loan.stl.common.NoDoubleClick
import com.loan.stl.module.home.viewControl.MineControl

/**
 * author: russell
 * time: 2019-07-12:11:36
 * describe：
 */
class BindingAdapter{
    companion object{
        @BindingAdapter("visibility")
        @JvmStatic
        open fun visibility(view: View,text:String?){
            view.visibility=
                if(text==null || text == "")
                    View.GONE
                else
                    View.VISIBLE

        }

    }
}
