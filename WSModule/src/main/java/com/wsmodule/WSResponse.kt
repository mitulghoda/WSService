package com.wsmodule


interface WSResponse {
    fun onSuccess(data: String, message: String, code: Int, requestCode: Int)
    fun onFailure(data: String, message: String, code: Int, requestCode: Int)
}
