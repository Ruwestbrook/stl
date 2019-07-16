package com.loan.stl.module.mine.viewModel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

/**
author: russell
time: 2019-07-15:21:15
describe：
 */
class ContactVM:BaseObservable() {
    @get:Bindable
    var phone:String?=null
        set(value) {
            field=value
            change()
            notifyPropertyChanged(BR.phone)
        }



    @get:Bindable
    var enable:Boolean=false
        set(value) {
            field=value
            notifyPropertyChanged(BR.enable)
        }



    @get:Bindable
    var name:String?=null
        set(value) {
            field=value
            change()
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var relation:String?=null
        set(value) {
            field=value
            change()
            notifyPropertyChanged(BR.relation)
        }


    private fun change(){
        enable= name !=null  && name!="" &&
                relation !=null  && relation!="" &&
                phone !=null  && phone!="" &&
                name !=null  && name!=""
    }

}