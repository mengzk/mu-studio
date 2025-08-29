package com.edu.postest.modules.network

import com.edu.postest.modules.exception.CoroutineHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Author: Meng
 * Date: 2025/08/03
 * Modify: 2025/08/03
 * Desc:
 */
object NetIO {

    /**
     * 在协程作用域中切换至IO线程
     */
    suspend fun <T> on(block: suspend () -> T): T {
        return withContext(Dispatchers.IO + CoroutineHandler) {
            block.invoke()
        }
    }
}