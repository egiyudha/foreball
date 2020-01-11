package com.yudha.foreball.di

import android.app.Application

class InjectorFactory {

    companion object {
        fun newAppInjector(app: Application): AppComponent {
            return DaggerAppComponent.builder()
                .appModule(AppModule(app))
                .build()
        }
    }
}