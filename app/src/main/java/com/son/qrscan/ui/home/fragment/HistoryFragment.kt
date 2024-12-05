package com.son.qrscan.ui.home.fragment

import android.view.LayoutInflater
import com.son.common.ui.base.BaseFragment
import com.son.qrscan.databinding.FragmentHistoryBinding

class HistoryFragment: BaseFragment<FragmentHistoryBinding>() {
    override fun inflateLayout(inflater: LayoutInflater): FragmentHistoryBinding {
        return FragmentHistoryBinding.inflate(inflater)
    }
}