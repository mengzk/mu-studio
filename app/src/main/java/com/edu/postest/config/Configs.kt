package com.edu.postest.config

/**
 * Author: Meng
 * Date: 2025/11/21
 * Modify: 2025/11/21
 * Desc: 配置类 -单例
 */
object Configs {
    private var env: String = "test"
    const val secret: String = ""
    const val apiId: String = ""
    const val h5Url: String = "https://test.com/"

    fun getEnv(): String {
        return env
    }

    fun setEnv(env: String) {
        this.env = env
    }

    fun getDomain(env: String): String {
        return when (env) {
            "test" -> "https://test.com"
            "dev" -> "http://192.168.31.1:8093"
            else -> "https://prod.com"
        }
    }
}
