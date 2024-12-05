package com.son.qrscan.ui.home

import android.view.LayoutInflater
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.son.common.ui.base.BaseActivity
import com.son.qrscan.R
import com.son.qrscan.databinding.ActivityHomeBinding
import com.son.qrscan.ui.home.fragment.CreateFragment
import com.son.qrscan.ui.home.fragment.HistoryFragment
import com.son.qrscan.ui.home.fragment.ScanFragment
import com.son.qrscan.ui.home.fragment.SettingFragment
import com.son.qrscan.utils.Constants.FRAGMENT_CREATE
import com.son.qrscan.utils.Constants.FRAGMENT_HISTORY
import com.son.qrscan.utils.Constants.FRAGMENT_SCAN
import com.son.qrscan.utils.Constants.FRAGMENT_SETTING
import java.util.concurrent.ExecutorService

class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var barcodeScanner: BarcodeScanner

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(inflater)
    }

    override fun setupUI() {
        initFragment()
        startCamera()
    }

    private fun initFragment() {
        if (supportFragmentManager.fragments.isEmpty()) loadFragment(FRAGMENT_SCAN)
    }

    override fun setupListener() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.scan -> loadFragment(FRAGMENT_SCAN)
                R.id.create -> loadFragment(FRAGMENT_CREATE)
                R.id.history -> loadFragment(FRAGMENT_HISTORY)
                R.id.settings -> loadFragment(FRAGMENT_SETTING)
            }
            true
        }
    }

    private fun loadFragment(tag: String) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        val currentFragment = supportFragmentManager.primaryNavigationFragment

        currentFragment?.let {
            fragmentTransaction.hide(it)
        }
        var newFragment = supportFragmentManager.findFragmentByTag(tag)
        if (newFragment == null) {
            newFragment = when (tag) {
                FRAGMENT_SCAN -> ScanFragment()
                FRAGMENT_CREATE -> CreateFragment()
                FRAGMENT_HISTORY -> HistoryFragment()
                FRAGMENT_SETTING -> SettingFragment()
                else -> return
            }
            fragmentTransaction.add(R.id.navHostFragment, newFragment, tag)
        } else {
            fragmentTransaction.show(newFragment)
        }
        fragmentTransaction.setPrimaryNavigationFragment(newFragment)
        fragmentTransaction.commit()
    }

    private fun startCamera() {
//        val cameraController = LifecycleCameraController(this)
//        val preview = binding.viewFinder
//
//        val options = BarcodeScannerOptions.Builder()
//            .setBarcodeFormats(
//                Barcode.FORMAT_QR_CODE,
//                Barcode.FORMAT_AZTEC
//            )
//            .build()
//
//        barcodeScanner = BarcodeScanning.getClient(options)
//
//        cameraController.setImageAnalysisAnalyzer(
//            ContextCompat.getMainExecutor(this),
//            MlKitAnalyzer(
//                listOf(barcodeScanner),
//                COORDINATE_SYSTEM_VIEW_REFERENCED,
//                ContextCompat.getMainExecutor(this)
//            ) { result ->
//
//                val barcodeResult = result.getValue(barcodeScanner)
//                if (barcodeResult == null || barcodeResult.size == 0 || barcodeResult.first() == null) {
//                    preview.overlay.clear()
//                    preview.setOnTouchListener { _, _ -> false }
//                    return@MlKitAnalyzer
//                }
//            }
//        )
//
//        cameraController.bindToLifecycle(this)
//        preview.controller = cameraController
    }

    private fun takePhoto() {

    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
        barcodeScanner.close()
    }

    companion object {
        private const val TAG = "SonLN"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
    }
}