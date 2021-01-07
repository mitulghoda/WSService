package com.wsmodule

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

class ResponseStatus {
    @SerializedName("code")
    var code: Int = 200

    @SerializedName("verification_status")
    var verification_status: String = ""

    @SerializedName("success")
    var success: Boolean = false

    @SerializedName("msg")
    var message: String = ""

    @SerializedName("data")
    var data: JsonElement? = null
}
