package com.victor.xapogithubtrending.features.repository_details

import android.arch.lifecycle.MutableLiveData
import com.victor.domain.executor.Schedulers
import com.victor.domain.usecase.DetailsRepositoryUseCase
import com.victor.xapogithubtrending.base.BaseViewModel
import com.victor.xapogithubtrending.features.state.ViewResource
import com.victor.xapogithubtrending.features.state.ViewState
import com.victor.xapogithubtrending.model.EntityViewMapper
import com.victor.xapogithubtrending.model.RepositoryView
import javax.inject.Inject

/**
 * Created by victor on 10/7/18
 */
class GithubRepositoryDetailsViewModel @Inject constructor(
        private val schedulers: Schedulers,
        private val detailsRepositoryUseCase: DetailsRepositoryUseCase,
        private val entityViewMapper: EntityViewMapper) : BaseViewModel() {

    private val liveData: MutableLiveData<ViewResource<RepositoryView>> = MutableLiveData()

    private  var currentRepository : RepositoryView? = null

    fun getLiveData() = liveData

    fun loadGithubRepositories( ownerName : String, repoName : String){
        liveData.postValue(ViewResource(ViewState.LOADING,null,null))
        val disposable = detailsRepositoryUseCase
                .execute(DetailsRepositoryUseCase.Params(ownerName,repoName))
                .subscribeOn(schedulers.subscribeOn)
                .observeOn(schedulers.observeOn)
                .subscribe ({
                    currentRepository = entityViewMapper.mapFromEntity(it)
                    liveData.postValue(ViewResource(ViewState.SUCCESS,currentRepository,null))
                },{
                    liveData.postValue(ViewResource(ViewState.ERROR,null,it.localizedMessage))
                })
        addDisposable(disposable)
    }
}