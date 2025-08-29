package com.edu.postest.modules.exception

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.cancel
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

/**
 * Author: Meng
 * Date: 2025/08/03
 * Modify: 2025/08/03
 * Desc:
 */
object CoroutineHandler : AbstractCoroutineContextElement(CoroutineExceptionHandler),
    CoroutineExceptionHandler {

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        exception.printStackTrace()
        ExceptionHelper.parseException(exception, context)
        context.cancel()
    }
}