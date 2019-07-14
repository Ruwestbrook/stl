package com.loan.stl.module.user.viewModel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

/**
author: russell
time: 2019-07-13:23:09
describe：
 */
class LoginVM :BaseObservable() {
    /*
    手机号码
     */
    @get:Bindable
    var phone:String?=null
    set(value) {
        field=value
        change()
        notifyPropertyChanged(BR.phone)

    }
    /*
    验证码
     */
    @get:Bindable
    var code:String?=null
    set(value) {
        field=value
        change()
        notifyPropertyChanged(BR.code)
    }


    @get:Bindable
    var protocol=true
    set(value) {
        field=value
        notifyPropertyChanged(BR.protocol)
    }

    @get:Bindable
    var enable=false
    set(value) {
        field=value
        notifyPropertyChanged(BR.enable)
    }

    private fun change(){
        enable =(phone!=null && phone!="" && code!=null && code!="")
    }


}