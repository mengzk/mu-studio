package com.edu.postest.libs.hardware.`interface`

/**
 * Author: Meng
 * Date: 2025/09/03
 * Modify: 2025/09/03
 * Desc: pos机
 */
interface Pos {
    fun link(): Boolean
    fun unlink(): Boolean
}