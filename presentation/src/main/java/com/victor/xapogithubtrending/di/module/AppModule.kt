package com.victor.xapogithubtrending.di.module

import android.content.Context
import com.victor.xapogithubtrending.di.DaggerApplication
import dagger.Binds
import dagger.Module

/**
 * Created by victor on 10/6/18
 */
@Module
abstract class AppModule {

    @Binds
    abstract fun bindContext(application: DaggerApplication) : Context

}