package com.son.common.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding> : AppCompatActivity() {
    private lateinit var binding: VB
    protected abstract fun inflateViewBinding(inflater: LayoutInflater): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding(layoutInflater)

        setupObserver()
        setupListener()
        setupUI()
    }

    open fun setupUI() {}
    open fun setupListener() {}
    open fun setupObserver() {}
}