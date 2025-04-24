package com.mon.mustudio.apis

import com.mon.mustudio.module.network.ResultData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

/**
 * Author: Meng
 * Date: 2025/04/24
 * Modify: 2025/04/24
 * Desc:
 *  Request.api.test("").enqueue(object : OKCallback<ResultData<Any>>() {
 *     override fun onResult(result: ResultData<Any>) {
 *          super.onResult(result)
 *     }
 *     override fun onFail(code: Int, e: Throwable) {
 *         super.onFail(code, e)
 *     }
 *  })
 */
interface Api {
//    @Headers(*["Token3: 131231","Token: 131231"])
//    @GET("xxx/test")
//    fun test(@Header("textHead") String test, @Query("id") id: Int): Call<ResultData<Any>>

//    @POST("xxx/{id}/invoke")
//    fun test2(@Path("id") id: String, @Body body: TestBody): Call<ResultData<Any>>

    @Multipart
    @POST("file/upload")
    fun upload3(@Part file: MultipartBody.Part, @Part("userId") tag: RequestBody?): Call<ResultData<Any>>
}