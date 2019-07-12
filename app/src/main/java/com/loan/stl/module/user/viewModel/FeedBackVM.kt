package com.loan.stl.module.user.viewModel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.loan.stl.BR
import com.loan.stl.utils.TextUtil

/**
author: russell
time: 2019-07-12:14:43
describe：
 */
open class FeedBackVM:BaseObservable() {
    private val MAX_LEN = 160
    private val MIN_LEN = 0

    @get:Bindable
    var enable = false
       set(enable) {
           field = enable
           notifyPropertyChanged(BR.enable)
       }

    @get:Bindable
     var idea: String? = null
        set(idea){
            field=idea
            change()
            notifyPropertyChanged(BR.idea)
        }

    @get:Bindable
    var count = "$MIN_LEN/$MAX_LEN"
    set(value) {
        field=value
        notifyPropertyChanged(BR.count)
    }

    private fun change() {
        count= (MIN_LEN + idea!!.length).toString() + "/" + MAX_LEN
        enable=!TextUtil.isEmpty(idea)

    }
}