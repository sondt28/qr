package com.son.qrscan.di

import android.content.Context
import com.son.qrscan.data.pref.SharePref
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SharePrefModule {

    @Provides
    fun provideSharePref(@ApplicationContext context: Context): SharePref {
        return SharePref(context)
    }
}