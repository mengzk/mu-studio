package com.edu.postest.libs.hardware.`interface`

/**
 * Author: Meng
 * Date: 2025/09/03
 * Modify: 2025/09/03
 * Desc: 收银钱箱接口
 */
interface Cashbox {
    fun isOpen(): Boolean
    fun open(): Boolean
    fun close(): Boolean
}