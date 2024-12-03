package com.son.qrscan.ui.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.son.qrscan.data.model.Language
import com.son.qrscan.data.repository.LanguageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(private val languageRepository: LanguageRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(LanguageUiState())
    val uiState = _uiState.asStateFlow()

    init {
       initListLanguage()
    }

    private fun initListLanguage() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                listLanguage = languageRepository.getListLanguage()
            )
        }
    }

    fun selectLanguage(language: Language) {
        _uiState.value = _uiState.value.copy(
            listLanguage = _uiState.value.listLanguage.map {
                it.copy(isSelected = it == language)
            },
            selectedLanguage = language
        )
    }

    fun enableNextButton() {
        if (!_uiState.value.isNextEnabled) {
            _uiState.value = _uiState.value.copy(
                isNextEnabled = true
            )
        }
    }
}

data class LanguageUiState(
    val listLanguage: List<Language> = listOf(),
    val selectedLanguage: Language? = null,
    val isNextEnabled: Boolean = false
)