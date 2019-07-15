package com.loan.stl.utils

import android.util.Log
import android.widget.Toast
import com.loan.stl.LoanApplication

/**
author: russell
time: 2019-07-11:15:03
describe：
 */
class LogUtils {
    companion object {
        val tag="stl__loan"
        fun d(text:String?){
            if(text!=null)
                Log.d(tag,text)
            else
                Log.d(tag,"空值")
        }
        fun d(text:Int?){
            if(text!=null)
                Log.d(tag,text.toString())
            else
                Log.d(tag,"空值")
        }
        fun d(text:Boolean){
            if(text!=null)
                Log.d(tag,text.toString())
            else
                Log.d(tag,"空值")
        }



    }
}
class ToastUtils{
    companion object{
        @JvmStatic
        fun toast(text: String?){
            Toast.makeText(LoanApplication.context,text,Toast.LENGTH_SHORT).show()
        }
        @JvmStatic
        fun toast(text: Int) {
            var context=LoanApplication.context
            Toast.makeText(context,context.getText(text),Toast.LENGTH_SHORT).show()
        }

    }
}
