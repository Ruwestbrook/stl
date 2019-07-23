package com.loan.stl.module.user.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.loan.stl.R
import com.loan.stl.common.BaseActivity
import com.loan.stl.databinding.ActivityFeedbackBinding
import com.loan.stl.module.user.viewControl.FeedBackControl
import com.loan.stl.router.RouterUrl

/**
author: russell
time: 2019-07-12:14:35
describe：意见反馈
 */
@Route(path = RouterUrl.FEEDBACK_PAGE)
class FeedbackActivity :BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityFeedbackBinding=DataBindingUtil.
            setContentView<ActivityFeedbackBinding>(this,R.layout.activity_feedback)
        setPageTitle("意见反馈",false)
        activityFeedbackBinding.ctrl= FeedBackControl()
    }



}