package com.loan.stl.common

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
author: russell
time: 2019-07-11:11:54
describe：ui基类
 */
/*
 fragment基类
 */
open class BaseFragment:Fragment(){

}
/*
 activity基类
 */
@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity(){

    fun setTitle(title:String){

    }

}

