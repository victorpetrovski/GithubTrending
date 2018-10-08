package com.victor.xapogithubtrending

import android.app.Application
import com.victor.xapogithubtrending.di.DaggerApplication
import com.victor.xapogithubtrending.di.extension.applyAutoInjector

class GithubApplication : DaggerApplication(){

    override fun onCreate() {
        super.onCreate()

        applyAutoInjector()
    }
}