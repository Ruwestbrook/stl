package com.loan.stl.common.binding

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.widget.AbsListView
import android.widget.ImageView
import android.widget.ListView
import android.widget.ScrollView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.loan.stl.R
import com.loan.stl.common.SwipeListener
import com.loan.stl.utils.ConverterUtil
import com.loan.stl.utils.RegularUtil



/**
 * author: russell
 * time: 2019-07-12:11:36
 * describe：
 */
class BindingAdapter{
    companion object{

        /**
         * 设置view是否显示
         */
        @BindingAdapter("visibility")
        @JvmStatic
        fun viewVisibility(view: View, visible: Boolean) {
            if (visible) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.GONE
            }
        }

        /**
         * 设置view是否显示
         */
        @BindingAdapter("invisibility")
        @JvmStatic
        fun viewInVisibility(view: View, visible: Boolean) {
            if (visible) {
                view.visibility = View.VISIBLE
            } else {
                view.visibility = View.INVISIBLE
            }
        }

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

        /**
         * 配置下拉刷新事件
         */
        @Suppress("DEPRECATION")
        @BindingAdapter(value = [ "listener" ])
        @JvmStatic
        fun listener(layout: SwipeToLoadLayout, listener: SwipeListener?) {
            if (listener != null) {
                val mInflater = layout.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                // 设置头部、底部加载刷新控件
                layout.setRefreshHeaderView(mInflater.inflate(R.layout.swipe_twitter_header, layout, false))
                layout.setLoadMoreFooterView(mInflater.inflate(R.layout.swipe_twitter_footer, layout, false))
                layout.setSwipeStyle(SwipeToLoadLayout.STYLE.CLASSIC)
                layout.isLoadMoreEnabled = true
                layout.post { layout.isRefreshing = true }

                if (layout.childCount >= 2) {
                    for (i in 0 until layout.childCount) {
                        val tClass = layout.getChildAt(i)
                        if (tClass is RecyclerView) {// 判断滑动布局是否为recyclerView，添加自动加载事件
                            tClass.setOnScrollListener(object : RecyclerView.OnScrollListener() {
                               override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                                        if (!ViewCompat.canScrollVertically(recyclerView, 2)) {
                                            layout.isLoadingMore = true
                                        }
                                    }
                                }
                            })
                            break
                        } else if (tClass is ListView) {// 判断滑动布局是否为ListView，添加自动加载事件
                            tClass.setOnScrollListener(object : AbsListView.OnScrollListener {
                                override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {
                                    if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                                        if (view.lastVisiblePosition == view.count - 1 && !ViewCompat.canScrollVertically(
                                                view,
                                                1
                                            )
                                        ) {
                                            layout.isLoadingMore = true
                                        }
                                    }
                                }

                                override fun onScroll(absListView: AbsListView, i: Int, i1: Int, i2: Int) {}
                            })
                            break
                        } else if (tClass is ScrollView) {// 判断滑动布局是否为ScrollView，添加自动加载事件
                            layout.isLoadMoreEnabled = false
                            tClass.viewTreeObserver.addOnScrollChangedListener {
                                if (tClass.getChildAt(0).height < tClass.scrollY + tClass.height && !ViewCompat
                                        .canScrollVertically(tClass, 1)
                                ) {
                                    layout.isLoadingMore = true
                                }
                            }
                            break
                        }
                    }
                }

                // 添加响应事件方法
                layout.setOnLoadMoreListener(listener)
                layout.setOnRefreshListener(listener)
                // 初始化刷新方法类
                listener!!.swipeInit(layout)
            }
        }




    }


}
