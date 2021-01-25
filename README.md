
Step->1

dependencies {
    implementation 'com.github.mitulghoda:WSService:release_version'
}

Step-> 2
   Need to implement interface name is WSResponse
   //After implement WSResponse interface will get two override method name is onSuccess and OnFailure,
   can manage multiple api call in single screen with requestCode in api call mehotd- Check in step-3
   
   class MainActivity : BaseActivity(), WSResponse
       
Step-> 3
Can use method what we need to call 

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
    
    For MultipartBody req para
     private fun File.toMultipartBody(parameterName: String): MultipartBody.Part {
        return MultipartBody.Part.createFormData(
            parameterName,
            name,
            this.asRequestBody("image/*".toMediaType())
        )
    }
    
    
    Step-> 4   Got api response in two methods
    
     override fun onSuccess(data: String, message: String, code: Int, requestCode: Int) {
/*Api Success*/
//We can manage multiple api call with request code
    }

    override fun onFailure(data: String, message: String, code: Int, requestCode: Int) {
/*Api failure*/
        //We can manage multiple api call with request code
    }
 
