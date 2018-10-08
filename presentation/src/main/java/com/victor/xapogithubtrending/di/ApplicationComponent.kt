package com.victor.xapogithubtrending.di

import com.victor.xapogithubtrending.di.module.ActivityModule
import com.victor.xapogithubtrending.di.module.AppModule
import com.victor.xapogithubtrending.di.module.DataModule
import com.victor.xapogithubtrending.di.module.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by victor on 10/6/18
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    AppModule::class,
    DataModule::class,
    ViewModelModule::class,
    ActivityModule::class])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DaggerApplication>()

}