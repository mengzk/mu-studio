package com.edu.postest.modules.network

/**
 * Author: Meng
 * Date: 2025/09/08
 * Modify: 2025/09/08
 * Desc:
 */
sealed class SafeResult<out T> {
    data class Success<out T>(val data: T) : SafeResult<T>()

    data class Error(val msg: String?) : SafeResult<String?>()
}

// 协程封装
suspend fun <T> safeApiCall(block: suspend () -> BodyData<T>): SafeResult<T> {
    return try {
        val res = block()
        if (res.code == 0) {
            SafeResult.Success(res.data)
        } else {
            SafeResult.Error(res.message)
        }
    } catch (e: Exception) {
        SafeResult.Error(e.message ?: "请求处理报错")
    } as SafeResult<T>
}