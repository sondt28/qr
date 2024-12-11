package com.son.qrscan.di

import android.content.Context
import com.son.qrscan.data.repository.ImageRepository
import com.son.qrscan.data.repository.LanguageRepository
import com.son.qrscan.data.repository.impl.ImageRepositoryImpl
import com.son.qrscan.data.repository.impl.LanguageRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun provideLanguageRepository() : LanguageRepository {
        return LanguageRepositoryImpl()
    }

    @Provides
    fun provideImageRepository(@ApplicationContext context: Context) : ImageRepository {
        return ImageRepositoryImpl(context)
    }
}