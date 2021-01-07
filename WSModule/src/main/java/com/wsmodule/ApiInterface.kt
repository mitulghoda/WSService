package com.wsmodule

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

    @GET
    fun getData(@Url path: String, @HeaderMap headers: Map<String, String>): Call<ResponseBody>
    @GET
    fun getDataWithoutHeader(@Url path: String): Call<ResponseBody>

    @GET
    fun getWithBodyData(
        @Url path: String,
        @HeaderMap headers: Map<String, String>,
        @QueryMap body: Map<String, String>
    ): Call<ResponseBody>

    @POST
    fun postData(
        @Url path: String,
        @HeaderMap headers: Map<String, String>,
        @Body body: Any
    ): Call<ResponseBody>

    @POST
    fun formData(
        @Url path: String,
        @HeaderMap headers: Map<String, String>,
        @Body body: JSONObject
    ): Call<ResponseBody>

    @PUT
    fun putData(
        @Url path: String,
        @HeaderMap headers: Map<String, String>,
        @Body data: Any
    ): Call<ResponseBody>

    @DELETE
    fun deleteData(
        @Url path: String,
        @HeaderMap headers: Map<String, String>,
        @QueryName data: Map<String, String>
    ): Call<ResponseBody>

    @Multipart
    @POST
    fun multipartDataForImage(
        @Url path: String, @HeaderMap headers: Map<String, String>,
        @PartMap body: HashMap<String, RequestBody>,
        @Part name: MultipartBody.Part
    ): Call<ResponseBody>

    @Multipart
    @POST
    fun multipartDataForTwoImage(
        @Url path: String, @HeaderMap headers: Map<String, String>,
        @PartMap body: HashMap<String, RequestBody>,
        @Part name1: MultipartBody.Part,
        @Part name: MultipartBody.Part, @Part videoName: MultipartBody.Part
    ): Call<ResponseBody>

    @Multipart
    @POST
    fun multipartDataForSingleImage(
        @Url path: String, @HeaderMap headers: Map<String, String>,
        @Body body: JSONObject,
        @Part name: MultipartBody.Part
    ): Call<ResponseBody>

    @Multipart
    @POST
    fun multipartDataForVideo(
        @Url path: String, @HeaderMap headers: Map<String, String>,
        @Part("data") body: RequestBody,
        @Part videoName: MultipartBody.Part
    ): Call<ResponseBody>

    @Multipart
    @POST
    fun multipleImageUpload(
        @Url path: String, @HeaderMap headers: Map<String, String>,
        @PartMap body: HashMap<String, RequestBody>,
        @Part name: List<MultipartBody.Part>
    ): Call<ResponseBody>

    @Multipart
    @POST
    fun multipleImageUploadWithSingleImage(
        @Url path: String, @HeaderMap headers: Map<String, String>,
        @QueryMap body: Map<String, String>,
        @Part name: List<MultipartBody.Part>,
        @Part name1: MultipartBody.Part
    ): Call<ResponseBody>


}
