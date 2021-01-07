package com.wsmodule

import android.content.Context
import com.google.gson.Gson
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WebService(private val context: Context, private val baseUrl: String) {

    fun callGetMethod(
        path: String,
        header: Map<String, String>,
        objWsResponse: WSResponse,
        requestCode: Int
    ) {
        val call =
            ApiClient.client(baseUrl).create(ApiInterface::class.java)
                .getData(path, header)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (requestCode == 100000 && response.isSuccessful) {
                    objWsResponse.onSuccess(
                        response.body()!!.string(),
                        "",
                        response.code(),
                        requestCode
                    )
                } else {
                    sendData(response, requestCode, objWsResponse)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                objWsResponse.onFailure(
                    "",
                    t.message.toString(),
                    500,
                    requestCode
                )
            }
        })
    }
    fun callGetMethodWithoutHeader(
        path: String,
        objWsResponse: WSResponse,
        requestCode: Int
    ) {
        val call =
            ApiClient.client(baseUrl).create(ApiInterface::class.java)
                .getDataWithoutHeader(path)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (requestCode == 100000 && response.isSuccessful) {
                    objWsResponse.onSuccess(
                        response.body()!!.string(),
                        "",
                        response.code(),
                        requestCode
                    )
                } else {
                    sendData(response, requestCode, objWsResponse)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                objWsResponse.onFailure(
                    "",
                    t.message.toString(),
                    500,
                    requestCode
                )
            }
        })
    }

    fun callGetWithBodyMethod(
        path: String,
        header: Map<String, String>,
        body: Map<String, String>,
        objWsResponse: WSResponse,
        requestCode: Int
    ) {
        val call =
            ApiClient.client(baseUrl).create(ApiInterface::class.java)
                .getWithBodyData(path, header, body)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                sendData(response, requestCode, objWsResponse)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                objWsResponse.onFailure(
                    "",
                    t.message.toString(),
                    500,
                    requestCode
                )
            }
        })
    }

    fun callPostMethod(
        path: String,
        header: Map<String, String>,
        body: Any,
        objWsResponse: WSResponse,
        requestCode: Int
    ) {
        val call =
            ApiClient.client(baseUrl).create(ApiInterface::class.java)
                .postData(
                    path,
                    header, body
                )
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (requestCode == 100000000) {
                    if (response.isSuccessful) {
                        objWsResponse.onSuccess(
                            response.body()!!.string(),
                            response.message(),
                            response.code(),
                            requestCode
                        )
                    } else {
                        objWsResponse.onFailure(
                            response.body().toString(),
                            context.getString(R.string.internal_server_error),
                            500,
                            requestCode
                        )
                    }

                } else {
                    sendData(response, requestCode, objWsResponse)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                objWsResponse.onFailure(
                    "",
                    t.message.toString(),
                    500,
                    requestCode
                )
            }
        })
    }

    fun callPutMethod(
        path: String,
        header: Map<String, String>,
        body: Any,
        objWsResponse: WSResponse,
        requestCode: Int
    ) {
        val call = ApiClient.client(baseUrl).create(ApiInterface::class.java)
            .putData(path, header, body)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                sendData(response, requestCode, objWsResponse)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                objWsResponse.onFailure(
                    "",
                    t.message.toString(),
                    500,
                    requestCode
                )
            }
        })
    }

    fun callDeleteMethod(
        path: String,
        header: Map<String, String>,
        body: Map<String, String>,
        objWsResponse: WSResponse,
        requestCode: Int
    ) {
        val call =
            ApiClient.client(baseUrl).create(ApiInterface::class.java)
                .deleteData(path, header, body)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (requestCode == 100000 && response.isSuccessful) {
                    objWsResponse.onSuccess(
                        response.body()!!.string(),
                        "",
                        response.code(),
                        requestCode
                    )
                } else {
                    sendData(response, requestCode, objWsResponse)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                objWsResponse.onFailure(
                    "",
                    t.message.toString(),
                    500,
                    requestCode
                )
            }
        })
    }

    fun callFormDataMethod(
        path: String,
        header: Map<String, String>,
        body: JSONObject,
        objWsResponse: WSResponse,
        requestCode: Int
    ) {
        val call = ApiClient.client(baseUrl).create(ApiInterface::class.java)
            .formData(path, header, body)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                sendData(response, requestCode, objWsResponse)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                objWsResponse.onFailure(
                    "",
                    t.message.toString(),
                    500,
                    requestCode
                )
            }
        })
    }

    fun callMultipartDataImageMethod(
        path: String,
        header: Map<String, String>,
        body: HashMap<String, RequestBody>,
        responseBody: MultipartBody.Part,
        objWsResponse: WSResponse,
        requestCode: Int
    ) {
        val call =
            ApiClient.client(baseUrl).create(ApiInterface::class.java)
                .multipartDataForImage(path, header, body, responseBody)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                sendData(response, requestCode, objWsResponse)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                objWsResponse.onFailure(
                    "",
                    t.message.toString(),
                    500,
                    requestCode
                )
            }
        })
    }

    fun callTwoImageUpload(
        path: String,
        header: Map<String, String>,
        body: HashMap<String, RequestBody>,
        responseBody: MultipartBody.Part,
        responseBody1: MultipartBody.Part, videoBody: MultipartBody.Part,
        objWsResponse: WSResponse,
        requestCode: Int
    ) {
        val call =
            ApiClient.client(baseUrl).create(ApiInterface::class.java)
                .multipartDataForTwoImage(
                    path,
                    header,
                    body,
                    responseBody,
                    responseBody1,
                    videoBody
                )
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                sendData(response, requestCode, objWsResponse)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                objWsResponse.onFailure(
                    "",
                    t.message.toString(),
                    500,
                    requestCode
                )
            }
        })
    }

    fun callSingleImageUpload(
        path: String,
        header: Map<String, String>,
        body: JSONObject,
        responseBody: MultipartBody.Part,
        objWsResponse: WSResponse,
        requestCode: Int
    ) {
        val call =
            ApiClient.client(baseUrl).create(ApiInterface::class.java)
                .multipartDataForSingleImage(path, header, body, responseBody)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                sendData(response, requestCode, objWsResponse)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                objWsResponse.onFailure(
                    "",
                    t.message.toString(),
                    500,
                    requestCode
                )
            }
        })
    }

    fun callVideoImageUpload(
        path: String,
        header: Map<String, String>,
        body: RequestBody,
        videoBody: MultipartBody.Part,
        objWsResponse: WSResponse,
        requestCode: Int
    ) {
        val call =
            ApiClient.client(baseUrl).create(ApiInterface::class.java)
                .multipartDataForVideo(path, header, body, videoBody)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                sendData(response, requestCode, objWsResponse)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                objWsResponse.onFailure(
                    "",
                    t.message.toString(),
                    500,
                    requestCode
                )
            }
        })
    }

    fun callMultipleImageUploadMethod(
        path: String,
        header: Map<String, String>,
        body: HashMap<String, RequestBody>,
        imageBody: List<MultipartBody.Part>,
        objWsResponse: WSResponse,
        requestCode: Int
    ) {
        val call = ApiClient.client(baseUrl).create(ApiInterface::class.java)
            .multipleImageUpload(
                path, header, body, imageBody
            )
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                sendData(response, requestCode, objWsResponse)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                objWsResponse.onFailure(
                    "",
                    t.message.toString(),
                    500,
                    requestCode
                )
            }
        })
    }

    fun sendData(response: Response<ResponseBody>, requestCode: Int, objWsResponse: WSResponse) {
        if (response.isSuccessful) {
            if (response.code() == 200) {
                objWsResponse.onSuccess(
                    Gson().toJson(response),
                    response.message(),
                    response.code(),
                    requestCode
                )
            } else if (response.code() == 401) {
                objWsResponse.onFailure(
                    "",
                    context.getString(R.string.unauthorized),
                    response.code(),
                    requestCode
                )
            } else if (response.code() == 400) {
                objWsResponse.onFailure(
                    Gson().toJson(response),
                    response.message(),
                    response.code(),
                    requestCode
                )
            } else if (response.code() == 404) {
                objWsResponse.onFailure(
                    Gson().toJson(response),
                    response.message(),
                    response.code(),
                    requestCode
                )
            } else if (response.code() == 422) {
                objWsResponse.onFailure(
                    Gson().toJson(response),
                    response.message(),
                    response.code(),
                    requestCode
                )
            } else {
                objWsResponse.onFailure(
                    Gson().toJson(response),
                    response.message(),
                    response.code(),
                    requestCode
                )
            }
        } else if (response.code() == 401) {
            objWsResponse.onFailure(
                Gson().toJson(response),
                response.message(),
                response.code(),
                requestCode
            )
        } else {
            val res = response.errorBody()!!.string().trim()
            if (res.startsWith("{")) {
                val json = JSONObject(res)
                objWsResponse.onFailure(
                    Gson().toJson(response),
                    response.message(),
                    response.code(),
                    requestCode
                )
            } else {
                objWsResponse.onFailure(
                    Gson().toJson(response),
                    response.message(),
                    response.code(),
                    requestCode
                )
            }
        }
    }

    fun callMultipleImageUploadWithSingleImageMethod(
        path: String,
        header: Map<String, String>,
        body: Map<String, String>,
        responseBody: List<MultipartBody.Part>,
        singleBody: MultipartBody.Part,
        objWsResponse: WSResponse,
        requestCode: Int
    ) {
        val call =
            ApiClient.client(baseUrl).create(ApiInterface::class.java)
                .multipleImageUploadWithSingleImage(
                    path, header, body, responseBody, singleBody
                )
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                sendData(response, requestCode, objWsResponse)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
                objWsResponse.onFailure(
                    "",
                    t.message.toString(), 1,
                    requestCode
                )
            }
        })
    }

}
