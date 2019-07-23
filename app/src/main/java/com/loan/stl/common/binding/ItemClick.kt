package com.loan.stl.common.binding

import android.view.View

/**
 * created by russell on 2019/3/10
 * email:igruwestbrook@gmail.com
 * Description:
 */
interface ItemClick {
    fun Click(view: View, o: Any)

}
interface PositionClick{
    fun click(view: View,position:Int)
}
