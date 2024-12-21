package com.son.qrscan.ui.onboarding



import android.os.Bundle
import android.view.LayoutInflater
import com.son.common.ui.base.BaseFragment

import com.son.qrscan.databinding.FragmentSecondIntroBinding

class SecondIntroFragment: BaseFragment<FragmentSecondIntroBinding>() {
    override fun inflateLayout(inflater: LayoutInflater): FragmentSecondIntroBinding{
        return FragmentSecondIntroBinding.inflate(inflater)
    }
    fun newInstance(): SecondIntroFragment {
        val args = Bundle()
        val fragment = SecondIntroFragment()
        fragment.arguments = args
        return fragment
    }
}