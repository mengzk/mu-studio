package com.mon.mustudio.module.network.body

/**
 * Author: Meng
 * Date: 2023/08/03
 * Modify: 2023/08/03
 * Desc:
 */
interface OnProgressListener {
    fun progress(readSize: Long, totalSize: Long, done: Boolean)
}