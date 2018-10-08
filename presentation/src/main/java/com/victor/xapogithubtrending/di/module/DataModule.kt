package com.victor.xapogithubtrending.di.module

import com.victor.data.remote.GithubRemoteRepoImpl
import com.victor.data.remote.api.GithubRemoteApi
import com.victor.data.remote.api.GithubService
import com.victor.data.repository.GithubRepositoryImpl
import com.victor.data.repository.gateway.GithubRemoteRepo
import com.victor.domain.executor.Schedulers
import com.victor.domain.gateway.GithubRepositoryGateway
import com.victor.xapogithubtrending.BuildConfig
import com.victor.xapogithubtrending.BuildConfig.BASE_URL
import com.victor.xapogithubtrending.util.AppSchedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by victor on 10/7/18
 */
@Module
class DataModule {

    @Provides
    fun providesGithubRepository(githubRemoteRepo: GithubRepositoryImpl) : GithubRepositoryGateway = githubRemoteRepo

    @Provides
    fun providesGithubRemoteRepository(githubRemoteRepoImpl: GithubRemoteRepoImpl) : GithubRemoteRepo = githubRemoteRepoImpl


    @Provides
    @Singleton
    fun provideGithubService() : GithubService{
        return GithubRemoteApi(BASE_URL, BuildConfig.DEBUG)
    }

    @Provides
    fun provideSchedulers(): Schedulers {
        return AppSchedulers()
    }
}