package com.edu.postest.config

import android.content.Context
import android.os.Build
import android.provider.Settings

/**
 * Author: Meng
 * Date: 2025/08/03
 * Modify: 2025/08/03
 * Desc: 常量类 -单例
 */
object Constants {
    var USER_ID = "" // 用户id
    var USER_NAME = "" // 用户名
    var USER_PHONE = "" // 用户手机号
    var USER_TOKEN = "" // 用户token
    var USER_JSON = "" // 用户信息json
//    var USER_AVATAR = "" // 用户头像

    var DEVICE_SN = Build.DEVICE // 设备sn
    var DEVICE_SDK = Build.VERSION.SDK_INT // 设备sdk号
    var VERSION = 10001L // 版本号
    var VERSION_NAME = "1.0.0" // 版本号
    var USER_AGENT = "sdk:0,device:,version:1,versionName:1"
    var DEVICE_NAME = "${Build.BRAND}_${Build.MODEL}" // 设备名
    var DEVICE_FINGERPRINT = Build.FINGERPRINT // 设备指纹
    var DEVICE_BOARD = Build.BOARD // 设备主板

    init {
        initUserAgent()
//        getDeviceUniqueId()
    }

    fun initUserAgent() {
        USER_AGENT = getUserAgent()
    }

    fun getUserAgent(): String {
        return "sdk:$DEVICE_SDK,version:$VERSION,versionName:$VERSION_NAME,fingerprint:$DEVICE_FINGERPRINT,host:${Build.HOST},deviceName:$DEVICE_NAME"
    }

    fun getDeviceName(): String {
        // 如何获取 android 设备名
        return DEVICE_NAME
    }
    fun getDeviceUniqueId(context: Context) {
        DEVICE_SN = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

}
