package com.mon.mustudio.module.network

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 * Author: Meng
 * Date: 2023/06/28
 * Desc:
 */
abstract class OKCallback3<T> : Callback {

    override fun onFailure(call: Call, e: IOException) {
        onFail(1001, e)
    }

    override fun onResponse(call: Call, response: Response) {
        val data = response.body
        if (data != null) {
            try {

            } catch (e: Exception) {
                onFail(1002, e)
            }
        } else {
            onFail(1003, Exception("接口返回数据格式异常"))
        }
    }

    open fun onFail(code: Int, e: Throwable) {
        Log.w("NetCallback", "---> Fail: code=$code, msg=${e.message}")
    }

    open fun onResult(result: T) {}
}