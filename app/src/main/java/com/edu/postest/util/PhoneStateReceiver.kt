package com.edu.postest.util

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log


/**
 * Author: Meng
 * Date: 2025/10/21
 * Modify: 2025/10/21
 * Desc: 监听电话来电
 */
class PhoneStateReceiver(): BroadcastReceiver() {

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(p0: Context, intent: Intent) {
        val state: String? = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
        if (TelephonyManager.EXTRA_STATE_RINGING == state) {
            // 来电响铃
            Log.d("TelephonyManager" , "---> onCallStateChanged: 来电响铃" )
        } else if (TelephonyManager.EXTRA_STATE_OFFHOOK == state) {
            // 通话中
            Log.d("TelephonyManager" , "---> onCallStateChanged: 通话中" )
        } else if (TelephonyManager.EXTRA_STATE_IDLE == state) {
            // 挂断
            Log.d("TelephonyManager" , "---> onCallStateChanged: 挂断" )
        }
    }
}