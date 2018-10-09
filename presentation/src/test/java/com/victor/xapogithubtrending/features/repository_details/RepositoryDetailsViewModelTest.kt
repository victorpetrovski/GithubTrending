package com.victor.xapogithubtrending.features.repository_details

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.victor.domain.usecase.DetailsRepositoryUseCase
import com.victor.xapogithubtrending.DataFactory
import com.victor.xapogithubtrending.any
import com.victor.xapogithubtrending.executor.TestSchedulers
import com.victor.xapogithubtrending.features.state.ViewResource
import com.victor.xapogithubtrending.features.state.ViewState
import com.victor.xapogithubtrending.model.EntityViewMapper
import com.victor.xapogithubtrending.model.RepositoryView
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

/**
 * Created by victor on 10/9/18
 */
@RunWith(JUnit4::class)
class RepositoryDetailsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    //Used to observe the changes on our ViewResource
    @Mock
    lateinit var observer: Observer<ViewResource<RepositoryView>>

    lateinit var githubRepositoryDetailsViewModel: GithubRepositoryDetailsViewModel

    @Mock
    lateinit var detailsRepositoryUseCase: DetailsRepositoryUseCase

    @Mock
    lateinit var entityViewMapper: EntityViewMapper

    val schedulers = TestSchedulers()
    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        githubRepositoryDetailsViewModel = GithubRepositoryDetailsViewModel(schedulers,detailsRepositoryUseCase,entityViewMapper)
        githubRepositoryDetailsViewModel.getLiveData().observeForever(observer)
    }

    @Test
    fun verifyUseCaseCall(){

        val repositoryEntity = DataFactory.makeRepositoryEntity()

        val randomString = DataFactory.randomString()
        whenever(detailsRepositoryUseCase.execute(DetailsRepositoryUseCase.Params(randomString,randomString)))
                .thenReturn(Observable.just(repositoryEntity))

        githubRepositoryDetailsViewModel.loadGithubRepositories(randomString,randomString)

        verify(detailsRepositoryUseCase, times(1)).execute(DetailsRepositoryUseCase.Params(randomString,randomString))

    }


    @Test
    fun verifyUseCaseDataReturn(){
        val repositoryEntity = DataFactory.makeRepositoryEntity()
        val repositoryView = DataFactory.makeRepositoryView()

        val ownerName = DataFactory.randomString()
        val repoName = DataFactory.randomString()


        val parms = DetailsRepositoryUseCase.Params(ownerName,repoName)

        whenever(entityViewMapper.mapFromEntity(repositoryEntity))
                    .thenReturn(repositoryView)


        whenever(detailsRepositoryUseCase.execute(parms))
                .thenReturn(Observable.just(repositoryEntity))

        githubRepositoryDetailsViewModel.loadGithubRepositories(ownerName,repoName)

        assertEquals(githubRepositoryDetailsViewModel.getCurrentRepository(),repositoryView)

        verify(observer).onChanged(ViewResource(ViewState.LOADING, null, null))
        verify(observer).onChanged(ViewResource(ViewState.SUCCESS, githubRepositoryDetailsViewModel.getCurrentRepository(), null))
    }

//    @Test
//    fun viewModelUseCaseErrorReturn(){
//        val error = Throwable()
//
//        whenever(trendingRepositoriesUseCase.execute())
//                .thenReturn(Observable.error(error))
//
//        githubRepositoryListViewModel.loadGithubRepositories()
//
//        verify(observer).onChanged(ViewResource(ViewState.LOADING, githubRepositoryListViewModel.getRepositoryList(), null))
//        verify(observer).onChanged(ViewResource(ViewState.ERROR, null, error.localizedMessage))
//    }
}