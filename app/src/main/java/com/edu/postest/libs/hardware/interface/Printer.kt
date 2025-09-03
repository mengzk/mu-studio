package com.edu.postest.libs.hardware.`interface`

/**
 * Author: Meng
 * Date: 2025/09/03
 * Modify: 2025/09/03
 * Desc: 打印机
 */
interface Printer {
    fun printText(text: String): Boolean
    fun printImage(path: String): Boolean
    fun printQRCode(data: String, size: Int): Boolean
    fun printBarcode(data: String, type: String): Boolean
    fun cutPaper(): Boolean
}