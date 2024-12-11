package com.son.common.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

abstract class BaseDialogFragment<V: ViewBinding> : DialogFragment() {
    lateinit var binding: V
    protected abstract fun inflateLayout(inflater: LayoutInflater) : V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflateLayout(inflater)
        return binding.root
    }

    private fun setSizeDialog() {
        val params = dialog?.window?.attributes
        params?.width = (resources.displayMetrics.widthPixels * 0.88).toInt()
        params?.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.attributes = params
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(null)
        setSizeDialog()
        initView()
    }

    abstract fun initView()
}