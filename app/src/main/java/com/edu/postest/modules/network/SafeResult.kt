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
