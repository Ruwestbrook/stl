package com.loan.stl.module.user.viewModel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

/**
author: russell
time: 2019-07-15:13:47
describe：
 */
class ForgetLoginVM :BaseObservable() {


    @get:Bindable
    var phone:String?=null
        set(value) {
            field=value
            nextStep()
            notifyPropertyChanged(BR.phone)
        }
    @get:Bindable
    var code:String?=null
        set(value) {
            field=value
            nextStep()
            notifyPropertyChanged(BR.code)
        }



    @get:Bindable
    var newPass:String?=null
        set(value) {
            field=value
            change()
            notifyPropertyChanged(BR.newPass)
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
        enable=  newPass!=null && newPass!="" &&
                repeatPass!=null && repeatPass!=""

    }

    private fun nextStep() {
        next=phone!=null && phone!="" &&
                code!=null && code!=""
    }

    @get:Bindable
    var next=false
        set(value) {
            field=value
            notifyPropertyChanged(BR.next)
        }
}