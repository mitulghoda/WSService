package com.dev.wsservice

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.dev.wsservice.databinding.ActivityMainBinding
import com.wsmodule.WSResponse
import com.wsmodule.WebService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import java.io.File

class MainActivity : BaseActivity(), WSResponse {
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun callPostMethod() {
        val body = HashMap<String, String>()
        body["email"] = mainBinding.etEmail.text.toString()
        body["pass"] = mainBinding.etPass.text.toString()
        WebService(this, "http://www.example.com/api/").callPostMethod(
            "user/login",
            getHeaders(),
            body,
            this, 1001
        )
    }

    private fun callGetMothod() {
        WebService(this, "http://www.example.com/api/").callGetMethod(
            "user/login",
            getHeaders(),
            this, 1002
        )
        WebService(this, "http://www.example.com/api/").callGetMethodWithoutHeader(
            "get/master",
            this, 1002
        )
    }

    private fun callFormData() {
        val jsonObject = JSONObject()
        jsonObject.put("name", "xyz")
        WebService(this, baseUrl = "http://www.example.com/api/").callFormDataMethod(
            path = "user/login",
            header = getHeaders(),
            jsonObject,
            objWsResponse = this, requestCode = 1003
        )
    }

    private fun callMultipartRequest() {
        val jsonObject = JSONObject()
        jsonObject.put("name", "xyz")
        val image = File("filepath").toMultipartBody("image_key")
        WebService(this, baseUrl = "http://www.example.com/api/").callSingleImageUpload(
            "upload/image",
            header = getHeaders(),
            jsonObject,
            image,
            this, 1004
        )
    }

    override fun onSuccess(data: String, message: String, code: Int, requestCode: Int) {
/*Api Success*/
//We can manage multiple api call with request code
    }

    override fun onFailure(data: String, message: String, code: Int, requestCode: Int) {
/*Api failure*/
        //We can manage multiple api call with request code
    }

    private fun File.toMultipartBody(parameterName: String): MultipartBody.Part {
        return MultipartBody.Part.createFormData(
            parameterName,
            name,
            this.asRequestBody("image/*".toMediaType())
        )
    }
}