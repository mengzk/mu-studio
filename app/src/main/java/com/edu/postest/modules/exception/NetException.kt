package com.edu.postest.modules.exception

import java.io.IOException

/**
 * Author: Meng
 * Date: 2025/08/03
 * Modify: 2025/08/03
 * Desc:
 */
class NetException(val msg: String): IOException(msg) {
}