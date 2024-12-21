package com.son.qrscan.ui.onboarding

import android.view.LayoutInflater
import com.son.common.ui.base.BaseActivity
import com.son.qrscan.databinding.ActivityOnBoardingBinding


class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityOnBoardingBinding {
        return ActivityOnBoardingBinding.inflate(inflater)
    }

    override fun setupUI() {
        val listTitle = arrayListOf(
            "Tab 1",
            "Tab 2",
            "Tab 3",
        )
        val adapter = OBViewPagerAdapter(this, listTitle)
        binding.viewPager.adapter = adapter
        binding.dotsIndicatorOnboard.attachTo(binding.viewPager)
    }

}