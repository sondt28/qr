package com.son.qrscan.ui.splash

import android.view.LayoutInflater
import com.son.common.ui.base.BaseActivity
import com.son.qrscan.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(inflater)
    }

}