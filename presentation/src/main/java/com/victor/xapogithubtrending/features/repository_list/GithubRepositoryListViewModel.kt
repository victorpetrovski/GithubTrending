package com.victor.xapogithubtrending.features.repository_list

import android.arch.lifecycle.MutableLiveData
import android.support.annotation.VisibleForTesting
import com.victor.domain.executor.Schedulers
import com.victor.domain.usecase.TrendingRepositoriesUseCase
import com.victor.xapogithubtrending.base.BaseViewModel
import com.victor.xapogithubtrending.features.state.ViewResource
import com.victor.xapogithubtrending.features.state.ViewState
import com.victor.xapogithubtrending.model.EntityViewMapper
import com.victor.xapogithubtrending.model.RepositoryView
import javax.inject.Inject

/**
 * Created by victor on 10/6/18
 */
open class GithubRepositoryListViewModel @Inject constructor(
        private val schedulers: Schedulers,
        private val trendingRepositoriesUseCase: TrendingRepositoriesUseCase,
        private val entityViewMapper: EntityViewMapper
) : BaseViewModel(){


    private val liveData: MutableLiveData<ViewResource<List<RepositoryView>>> = MutableLiveData()

    private var repositoryList = mutableListOf<RepositoryView>()

    fun getLiveData() = liveData

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getRepositoryList() = repositoryList

    fun loadGithubRepositories(){

        //We already have the data no need to make network call again
//        if(!repositoryList.isEmpty()){
//            liveData.postValue(ViewResource(ViewState.SUCCESS,repositoryList,null))
//            return
//        }

        liveData.postValue(ViewResource(ViewState.LOADING,repositoryList,null))
        val disposable = trendingRepositoriesUseCase
                .execute()
                .subscribeOn(schedulers.subscribeOn)
                .observeOn(schedulers.observeOn)
                .subscribe ({
                    repositoryList.addAll(it.map { entityViewMapper.mapFromEntity(it) })
                    liveData.postValue(ViewResource(ViewState.SUCCESS,repositoryList,null))
                },{
                    liveData.postValue(ViewResource(ViewState.ERROR,null,it.localizedMessage))
                })
        addDisposable(disposable)
    }

}