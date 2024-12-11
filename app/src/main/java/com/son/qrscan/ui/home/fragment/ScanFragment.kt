package com.son.qrscan.ui.home.fragment

import android.view.LayoutInflater
import com.son.common.ui.base.BaseFragment
import com.son.qrscan.databinding.FragmentScanBinding
import com.son.qrscan.ui.pickphoto.PickPhotoActivity

class ScanFragment : BaseFragment<FragmentScanBinding>() {
    override fun inflateLayout(inflater: LayoutInflater): FragmentScanBinding {
        return FragmentScanBinding.inflate(inflater)
    }

    override fun setupListener() {
        binding.textView.setOnClickListener {

        }
    }
}