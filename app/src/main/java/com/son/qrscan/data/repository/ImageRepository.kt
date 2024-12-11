package com.son.qrscan.data.repository

import com.son.qrscan.data.model.ImageFolder

interface ImageRepository {
    suspend fun getImages(): List<ImageFolder>
}