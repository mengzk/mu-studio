package com.mon.mustudio.module.exception

import java.io.IOException

/**
 * Author: Meng
 * Date: 2023/08/03
 * Modify: 2023/08/03
 * Desc:
 */
class NetException(val msg: String): IOException(msg) {
}