package com.son.qrscan.ui.onboarding
import android.os.Bundle
import android.view.LayoutInflater
import com.son.common.ui.base.BaseFragment

import com.son.qrscan.databinding.FragmentFirstIntroBinding

class FirstIntroFragment: BaseFragment<FragmentFirstIntroBinding>() {
    override fun inflateLayout(inflater: LayoutInflater): FragmentFirstIntroBinding{
        return FragmentFirstIntroBinding.inflate(inflater)
    }
    fun newInstance(): FirstIntroFragment {
        val args = Bundle()
        val fragment = FirstIntroFragment()
        fragment.arguments = args
        return fragment
    }
}