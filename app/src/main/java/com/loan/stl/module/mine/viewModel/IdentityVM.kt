package com.loan.stl.module.mine.viewModel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

/**
author: russell
time: 2019-07-15:23:35
describe：
 */
class IdentityVM:BaseObservable() {
    @get:Bindable
    var workType:String?=null
        set(value) {
            field=value
            change()
            notifyPropertyChanged(BR.workType)
        }



    @get:Bindable
    var enable:Boolean=false
        set(value) {
            field=value
            notifyPropertyChanged(BR.enable)
        }



    @get:Bindable
    var salary:String?=null
        set(value) {
            field=value
            change()
            notifyPropertyChanged(BR.salary)
        }




    private fun change(){
        enable= salary !=null  && salary!="" &&
                workType !=null  && workType!=""
    }
}