package com.son.qrscan.data.repository.impl

import android.content.ContentUris
import android.content.Context
import android.provider.MediaStore
import com.son.qrscan.data.model.Image
import com.son.qrscan.data.model.ImageFolder
import com.son.qrscan.data.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(private val context: Context) : ImageRepository {
    override suspend fun getImages(): List<ImageFolder> {
        return withContext(Dispatchers.IO) {
            val images = fetchImageFromMediaStore()
            organizeImagesIntoFolders(images)
        }
    }

    private fun fetchImageFromMediaStore(): List<Image> {
        val images = mutableListOf<Image>()
        val collection = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME
        )
        val sorterOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"

        val query = context.contentResolver.query(collection, projection, null, null, sorterOrder)

        query?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val bucketNameColumn =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val bucketName = cursor.getString(bucketNameColumn)

                val contentUri =
                    ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)

                images += Image(id, contentUri, bucketName)
            }
        }

        return images
    }

    private fun organizeImagesIntoFolders(listImage: List<Image>): List<ImageFolder> {
        val allImagesFolder = ImageFolder(context.getString(com.son.common.R.string.gallery_all), listImage)
        val groupedFolder =
            listImage.groupBy { it.folder }.map { (folder, images) -> ImageFolder(folder, images) }
        return listOf(allImagesFolder) + groupedFolder
    }
}