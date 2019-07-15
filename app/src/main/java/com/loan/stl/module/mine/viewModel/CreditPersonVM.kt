package com.loan.stl.module.mine.viewModel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.loan.stl.BR

/**
author: russell
time: 2019-07-15:19:29
describe：
 */
class CreditPersonVM:BaseObservable() {

    /*
        身份证正面
     */
    @get:Bindable
    var position:String?=null
    set(value) {
        field=value
        notifyPropertyChanged(BR.position)
    }


    @get:Bindable
    var faceImg:String?=null
        set(value) {
            field=value
            notifyPropertyChanged(BR.faceImg)
        }
    /*
        身份证反面
     */
    @get:Bindable
    var back:String?=null
        set(value) {
            field=value
            notifyPropertyChanged(BR.back)
        }
    /*
     idNo
     */
    @get:Bindable
    var idNo:String?=null
        set(value) {
            field=value
            notifyPropertyChanged(BR.idNo)
        }

    /*
    名字
     */
    @get:Bindable
    var  name:String?=null
        set(value) {
        field=value
        notifyPropertyChanged(BR.name)
    }

    /*
        学历
     */
    @get:Bindable
    var education:String?=null
        set(value) {
            field=value
            notifyPropertyChanged(BR.education)
        }


    /** 地址-门牌号  */
    @get:Bindable
    var addressDetail: String? = null
        set(value) {
            field=value
            notifyPropertyChanged(BR.addressDetail)
        }



}