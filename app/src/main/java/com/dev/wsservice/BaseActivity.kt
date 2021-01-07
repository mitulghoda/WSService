package com.dev.wsservice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun getHeaders(): HashMap<String, String> {
        val meMap = HashMap<String, String>()
        meMap["X-AppVersion"] = "1.0"
        meMap["X_PLATEFORM"] = "ANDROID"
        meMap["TOKEN"] = "Bearer "
        return meMap
    }

}