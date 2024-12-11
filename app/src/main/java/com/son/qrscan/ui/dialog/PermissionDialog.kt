package com.son.qrscan.ui.dialog

import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.son.common.ui.base.BaseDialogFragment
import com.son.qrscan.databinding.DialogPermissionBinding

class PermissionDialog : BaseDialogFragment<DialogPermissionBinding>() {
    private var icon: Int? = null
    private var title: Int? = null
    private var desc: Int? = null

    var onGoToSettingClick: () -> Unit = {}

    override fun inflateLayout(inflater: LayoutInflater): DialogPermissionBinding {
        return DialogPermissionBinding.inflate(inflater)
    }

    override fun initView() {
        icon?.let { binding.ivIcon.setImageResource(it) }
        title?.let { binding.tvTitle.setText(it) }
        desc?.let { binding.tvDesc.setText(it) }

        binding.tvGoToSetting.setOnClickListener {
            onGoToSettingClick.invoke()
            dismiss()
        }

        binding.ivClose.setOnClickListener {
            dismiss()
        }
    }

    class Builder {
        private val dialog = PermissionDialog()

        fun setIcon(@DrawableRes icon: Int): Builder = apply {
            dialog.icon = icon
        }

        fun setTitle(@StringRes title: Int): Builder = apply {
            dialog.title = title
        }

        fun setDesc(@StringRes desc: Int): Builder = apply {
            dialog.desc = desc
        }

        fun build(): PermissionDialog {
            return dialog
        }
    }
}