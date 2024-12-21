package com.son.qrscan.ui.onboarding

import android.view.LayoutInflater
import androidx.viewpager2.widget.ViewPager2
import com.son.common.ui.base.BaseActivity
import com.son.qrscan.R
import com.son.qrscan.databinding.ActivityOnBoardingBinding


class OnBoardingActivity : BaseActivity<ActivityOnBoardingBinding>() {
    lateinit var listTitle : ArrayList<String>
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityOnBoardingBinding {
        return ActivityOnBoardingBinding.inflate(inflater)
    }

    override fun setupUI() {
        listTitle = arrayListOf(
            "Tab 1",
            "Tab 2",
            "Tab 3",
        )
        val adapter = OBViewPagerAdapter(this, listTitle)
        binding.viewPager.adapter = adapter
        binding.dotsIndicatorOnboard.attachTo(binding.viewPager)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == listTitle.size - 1) {
                    binding.tvNext.text = getString(com.son.common.R.string.onboarding_get_started)
                } else {
                    binding.tvNext.text = getString(com.son.common.R.string.onboarding_next)
                }
            }
        })
    }

    override fun setupListener() {
        binding.tvNext.setOnClickListener {
            val currentItem = binding.viewPager.currentItem
            if (currentItem < listTitle.size - 1) {
                binding.viewPager.currentItem = currentItem + 1
            }
        }


    }

}