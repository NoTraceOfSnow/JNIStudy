package com.testndk.jnistudy.ui

import android.Manifest
import android.content.Intent
import android.view.View
import com.tbruyelle.rxpermissions2.RxPermissions
import com.testndk.jnistudy.R
import com.testndk.jnistudy.annotation.TestAnnotation
import com.testndk.jnistudy.ui.activity.*
import com.testndk.jnistudy.utils.isEquals

class FirstActivity : BaseActivity() {
    val permissions: RxPermissions by lazy { RxPermissions(this) }
    override fun initLayout(): Int {
        return R.layout.activity_first;
    }

    override fun initView() {
        super.initView()
        initPermission()
    }

    private fun initPermission() {
        if ((!permissions.isGranted(Manifest.permission.CAMERA) || !permissions.isGranted(Manifest.permission.READ_EXTERNAL_STORAGE)
                    || !permissions.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE))
        ) {
            mDisposable.add(permissions.requestEach(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ).subscribe {
                if (isEquals(it.name, Manifest.permission.CAMERA)) {

                }
            })
        }
    }

    private fun start(clazz: Class<*>) {
        val intent = Intent(this, clazz)
        this.startActivity(intent);
    }

    fun onClickAndFix(view: View) {
        start(MainActivity::class.java)
    }

    fun onClickBitmapOption(view: View) {
        start(BitmapOptionActivity::class.java)
    }

    fun onClickBsdiff(view: View) {

    }

    @TestAnnotation
    fun onClickFFmpeg(view: View) {
        start(TestAspectActivity::class.java)
    }

    fun onClickEdit(view: View) {
        start(EditWeightActivity::class.java)
    }

    fun onClickCamera(view: View) {
        if ((!permissions.isGranted(Manifest.permission.CAMERA) || !permissions.isGranted(Manifest.permission.READ_EXTERNAL_STORAGE)
                    || !permissions.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE))
        ) {
            mDisposable.add(permissions.request(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            ).subscribe { granted ->
                if (granted) {
                    start(CameraActivity::class.java)
                }
            })
        } else {
            start(CameraActivity::class.java)
        }
    }

    fun onClickLoadGif(view: View) {
        start(GifActivity::class.java)
    }

    fun onClickMergeApk(view: View) {
        start(BsdiffActivity::class.java)
    }

    fun onClickFmod(view: View) {
        start(FmodActivity::class.java)
    }
}