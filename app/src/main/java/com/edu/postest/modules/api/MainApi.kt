package com.edu.postest.modules.api

import com.edu.postest.model.body.*
import com.edu.postest.model.entity.*
import com.edu.postest.modules.network.BodyData
import com.edu.postest.modules.network.ResultData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


/**
 * Author: Meng
 * Date: 2025/11/21
 * Modify: 2025/11/21
 * Desc:
 *  AppApis.mainApi.checkVersion("").enqueue(object : OKCallback<ResultData<Any>>() {
 *     override fun onResult(result: ResultData<Any>) {
 *          super.onResult(result)
 *     }
 *     override fun onFail(code: Int, e: Throwable) {
 *         super.onFail(code, e)
 *     }
 *  })
 */
interface MainApi {
//    @Headers(*["Token3: 131231","Token: 131231"])
//    @GET("customOrder/detail")
//    fun detail(@Header("textHead") String test, @Query("id") id: Int): Call<ResultData<Any>>

//    @POST("open/model/{id}/invoke")
//    fun chatModelV3Api(@Path("id") id: String, @Body body: TestBody): Call<ResultData<Any>>


    @Multipart
    @POST("file/upload")
    fun upload3(@Part file: MultipartBody.Part, @Part("userId") tag: RequestBody?): Call<ResultData<Any>>

    @Multipart
    @POST("file/uploads")
    fun uploads3(@Part files: List<MultipartBody.Part>, @Part("tag") tag: RequestBody?): Call<ResultData<Any>>

    @Multipart
    @POST("file/insert")
    fun insert3(@PartMap map: Map<String, RequestBody>): Call<ResultData<Any>>

    @Multipart
    @POST("file/uploadMap")
    fun uploadMap(@Part files: List<MultipartBody.Part>, @Part("scene") scene: RequestBody, @Part("userId") userId: RequestBody): Call<BodyData<Any>>

    @FormUrlEncoded
    @POST("account/info")
    fun info(@Field("code") code: Int, @Field("user") user: Int): Call<BodyData<Any>>

    /**
     * 登录
     * @param phone
     * @param verifyCode
     */
    @POST("/login/login")
    fun loginAccount(@Body body: LoginBody): Call<BodyData<UserEntity>>

    /**
     * 检测版本更新
     */
    @GET("version/check")
    fun checkVersion(@Query("version") version: String, @Query("os") os: Long): Call<BodyData<UpdateEntity>>


    /**
     *
     */
    fun test33(@Query("id") id: Int): Call<BodyData<Any>>
}