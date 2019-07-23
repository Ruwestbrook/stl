package com.loan.stl.module.mine.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.loan.stl.R
import com.loan.stl.databinding.ActivityGdMapBinding
import com.loan.stl.module.mine.viewControl.GdMapCtrl
import com.loan.stl.router.RouterUrl

/**
author: russell
time: 2019-07-15:14:38
describe：高德地图定位
 */
@Route(path = RouterUrl.ACTIVITY_MAP)
class GdMapActivity : AppCompatActivity() {
    lateinit var binding:ActivityGdMapBinding
    private lateinit var viewCtrl: GdMapCtrl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_gd_map)
        binding.map.onCreate(savedInstanceState)
        viewCtrl=GdMapCtrl(binding,this)
        binding.ctrl=viewCtrl
    }


    override fun onDestroy() {
        super.onDestroy()
        binding.map.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        binding.map.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.map.onPause()
        viewCtrl.deactivate()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.map.onSaveInstanceState(outState)
    }
}
