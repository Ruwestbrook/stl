package com.loan.stl.module.mine.viewModel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

/**
author: russell
time: 2019-07-17:10:48
describe：
 */
class LoanVM : BaseObservable() {

    /*
    最大额度
     */
    var maxQuota=0

    @get:Bindable
    var money:String?=null
        set(value) {
            field=value
            checkMoney()
            getPlan()
            change()
            notifyPropertyChanged(BR.money)
        }

    private fun checkMoney() {
       if(money!=null && money!=""){
           if(money!!.length>1 && money!!.indexOf("0")==0)
               money=money!!.substring(1,money!!.length)
           if(money!="" && money!!.toInt()>maxQuota)
               money=maxQuota.toString()
       }

    }


    /*
    是否是固定额度
     */
    @get:Bindable
    var fixed:Boolean = false
    set(value) {
        field=value
        if(fixed)
            money=maxQuota.toString()
        notifyPropertyChanged(BR.fixed)
    }


    /*
    是否可以下一步
     */
    @get:Bindable
    var enable:Boolean=false
        set(value) {
            field=value
            notifyPropertyChanged(BR.enable)
        }


    /*
    是否显示还款计算
     */
    @get:Bindable
    var plan:Boolean=false
        set(value) {
            field=value
            notifyPropertyChanged(BR.plan)
        }

    @get:Bindable
    var planText:String?=null
        set(value) {
            field=value
            notifyPropertyChanged(BR.planText)
        }

    /*
    怎么用
     */
    @get:Bindable
    var use:String?=null
        set(value) {
            field=value
            getPlan()
            change()
            notifyPropertyChanged(BR.use)
        }


    /*
    还款方式
     */
    @get:Bindable
    var returnType:String?=null
        set(value) {
            field=value
            getPlan()
            change()
            notifyPropertyChanged(BR.returnType)
        }


    /*
        是否可以选择还款方式
     */
    @get:Bindable
    var chooseType:Boolean=false
        set(value) {
            field=value
            notifyPropertyChanged(BR.chooseType)
        }




    /*
        借多久
     */
    @get:Bindable
    var term:String?=null
        set(value) {
            field=value
            getPlan()
            change()
            notifyPropertyChanged(BR.term)
        }


    /*
        借多久
     */
    @get:Bindable
    var card:String?=null
        set(value) {
            field=value
            notifyPropertyChanged(BR.card)
        }




    /*
            出借公司
         */
    @get:Bindable
    var company:String?=null
        set(value) {
            field=value
            notifyPropertyChanged(BR.company)
        }

    private fun change(){
        enable= card !=null  && card!="" &&
                term !=null  && term!="" &&
                use !=null  && use!="" &&
                returnType !=null  && returnType!="" &&
                money !=null  && money!=""
    }

    private fun getPlan(){
        plan=   term !=null  && term!="" &&
                use !=null  && use!="" &&
                returnType !=null  && returnType!="" &&
                money !=null  && money!=""
    }
}