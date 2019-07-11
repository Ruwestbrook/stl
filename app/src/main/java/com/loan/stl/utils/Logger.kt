package com.loan.stl.utils

import android.util.Log

/**
author: russell
time: 2019-07-11:15:03
describe：
 */
class Logger {
    companion object {
        val tag="stl__loan"
        fun d(text:String){
            Log.d(tag,text)
        }
        fun d(text:Int){
            Log.d(tag, text.toString())
        }
        fun d(text:Boolean){
            Log.d(tag, text.toString())
        }



    }
}