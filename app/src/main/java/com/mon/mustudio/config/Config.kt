package com.mon.mustudio.config

/**
 * Author: Meng
 * Date: 2024/11/21
 * Modify: 2024/11/21
 * Desc:
 */
object Config {
    private var env: String = "test"
    const val secret: String = ""
    const val apiId: String = ""

    fun getEnv(): String {
        return env
    }

    fun setEnv(env: String) {
        this.env = env
    }

    fun getDomain(env: String): String {
        return when (env) {
            "test" -> "https://xxxxx.com"
            "dev" -> "http://192.168.0.0:8093"
            else -> "https://xxxxx.com"
        }
    }
}
