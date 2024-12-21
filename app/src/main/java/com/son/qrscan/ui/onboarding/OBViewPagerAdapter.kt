package com.son.qrscan.ui.onboarding

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class OBViewPagerAdapter(fragment: OnBoardingActivity, private var listTitle: ArrayList<String>) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return listTitle.size
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            TAB_1 -> FirstIntroFragment().newInstance()
            TAB_2 -> SecondIntroFragment().newInstance()
            TAB_3 -> ThirdIntroFragment().newInstance()

            else -> FirstIntroFragment().newInstance()
        }
    }
    companion object{
        const val TAB_1 = 0
        const val TAB_2 = 1
        const val TAB_3 = 2

    }
}