package com.loan.stl.common.binding

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.loan.stl.R
import com.loan.stl.utils.ConverterUtil
import com.loan.stl.utils.RegularUtil



/**
 * author: russell
 * time: 2019-07-12:11:36
 * describe：
 */
class BindingAdapter{
    companion object{
        @BindingAdapter("visibility")
        @JvmStatic
        fun visibility(view: View,text:String?){
            view.visibility=
                if(text==null || text == "")
                    View.GONE
                else
                    View.VISIBLE
        }


        /**
         * 为ImageView设置图片
         *
         * @param imageView
         * imageView
         * @param path
         * 路径
         * @param defaultImage
         * 默认图片
         * @param errorImage
         * 加载失败图片
         */
        @BindingAdapter(value = ["src", "defaultImage", "errorImage"], requireAll = false)
        @JvmStatic
        fun setImage(imageView: ImageView, path: String, defaultImage: Drawable?, errorImage: Drawable?) {
            var errorImage = errorImage
            val context = imageView.rootView.context
            try {
                System.gc()
                if (null == errorImage) {
                    errorImage = ContextCompat.getDrawable(context, com.loan.stl.R.mipmap.ic_launcher)
                }
                val options = RequestOptions()
                    .centerCrop()
                    .placeholder(defaultImage)
                    .error(errorImage)
                    .priority(Priority.HIGH)
                if (RegularUtil.isNumeric(path)) {
                    Glide.with(context)
                        .setDefaultRequestOptions(options)
                        .load(ConverterUtil.getInteger(path))
                        .thumbnail(0.1f)
                        .into(imageView)
                } else {
                    Glide.with(context)
                        .setDefaultRequestOptions(options)
                        .load(path)
                        .thumbnail(0.1f)
                        .into(imageView)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }
}
