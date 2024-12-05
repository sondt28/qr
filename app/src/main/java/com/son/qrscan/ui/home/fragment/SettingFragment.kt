package com.son.qrscan.ui.home.fragment

import android.view.LayoutInflater
import com.son.common.ui.base.BaseFragment
import com.son.qrscan.databinding.FragmentSettingBinding

class SettingFragment : BaseFragment<FragmentSettingBinding>() {
    override fun inflateLayout(inflater: LayoutInflater): FragmentSettingBinding {
        return FragmentSettingBinding.inflate(inflater)
    }
}