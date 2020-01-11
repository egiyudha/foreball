package com.yudha.foreball

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.plugins.RxJavaPlugins

class App : Application() {

    companion object {
        private val LOG_TAG = this::class.java.simpleName
    }

    override fun onCreate() {
        super.onCreate()

        RxJavaPlugins.setErrorHandler {
            Log.e(LOG_TAG, "Unhandled rxjava error", it)
        }
    }
}
