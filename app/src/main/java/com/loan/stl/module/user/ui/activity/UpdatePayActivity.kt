package com.loan.stl.module.user.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.loan.stl.R
import com.loan.stl.common.BaseActivity
import com.loan.stl.common.BundleKeys
import com.loan.stl.common.Constant
import com.loan.stl.common.RequestResultCode
import com.loan.stl.databinding.ActivityUpdatePayBinding
import com.loan.stl.module.user.viewControl.PayControl
import com.loan.stl.router.RouterUrl

/**
author: russell
time: 2019-07-15:10:06
describe：修改交易密码
 */
@Route(path = RouterUrl.UPDATE_PASSWORD)
class UpdatePayActivity : BaseActivity() {

    @Autowired(name = BundleKeys.TYPE)
    @JvmField
    var payType:Int=0

    var ctrl:PayControl?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
      val binding= DataBindingUtil.setContentView<ActivityUpdatePayBinding>(this,R.layout.activity_update_pay)
        binding.ctrl= PayControl(binding,payType,this)
    }

    @Suppress("UNUSED_PARAMETER")
    fun finishPage(view: View){
        finish()
    }

    @Suppress("UNUSED_PARAMETER")
    fun forget(view: View){
        ARouter.getInstance().build(RouterUrl.FORGET_PASSWORD).navigation(this,
            RequestResultCode.REQ_FORGOT_PAY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (Activity.RESULT_OK == resultCode) {
            ctrl?.setPayType(Constant.NUMBER_3)
        }

    }

}
