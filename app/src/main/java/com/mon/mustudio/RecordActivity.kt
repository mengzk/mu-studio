package com.mon.mustudio

import android.content.ContentValues
import android.content.Context
import android.hardware.Camera
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.MediaStore
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileInputStream

class RecordActivity : AppCompatActivity() {
    private lateinit var mediaRecorder: MediaRecorder
    private lateinit var camera: Camera
    private lateinit var wakeLock: PowerManager.WakeLock
    private lateinit var surfaceView: SurfaceView
    private lateinit var surfaceHolder: SurfaceHolder
    private var fileName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        surfaceView = SurfaceView(this)
        setContentView(surfaceView)

        // 初始化 SurfaceHolder
        surfaceHolder = surfaceView.holder

        // 初始化 WakeLock
        val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "RecordActivity:WakeLock")

        surfaceHolder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                startRecording()
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

            override fun surfaceDestroyed(holder: SurfaceHolder) {
//                stopRecording()
            }
        })
    }

    override fun onResume() {
        super.onResume()
//        surfaceHolder.addCallback(object : SurfaceHolder.Callback {
//            override fun surfaceCreated(holder: SurfaceHolder) {
//                startRecording()
//            }
//
//            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
//
//            override fun surfaceDestroyed(holder: SurfaceHolder) {
//                stopRecording()
//            }
//        })
    }

    private fun startRecording() {
        // 获取摄像头
        camera = Camera.open()
        camera.setDisplayOrientation(90)
        camera.unlock()

        // 初始化 MediaRecorder
        mediaRecorder = MediaRecorder().apply {
            fileName = "V${System.currentTimeMillis()}.mp4"
            setCamera(camera)
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setVideoSource(MediaRecorder.VideoSource.CAMERA)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setVideoEncoder(MediaRecorder.VideoEncoder.H264)
            setOutputFile(File(getExternalFilesDir(null), fileName).absolutePath)
            setPreviewDisplay(surfaceHolder.surface)
            setOrientationHint(90)
            prepare()
            start()
        }

        surfaceHolder = surfaceView.getHolder()
        surfaceHolder.setFixedSize(640, 480)
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)
        mediaRecorder.setPreviewDisplay(surfaceHolder.getSurface())

        // 获取 WakeLock
        wakeLock.acquire(10*60*1000L /*10 minutes*/)
    }

    private fun stopRecording() {
        if (::mediaRecorder.isInitialized) {
            mediaRecorder.stop()
            mediaRecorder.release()
        }

        camera.release()

        // 保存到相册
        val videoFile = File(getExternalFilesDir(null), fileName)
        saveVideoToGallery(videoFile)

        // 释放 WakeLock
        if (wakeLock.isHeld) {
            wakeLock.release()
        }
    }

    private fun saveVideoToGallery(file: File) {
        val contentResolver = contentResolver
        val videoCollection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Video.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } else {
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        }

        val contentValues = ContentValues().apply {
            put(MediaStore.Video.Media.DISPLAY_NAME, file.name)
            put(MediaStore.Video.Media.MIME_TYPE, "video/mp4")
            put(MediaStore.Video.Media.RELATIVE_PATH, "Movies/MyApp") // 存放在相册的 Movies/MyApp 目录
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                put(MediaStore.Video.Media.DATA, file.absolutePath)
            }
        }

        val uri = contentResolver.insert(videoCollection, contentValues)
        uri?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                contentResolver.openOutputStream(it).use { outputStream ->
                    FileInputStream(file).use { inputStream ->
                        inputStream.copyTo(outputStream!!)
                    }
                }
            }
            file.delete() // 删除原始文件
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mediaRecorder.isInitialized) {
            stopRecording()
        }
    }
}