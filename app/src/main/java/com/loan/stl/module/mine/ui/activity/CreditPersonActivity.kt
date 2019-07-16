package com.loan.stl.module.mine.ui.activity

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.loan.stl.R
import com.loan.stl.common.BaseActivity
import com.loan.stl.common.RequestResultCode
import com.loan.stl.databinding.ActivityCreditPersonBinding
import com.loan.stl.module.mine.viewControl.CreditControl
import com.loan.stl.utils.ToastUtils

/**
author: russell
time: 2019-07-15:18:08
describe：完善资料页面
 */
class CreditPersonActivity:BaseActivity() {

    var creditControl:CreditControl?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=DataBindingUtil.setContentView<ActivityCreditPersonBinding>(this, R.layout.activity_credit_person)
        creditControl=CreditControl(this)
        creditControl?.requestPermissions(false)
        binding.ctrl=creditControl
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            RequestResultCode.REQ_MAP_CODE ->{
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    creditControl?.openMap()
                } else {
                    ToastUtils.toast(R.string.linker_permission_error)

                }
            }
            RequestResultCode.REQ_LOCATION_CODE-> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        //开始定位

                } else {
                    ToastUtils.toast(R.string.linker_permission_error)

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }
        }
    }

}