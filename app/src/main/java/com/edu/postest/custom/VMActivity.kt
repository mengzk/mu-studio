package com.edu.postest.custom

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.os.SystemClock
import android.view.MotionEvent
import android.window.OnBackInvokedCallback
import android.window.OnBackInvokedDispatcher
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.edu.postest.MainActivity

/**
 * Author: Meng
 * Date: 2025/11/21
 * Modify: 2025/11/21
 * Desc:
 */
open class VMActivity : AppCompatActivity() {
    private var lastTouchTime = 0L
    private var duration = 120000L
    private val handler = Handler(Looper.getMainLooper())
    private val returnHomeRunnable = Runnable {
        openHomePage()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityStack.add(this)

//        // 处理侧滑返回事件
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            onBackInvokedDispatcher.registerOnBackInvokedCallback(
//                OnBackInvokedDispatcher.PRIORITY_DEFAULT,
//                OnBackInvokedCallback {
//                    onBack()
//                }
//            )
//        }else {
//            onBackPressedDispatcher.addCallback(this) {
//                onBack()
//            }
//        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(returnHomeRunnable)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityStack.remove(this)
        handler.removeCallbacks(returnHomeRunnable)
    }

    override fun onNightModeChanged(mode: Int) {
        super.onNightModeChanged(mode)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_UP) {
            lastTouchTime = SystemClock.elapsedRealtime()
//            Log.i("Activity", "-----> touch " + lastTouchTime)
            handler.removeCallbacks(returnHomeRunnable)
            handler.postDelayed(returnHomeRunnable, duration)
        }
        return super.onTouchEvent(event)
    }

//    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//        return super.dispatchTouchEvent(ev)
//    }

    private fun openHomePage() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        ActivityStack.close()
    }

    open fun setDuration(num: Long) {
        duration = num
    }

    open fun onEvent(data: Any) {

    }
//    // 通过id跳转
//    open fun navigate(@IdRes resId: Int, args: Bundle? = null) {
//        navigate?.navigate(resId, args)
//    }
//    // 通过id返回
//    open fun backTop(@IdRes resId: Int) {
//        navigate?.popBackStack(resId, true)
//    }
//    // 返回
//    open fun back() {
//        val back = navigate?.popBackStack()
//        if (back == null || !back) {
//            finish()
//        }else {
//            navigate?.navigateUp()
//        }
//    }
}