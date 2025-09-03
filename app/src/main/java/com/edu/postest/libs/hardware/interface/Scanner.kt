package com.edu.postest.libs.hardware.`interface`

/**
 * Author: Meng
 * Date: 2025/09/03
 * Modify: 2025/09/03
 * Desc: 扫码枪
 */
interface Scanner {
    fun start(): Boolean
    fun stop(): Boolean
    fun setMode(mode: Int): Boolean
}