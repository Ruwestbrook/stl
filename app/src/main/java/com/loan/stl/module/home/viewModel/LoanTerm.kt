package com.loan.stl.module.home.viewModel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.loan.stl.BR

/**
author: russell
time: 2019-07-17:13:43
describe：
 */
class LoanTerm:BaseObservable() {
    @set:Bindable
    var selected:Boolean = false
    set(value) {
        field=value
        notifyPropertyChanged(BR.selected)
    }

    @set:Bindable
    var term:String? = null
        set(value) {
            field=value
            notifyPropertyChanged(BR.term)
        }
}