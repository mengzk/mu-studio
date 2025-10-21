package com.edu.postest.util

import android.content.Context
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager

/**
 * Author: Meng
 * Date: 2025/10/16
 * Modify: 2025/10/16
 * Desc:
 * 在 Android 中监听通话接通与通话结束，可以通过注册 PhoneStateListener 并监听 TelephonyManager 的通话状态变化。
 * 主要监听 CALL_STATE_OFFHOOK（通话接通）和 CALL_STATE_IDLE（通话结束）。
 * 注意事项：
 * 需要在 AndroidManifest.xml 里声明权限：
 * <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
 * Android 6.0 及以上需动态申请权限。
 * Android 10 及以上，部分通话状态监听受限，仅系统应用可用。
 */
object TelephonyUtils {

    fun listenerCallState(context: Context) {
        // Implementation would go here
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        telephonyManager.listen(object : PhoneStateListener() {
            override fun onCallStateChanged(state: Int, phoneNumber: String?) {
                when (state) {
                    TelephonyManager.CALL_STATE_OFFHOOK -> {
                        // 通话接通
                    }
                    TelephonyManager.CALL_STATE_IDLE -> {
                        // 通话结束
                    }
                    TelephonyManager.CALL_STATE_RINGING -> {
                        // 来电响铃
                    }
                }
            }
        }, PhoneStateListener.LISTEN_CALL_STATE)
    }


}