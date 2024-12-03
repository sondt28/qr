package com.son.qrscan.ui.language

import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.son.common.ui.base.BaseActivity
import com.son.qrscan.databinding.ActivityLanguageBinding
import com.son.qrscan.ui.language.adapter.LanguageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.util.Locale

@AndroidEntryPoint
class LanguageActivity : BaseActivity<ActivityLanguageBinding>() {
    private val viewModel: LanguageViewModel by viewModels()
    private val adapter by lazy { LanguageAdapter() }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityLanguageBinding {
        return ActivityLanguageBinding.inflate(inflater)
    }

    override fun setupObserver() {
        viewModel.uiState
            .map { it.listLanguage }
            .filterNotNull()
            .distinctUntilChanged()
            .onEach {
                adapter.submitList(it)
            }
            .launchIn(lifecycleScope)

        viewModel.uiState
            .map { it.isNextEnabled }
            .filterNotNull()
            .distinctUntilChanged()
            .onEach {
                binding.ivCheck.isEnabled = it
            }.launchIn(lifecycleScope)
    }

    override fun setupUI() {
        Log.d("TAG", "setupUI: ${Locale.getDefault().language}")
        binding.rcLanguage.adapter = adapter
    }

    override fun setupListener() {
        adapter.onItemClickListener = {
            viewModel.selectLanguage(it)
            viewModel.enableNextButton()
        }

        binding.ivCheck.setOnClickListener {
            finish()
        }
    }
}