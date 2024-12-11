package com.son.qrscan.ui.pickphoto

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.son.common.R
import com.son.common.ui.base.BaseActivity
import com.son.qrscan.data.model.ImageFolder
import com.son.qrscan.databinding.ActivityPickPhotoBinding
import com.son.qrscan.ui.dialog.PermissionDialog
import com.son.qrscan.ui.pickphoto.adapter.FolderAdapter
import com.son.qrscan.ui.pickphoto.adapter.ImageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PickPhotoActivity : BaseActivity<ActivityPickPhotoBinding>() {
    private val viewModel: PickPhotoViewModel by viewModels()
    private val imageAdapter by lazy { ImageAdapter() }
    private val folderAdapter by lazy { FolderAdapter() }

    private val requiredPermissions: Array<String>
        get() = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                arrayOf(READ_MEDIA_IMAGES)
            }

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE -> {
                arrayOf(READ_MEDIA_VISUAL_USER_SELECTED)
            }

            else -> {
                arrayOf(READ_EXTERNAL_STORAGE)
            }
        }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { isGranted ->
            if (isGranted.values.all { it }) {
                viewModel.loadImages()
                binding.vGroup.isGone = true
            } else {
                showPermissionDialog()
            }
        }

    private val goToSettingLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (checkPhotoPermissionIsGranted()) {
                viewModel.loadImages()
                binding.vGroup.isGone = true
            }
        }

    override fun attachBaseContext(newBase: Context?) {
        Log.d("SonLN", "attachBaseContext: ")
        super.attachBaseContext(newBase)
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityPickPhotoBinding {
        return ActivityPickPhotoBinding.inflate(inflater)
    }

    override fun setupObserver() {
        viewModel.uiState
            .map { it.isOpenFolder }
            .filterNotNull()
            .distinctUntilChanged()
            .onEach {
                binding.rvFolder.isVisible = it
            }
            .launchIn(lifecycleScope)

        viewModel.uiState
            .map { it.imageFolders }
            .filterNotNull()
            .distinctUntilChanged()
            .onEach { imageFolders ->
                folderAdapter.submitList(imageFolders)
            }
            .launchIn(lifecycleScope)

        viewModel.uiState
            .map { it.currentFolder }
            .distinctUntilChanged()
            .onEach { folder -> updateUI(folder) }
            .launchIn(lifecycleScope)

        viewModel.uiState
            .map { it.currentPath }
            .distinctUntilChanged()
            .onEach {
                binding.tvUpload.alpha = if (it != null) 1f else 0.5f
            }
            .launchIn(lifecycleScope)
    }

    private fun updateUI(folder: ImageFolder?) {
        if (folder != null) {
            binding.ivNoData.isVisible = false
            binding.tvCategory.text = folder.name
            imageAdapter.submitList(folder.images)
        } else {
            binding.ivNoData.isVisible = true
        }
    }

    override fun setupListener() {
        imageAdapter.onImageClick = {
            viewModel.selectImage(it)
        }

        folderAdapter.onFolderClick = {
            viewModel.selectFolder(it)
        }

        binding.tvCategory.setOnClickListener {
            viewModel.toggleFolderVisibility()
        }

        binding.tvGoSetting.setOnClickListener {
            goToSetting()
        }
    }

    override fun setupUI() {
        with(binding) {
            rvImage.adapter = imageAdapter

            rvFolder.adapter = folderAdapter
            rvFolder.addItemDecoration(
                DividerItemDecoration(
                    this@PickPhotoActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        requestPermissionIfNeed()
    }

    private fun goToSetting() {
        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = android.net.Uri.fromParts("package", packageName, null)
            goToSettingLauncher.launch(this)
        }
    }

    private fun requestPermissionIfNeed() {
        if (checkPhotoPermissionIsGranted()) {
            viewModel.loadImages()
            binding.vGroup.isGone = true
        } else {
            requestPermissionLauncher.launch(requiredPermissions)
        }
    }

    private fun checkPhotoPermissionIsGranted(): Boolean {
        return requiredPermissions.all {
            ContextCompat.checkSelfPermission(this, it) == PERMISSION_GRANTED
        }
    }

    private fun showPermissionDialog() {
        PermissionDialog.Builder()
            .setIcon(R.drawable.ic_dialog_photo)
            .setTitle(R.string.gallery_access_photo_title)
            .setDesc(R.string.dialog_photo_permission_desc)
            .build().apply {
                show(supportFragmentManager, null)
                onGoToSettingClick = { goToSetting() }
            }
    }
}
