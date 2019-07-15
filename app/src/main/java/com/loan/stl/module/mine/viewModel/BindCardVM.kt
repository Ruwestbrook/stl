package com.loan.stl.module.mine.viewModel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

/**
author: russell
time: 2019-07-15:16:18
describe：
 */
class BindCardVM  : BaseObservable() {


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
    var usePhone:Boolean=false
        set(value) {
            field=value
            change()
            notifyPropertyChanged(BR.usePhone)
        }

    @get:Bindable
    var enable:Boolean=false
        set(value) {
            field=value
            change()
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
    var bankName:String?=null
        set(value) {
            field=value
            change()
            change()
            notifyPropertyChanged(BR.bankName)
        }



    @get:Bindable
    var cardNo:String?=null
        set(value) {
            field=value
            change()
            notifyPropertyChanged(BR.cardNo)
        }







    private fun change(){
      enable=  if(usePhone){
            cardNo !=null  && cardNo!="" &&
            bankName !=null  && bankName!="" &&
            name !=null  && name!="" &&
            phone !=null  && phone!="" &&
            code !=null  && code!=""
        }else{
           cardNo !=null  && cardNo!="" &&
           bankName !=null  && bankName!="" &&
           name !=null  && name!=""
        }
    }


}