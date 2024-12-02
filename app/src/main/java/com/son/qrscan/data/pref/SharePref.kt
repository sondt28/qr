package com.son.qrscan.data.pref

import android.content.Context

class SharePref(context: Context) {
    private val sharePrefName = "${context.applicationContext.packageName}_pref"
    private val sharePref =
        context.applicationContext.getSharedPreferences(sharePrefName, Context.MODE_PRIVATE)
}