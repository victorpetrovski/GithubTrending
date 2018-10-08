package com.victor.xapogithubtrending.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.victor.xapogithubtrending.di.ViewModelKey
import com.victor.xapogithubtrending.di.extension.ViewModelProviderFactory
import com.victor.xapogithubtrending.features.repository_details.GithubRepositoryDetailsViewModel
import com.victor.xapogithubtrending.features.repository_list.GithubRepositoryListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by victor on 10/7/18
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GithubRepositoryListViewModel::class)
    internal abstract fun  bindsGithubRepositoryListViewModel(githubRepositoryListViewModel: GithubRepositoryListViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GithubRepositoryDetailsViewModel::class)
    internal abstract fun  bindsGithubRepositoryDetailsViewModel(githubRepositoryDetailsViewModel: GithubRepositoryDetailsViewModel) : ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelProviderFactory) : ViewModelProvider.Factory


}