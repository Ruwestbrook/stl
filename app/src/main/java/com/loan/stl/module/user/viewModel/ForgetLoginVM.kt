package com.loan.stl.module.user.viewModel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.loan.stl.LoanApplication
import com.loan.stl.R
import com.loan.stl.utils.InputCheck
import com.loan.stl.utils.RegularUtil
import com.loan.stl.utils.TextUtil

/**
author: russell
time: 2019-07-15:13:47
describe：
 */
class ForgetLoginVM :BaseObservable() {

    /* 忘记修改面第一步 */
    /** 手机号  */
    @get:Bindable
    var phone: String? = null
        set(value) {
            field=value
            checkInput()
            codeEnableCheck()
            notifyPropertyChanged(BR.phone)
        }
    /** 验证码  */
    @get:Bindable
    var code: String? = null
        set(value) {
            field=value
            checkInput()
            notifyPropertyChanged(BR.code)
        }


    /** 获取验证码按钮是否可用  */
    @get:Bindable
    var codeEnable: Boolean = false
        set(value) {
            field=value
            notifyPropertyChanged(BR.codeEnable)
        }
    /** 下一步按钮是否可用  */
    @get:Bindable
    var enable: Boolean = false
        set(value) {
            field=value
            notifyPropertyChanged(BR.enable)
        }
    /* 忘记修改面第二步 */
    /** 修改按钮是否可用  */
    @get:Bindable
    var updateEnable: Boolean = false
        set(value) {
            field=value
            notifyPropertyChanged(BR.updateEnable)
        }


    /** 新密码  */
    @get:Bindable
    var pwd: String? = null
        set(value) {
            field=value
            this.pwd = pwd
            checkInputUpdate()
            notifyPropertyChanged(BR.pwd)
        }


    /** 确认新密码  */
    @get:Bindable
    var confirmPwd: String? = null
        set(value) {
            field=value
            this.confirmPwd = confirmPwd
            checkInputUpdate()
            notifyPropertyChanged(BR.confirmPwd)
        }





    @get:Bindable
    var title = LoanApplication.context.resources.getString(R.string.forgot_pwd_title_step_1)   //忘记密码标题
        set(value) {
            field=value
            notifyPropertyChanged(BR.title)
        }

    /**
     * TimeButton是否可用
     */
    fun codeEnableCheck() {
        codeEnable = RegularUtil.isPhoneLength(phone)
    }

    /**
     * 输入校验
     */
    fun checkInput() {
        enable = !TextUtil.isEmpty(phone) && InputCheck.checkCode(code)
    }

    /**
     * 修改密码输入校验
     */
    fun checkInputUpdate() {
        updateEnable =
            !(TextUtil.isEmpty(pwd) || TextUtil.isEmpty(confirmPwd) || pwd!!.length < 6 || confirmPwd!!.length < 6 || confirmPwd!!.length > 16 || pwd!!.length > 16)
    }
}