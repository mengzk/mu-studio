package com.edu.postest.modules.network.body

/**
 * Author: Meng
 * Date: 2025/08/03
 * Modify: 2025/08/03
 * Desc:
 */
interface OnProgressListener {
    fun progress(readSize: Long, totalSize: Long, done: Boolean)
}