package com.loan.stl.common

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.loan.stl.R
import kotlinx.android.synthetic.main.activity_login.view.*

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

    fun setPageTitle(title:String?){
        val textView=findViewById<TextView>(R.id.title)
        textView.text=title
        val imageView=findViewById<TextView>(R.id.back_button)
        imageView.setOnClickListener {
            finish()
        }

    }

    fun setPageTitle(title:Int){
        val textView=findViewById<TextView>(R.id.title)
        textView.text=resources.getText(title)
        val imageView=findViewById<TextView>(R.id.back_button)
        imageView.setOnClickListener {
            finish()
        }

    }
    fun setPageTitle(title:String?,show:Boolean){
        val textView=findViewById<TextView>(R.id.title)
        textView.text=title
        val imageView=findViewById<TextView>(R.id.back_button)
        val rightExplain=findViewById<TextView>(R.id.right_explain)
        imageView.setOnClickListener {
            finish()
        }
        rightExplain.visibility=if(show) View.VISIBLE else View.GONE

    }

}

