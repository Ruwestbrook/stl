package com.loan.stl.common.binding

import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Author: TinhoXu
 * E-mail: xth@erongdu.com
 * Date: 2016/3/1 11:57
 *
 *
 * Description: 分割线装饰
 */
class DividerLine @JvmOverloads constructor(// private Drawable dividerBackground;
    // 布局方向
    private val orientation: Int = DEFAULT
) : RecyclerView.ItemDecoration() {
    private var mDivider: Drawable? = null
    // 起始点间距
    private var marginStart = 0
    // 结束点间距
    private var marginEnd = 0

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        if (orientation == VERTICAL) {
            drawVertical(c, parent)
        } else if (orientation == HORIZONTAL) {
            drawHorizontal(c, parent)
        } else if (orientation == CROSS) {
            drawVertical(c, parent)
            drawHorizontal(c, parent)
        } else {
            // TODO
        }
    }

    /**
     * 设置分割线起始点的间距
     *
     * @param marginStart
     * 尺寸
     */
    fun setMarginStart(marginStart: Int) {
        this.marginStart = marginStart
    }

    /**
     * 设置分割线结束点的间距
     *
     * @param marginEnd
     * 尺寸
     */
    fun setMarginEnd(marginEnd: Int) {
        this.marginEnd = marginEnd
    }

    // 绘制垂直分割线
    private fun drawVertical(c: Canvas, parent: RecyclerView) {
        // 我们通过获取系统属性中的 dividerVertical 来添加，在系统中的AppTheme中设置
        val ta = parent.context.obtainStyledAttributes(intArrayOf(android.R.attr.dividerVertical))
        this.mDivider = ta.getDrawable(0)
        ta.recycle()

        val top = parent.paddingTop
        val bottom = parent.height - parent.paddingBottom

        val childCount = parent.childCount
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            // 获得child的布局信息
            val params = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + params.rightMargin
            val right = left + mDivider!!.intrinsicWidth

            mDivider!!.setBounds(left, top + marginStart, right, bottom - marginEnd)
            mDivider!!.draw(c)
        }
    }

    // 绘制水平分割线
    private fun drawHorizontal(c: Canvas, parent: RecyclerView) {
        // 我们通过获取系统属性中的 dividerHorizontal 来添加，在系统中的AppTheme中设置
        val ta = parent.context.obtainStyledAttributes(intArrayOf(android.R.attr.dividerHorizontal))
        this.mDivider = ta.getDrawable(0)
        ta.recycle()
        // 填充分割线margin或者padding部分的颜色
        // dividerBackground = ContextCompat.getDrawable(parent.getContext(), R.drawable.divider_backgroud);

        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            // 获得child的布局信息
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider!!.intrinsicHeight

            // dividerBackground.setBounds(left, top, right, bottom);
            // dividerBackground.draw(c);
            mDivider!!.setBounds(left + marginStart, top, right - marginEnd, bottom)
            mDivider!!.draw(c)
        }
    }

    companion object {
        /** 默认(不显示分割线) - -1  */
        val DEFAULT = -1
        /** 水平方向 - 0  */
        val HORIZONTAL = LinearLayoutManager.HORIZONTAL
        /** 垂直方向 - 1  */
        val VERTICAL = LinearLayoutManager.VERTICAL
        /** 全方向 - 9  */
        val CROSS = 9
    }

    // 由于Divider也有长宽高，每一个Item需要向下或者向右偏移
    //@Override
    //public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    //    super.getItemOffsets(outRect, view, parent, state);
    //    if (null == outRect || null == mDivider || parent.getChildLayoutPosition(view) == RecyclerView.NO_POSITION) {
    //        return;
    //    }
    //    switch (orientation) {
    //        // 画横线，就是往下偏移一个分割线的高度
    //        case HORIZONTAL:
    //            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
    //            break;
    //
    //        // 画竖线，就是往右偏移一个分割线的宽度
    //        case VERTICAL:
    //            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
    //            break;
    //
    //        // 画交叉线，就是往下偏移一个分割线的高度，往右偏移一个分割线的宽度
    //        case CROSS:
    //            break;
    //    }
    //}
}
