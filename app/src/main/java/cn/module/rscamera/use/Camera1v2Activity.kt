package cn.module.rscamera.use

import android.Manifest
import android.hardware.Camera
import android.view.WindowManager
import cn.readsense.module.base.BaseCoreActivity
import cn.readsense.module.camera1.v2.CameraParams.PreviewCallback
import cn.readsense.module.camera1.v2.CameraView
import cn.readsense.module.util.DLog
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class Camera1v2Activity : BaseCoreActivity() {

    lateinit var cameraView: CameraView

    override fun getLayoutId(): Int {
        requestPermissions(Manifest.permission.CAMERA)
        return R.layout.activity_camera1v2
    }

    override fun initView() {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        launch {


        }
        cameraView = findViewById(R.id.cameraview)
        cameraView.cameraRenderer.params.facing = 0
        cameraView.cameraRenderer.params.w = 640
        cameraView.cameraRenderer.params.h = 480
        cameraView.cameraRenderer.params.previewMode = 0
        cameraView.cameraRenderer.params.flipXY = false
        cameraView.cameraRenderer.params.previewCallback =
            object : PreviewCallback {
                override fun onPreviewFrame(data: ByteArray, camera: Camera) {
                    //接受buffer数据，但是注意外部不要修改buffer

                    launch(Dispatchers.IO) {
                        DLog.d("launch ${Thread.currentThread().name} working ")
                        val cost = measureTimeMillis {
                            val event1 = async { delay(2000) }
                            val event2 = async { delay(2000) }
                            event1.await()
                            event2.await()
                        }

                        DLog.d("launch ${Thread.currentThread().name} end cost :$cost")
                    }

                }
            }
    }

}