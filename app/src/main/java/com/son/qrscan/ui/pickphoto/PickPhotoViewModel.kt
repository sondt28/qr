package com.son.qrscan.ui.pickphoto

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.son.qrscan.data.model.Image
import com.son.qrscan.data.model.ImageFolder
import com.son.qrscan.data.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PickPhotoViewModel @Inject constructor(private val imageRepository: ImageRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(PickPhotoUiState())
    val uiState: StateFlow<PickPhotoUiState> = _uiState

     fun loadImages() {
        viewModelScope.launch {
            val images = imageRepository.getImages()
            _uiState.value = _uiState.value.copy(imageFolders = images, currentFolder = images.firstOrNull())
        }
    }

    fun selectImage(image: Image) {
        val currentFolder = _uiState.value.currentFolder ?: return
        var currentPath = _uiState.value.currentPath
        val updatedImages = currentFolder.images.map {
            if (it.id == image.id) {
                it.copy(isSelected = !it.isSelected)
            } else {
                it.copy(isSelected = false)
            }
        }

        val updatedFolder = currentFolder.copy(images = updatedImages)
        currentPath = if (currentPath == image.image) null else image.image

        _uiState.value = _uiState.value.copy(currentFolder = updatedFolder, currentPath = currentPath)
    }

    fun selectFolder(folder: ImageFolder) {
        _uiState.value = _uiState.value.copy(currentFolder = folder, isOpenFolder = false, currentPath = null)
    }

    fun toggleFolderVisibility() {
        _uiState.value = _uiState.value.copy(isOpenFolder = !_uiState.value.isOpenFolder)
    }
}

data class PickPhotoUiState(
    val imageFolders: List<ImageFolder>? = null,
    val currentPath: Uri? = null,
    val currentFolder : ImageFolder? = null,
    val isOpenFolder: Boolean = false
)

