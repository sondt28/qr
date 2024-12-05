package com.son.qrscan.ui.home.fragment

import android.view.LayoutInflater
import com.son.common.ui.base.BaseFragment
import com.son.qrscan.databinding.FragmentCreateBinding

class CreateFragment : BaseFragment<FragmentCreateBinding>() {
    override fun inflateLayout(inflater: LayoutInflater): FragmentCreateBinding {
        return FragmentCreateBinding.inflate(inflater)
    }
}