package com.loan.stl.module.user.viewControl

import android.view.View
import com.loan.stl.module.user.dataModel.submit.IdeaSub
import com.loan.stl.module.user.viewModel.FeedBackVM
import com.loan.stl.network.HttpClient
import com.loan.stl.network.ResponseCallback
import com.loan.stl.network.api.MineService
import com.loan.stl.network.entity.HttpResult
import com.loan.stl.utils.LogUtils
import com.loan.stl.utils.ToastUtils
import com.loan.stl.utils.Util
import retrofit2.Call
import retrofit2.Response

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
            ToastUtils.toast("请删除表情符号后再提交")
            return
        }
        LogUtils.d(feedBackVM?.idea)

        val call = HttpClient.getService(MineService::class.java).opinion(IdeaSub(feedBackVM?.idea))
        call.enqueue(object : ResponseCallback<HttpResult<Any>>() {
           override fun onSuccess(call: Call<HttpResult<Any>>, response: Response<HttpResult<Any>>) {
                ToastUtils.toast(response.body()!!.msg)
                Util.getActivity(view).finish()
            }
        })

    }


    /**
     * 检测是否有emoji表情
     */
    private fun containsEmoji(source: String?): Boolean {
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