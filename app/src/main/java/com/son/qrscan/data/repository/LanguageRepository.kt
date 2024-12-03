package com.son.qrscan.data.repository

import com.son.qrscan.data.model.Language

interface LanguageRepository {
    suspend fun getListLanguage() : List<Language>
}