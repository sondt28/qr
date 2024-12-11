package com.son.qrscan.data.repository.impl

import com.son.qrscan.data.model.Language
import com.son.qrscan.data.repository.LanguageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LanguageRepositoryImpl : LanguageRepository {
    override suspend fun getListLanguage(): List<Language> {
        return withContext(Dispatchers.IO) {
            listOf(
                Language(
                    com.son.common.R.drawable.ic_language_item_france,
                    com.son.common.R.string.language_france,
                    "fr"
                ),
                Language(
                    com.son.common.R.drawable.ic_language_item_eng,
                    com.son.common.R.string.language_english,
                    "en"
                ),
                Language(
                    com.son.common.R.drawable.ic_language_item_hindi,
                    com.son.common.R.string.language_hindi,
                    "hi"
                ),
                Language(
                    com.son.common.R.drawable.ic_language_item_saudi,
                    com.son.common.R.string.language_saudi,
                    "ar"
                ),
                Language(
                    com.son.common.R.drawable.ic_language_item_deutsch,
                    com.son.common.R.string.language_deutsch,
                    "de"
                ),
                Language(
                    com.son.common.R.drawable.ic_language_item_spain,
                    com.son.common.R.string.language_spain,
                    "es"
                ),

                Language(
                    com.son.common.R.drawable.ic_language_item_china,
                    com.son.common.R.string.language_china,
                    "zh"
                )
            )
        }
    }
}