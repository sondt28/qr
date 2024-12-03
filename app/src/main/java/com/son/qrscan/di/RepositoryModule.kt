package com.son.qrscan.di

import com.son.qrscan.data.repository.LanguageRepository
import com.son.qrscan.data.repository.impl.LanguageRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideLanguageRepository() : LanguageRepository {
        return LanguageRepositoryImpl()
    }
}