package com.loan.stl.module.user.viewModel

import android.text.TextUtils
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.loan.stl.LoanApplication
import com.loan.stl.R

/**
author: russell
time: 2019-07-15:14:16
describe：
 */
class ForgetPayVM  : BaseObservable() {

    @get:Bindable
    var title = LoanApplication.context.resources.getString(R.string.settings_forgot_pay_title)
        set(value) {
            field = value

            notifyPropertyChanged(BR.title)
        }
    @get:Bindable
    var phone: String? = null
        set(value) {
            field = value
            checkInput()
            codeEnableCheck()
            notifyPropertyChanged(BR.phone)
        }
    @get:Bindable
    var name: String? = null
        set(value) {
            field = value
            checkInput()
            notifyPropertyChanged(BR.name)
        }
    /*
    身份证号码
     */
    @get:Bindable
    var no: String? = null
        set(value) {
            field = value
            checkInput()
            notifyPropertyChanged(BR.no)
        }
    var code: String? = null
        set(value) {
            field = value
            checkInput()
            notifyPropertyChanged(BR.code)
        }
    @get:Bindable
    var codeEnable = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.codeEnable)
        }
    @get:Bindable
    var enable = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.enable)
        }
    /**
     * TimeButton是否可用
     */
    private fun codeEnableCheck() {
        codeEnable = !TextUtils.isEmpty(phone)
    }

    /**
     * 输入校验
     */
    private fun checkInput() {
        enable = !(TextUtils.isEmpty(no) || no?.length!! < 15 || TextUtils.isEmpty(name)
                || name?.length!!   < 2 || TextUtils.isEmpty(code))
    }
}