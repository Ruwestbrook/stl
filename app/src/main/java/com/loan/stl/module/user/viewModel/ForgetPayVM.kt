package com.loan.stl.module.user.viewModel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

/**
author: russell
time: 2019-07-15:14:16
describe：
 */
class ForgetPayVM  : BaseObservable() {


    @get:Bindable
    var phone:String?=null
        set(value) {
            field=value
            change()
            notifyPropertyChanged(BR.phone)
        }
    @get:Bindable
    var code:String?=null
        set(value) {
            field=value
            change()
            notifyPropertyChanged(BR.code)
        }


    @get:Bindable
    var name:String?=null
        set(value) {
            field=value
            change()
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var card:String?=null
        set(value) {
            field=value
            change()
            notifyPropertyChanged(BR.card)
        }

    @get:Bindable
    var enable=false
        set(value) {
            field=value
            notifyPropertyChanged(BR.enable)
        }

    private fun change(){
        enable=  card!=null && card!="" &&
                name!=null && name!="" &&
                code!=null && code!="" &&
                phone!=null && phone!=""

    }



}