package com.mon.mustudio.module.network

import com.mon.mustudio.apis.Api
import com.mon.mustudio.config.Config
import com.mon.mustudio.module.network.retrofit.MyGsonConverterFactory
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import retrofit2.Retrofit

/**
 * Author: Meng
 * Date: 2025/04/24
 * Modify: 2025/04/24
 * Desc:
 *     Request.api.test(1).enqueue(object : Callback<Any>{
 *         override fun onFailure(call: retrofit2.Call<Any>, t: Throwable) {}
 *
 *         override fun onResponse(call: retrofit2.Call<Any>, response: retrofit2.Response<Any>) {
 *             if (response.isSuccessful) {
 *                 for ((name, value) in response.headers()) {
 *                     println("$name: $value")
 *                 }
 *                 println(response.body().toString())
 *             }
 *             println(call.request().url)
 *         }
 *     })
 */
object Request {

    val VideoType = "video/mp4".toMediaTypeOrNull()
    val ImageType = "image/*".toMediaTypeOrNull()
    val PNGType = "image/png".toMediaTypeOrNull()
    val TextType = "text/plain".toMediaTypeOrNull()
    val JsonType = "application/json; charset=utf-8".toMediaTypeOrNull()
    val FormType = "application/x-www-form-urlencoded".toMediaTypeOrNull()
    val StreamType = "application/octet-stream".toMediaTypeOrNull()
    val FormData = "multipart/form-data".toMediaTypeOrNull()

    lateinit var api: Api

    private val builder: Retrofit.Builder = Retrofit.Builder()
        .client(NetClient.client())
        .addConverterFactory(MyGsonConverterFactory.create())

    init {
        getClient()
    }

    fun setClientEnv(env: String) {
        Config.setEnv(env)
        getClient()
    }

    private fun getClient() {
        val mainUrl =  Config.getDomain(Config.getEnv())
        val retrofit = builder.baseUrl(mainUrl).build()
        api = retrofit.create(Api::class.java)
    }
}