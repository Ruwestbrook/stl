package com.loan.stl.module.user.viewModel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

/**
author: russell
time: 2019-07-15:11:01
describe：
 */
class UpdateLoginVM:BaseObservable() {
    @get:Bindable
    var oldPass:String?=null
    set(value) {
        field=value
        change()
        notifyPropertyChanged(BR.oldPass)
    }

    @get:Bindable
    var newPass:String?=null
        set(value) {
            field=value
            change()
            notifyPropertyChanged(BR.newPass)
        }

    @get:Bindable
    var phone:String?=null
        set(value) {
            field=value
            notifyPropertyChanged(BR.phone)
        }

    @get:Bindable
    var repeatPass:String?=null
        set(value) {
            field=value
            change()
            notifyPropertyChanged(BR.repeatPass)
        }

    @get:Bindable
    var enable=false
        set(value) {
            field=value
            notifyPropertyChanged(BR.enable)
        }

    private fun change(){
      enable= oldPass!=null && oldPass!="" &&
            newPass!=null && newPass!="" &&
            repeatPass!=null && repeatPass!=""

    }
}