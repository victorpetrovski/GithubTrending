package com.victor.xapogithubtrending.di.module

import com.victor.xapogithubtrending.features.repository_details.GithubRepositoryDetailsActivity
import com.victor.xapogithubtrending.features.repository_list.GithubListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by victor on 10/7/18
 */
@Module
abstract class ActivityModule {


    @ContributesAndroidInjector
    internal abstract fun contributeListActivity(): GithubListActivity

    @ContributesAndroidInjector
    internal abstract fun contributeDetailsActivity() : GithubRepositoryDetailsActivity


}
