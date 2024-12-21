package com.son.qrscan.ui.onboarding


import android.os.Bundle
import android.view.LayoutInflater
import com.son.common.ui.base.BaseFragment
import com.son.qrscan.databinding.FragmentThirdIntroBinding

class ThirdIntroFragment: BaseFragment<FragmentThirdIntroBinding>() {
    override fun inflateLayout(inflater: LayoutInflater): FragmentThirdIntroBinding{
        return FragmentThirdIntroBinding.inflate(inflater)
    }
    fun newInstance(): ThirdIntroFragment {
        val args = Bundle()
        val fragment = ThirdIntroFragment()
        fragment.arguments = args
        return fragment
    }
}