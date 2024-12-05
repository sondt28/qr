package com.son.qrscan.ui.home.fragment

import android.view.LayoutInflater
import com.son.common.ui.base.BaseFragment
import com.son.qrscan.databinding.FragmentScanBinding

class ScanFragment : BaseFragment<FragmentScanBinding>() {
    override fun inflateLayout(inflater: LayoutInflater): FragmentScanBinding {
        return FragmentScanBinding.inflate(inflater)
    }


}