package com.son.qrscan.data.model

import android.net.Uri

data class Image(val id: Long, val image: Uri, val folder: String, val isSelected: Boolean = false)
