package com.loan.stl.module.user.viewControl

import android.view.View
import com.loan.stl.module.user.viewModel.FeedBackVM
import com.loan.stl.utils.LogUtils
import com.loan.stl.utils.ToastUtils

/**
author: russell
time: 2019-07-12:15:03
describe：
 */
class FeedBackControl {
    var feedBackVM:FeedBackVM?=null
    init {
        feedBackVM= FeedBackVM()
    }

    /* 提交 */
    fun submit(view: View){
        if(feedBackVM?.idea==null){
            ToastUtils.toast("请输入信息")
            return
        }

        if(containsEmoji(feedBackVM?.idea)){
            ToastUtils.toast("含有表情符号")
            return
        }
        LogUtils.d(feedBackVM?.idea)
        ToastUtils.toast("提交表单")

    }


    /**
     * 检测是否有emoji表情
     */
    fun containsEmoji(source: String?): Boolean {
        if(source==null)
            return  false
        val len = source.length
        for (i in 0 until len) {
            val codePoint = source[i]
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true
            }
        }
        return false
    }

    private fun isEmojiCharacter(codePoint: Char): Boolean {
        return codePoint.toInt() == 0x0 || codePoint.toInt() == 0x9 || codePoint.toInt() == 0xA ||
                codePoint.toInt() == 0xD || codePoint.toInt() in 0x20..0xD7FF ||
                codePoint.toInt() in 0xE000..0xFFFD || codePoint.toInt() in 0x10000..0x10FFFF
    }

}