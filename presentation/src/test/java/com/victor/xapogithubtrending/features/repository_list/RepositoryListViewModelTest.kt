package com.victor.xapogithubtrending.features.repository_list

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.nhaarman.mockito_kotlin.*
import com.victor.domain.model.GithubRepositoryEntity
import com.victor.domain.usecase.TrendingRepositoriesUseCase
import com.victor.xapogithubtrending.DataFactory
import com.victor.xapogithubtrending.executor.TestSchedulers
import com.victor.xapogithubtrending.features.state.ViewResource
import com.victor.xapogithubtrending.features.state.ViewState
import com.victor.xapogithubtrending.model.EntityViewMapper
import com.victor.xapogithubtrending.model.RepositoryView
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

/**
 * Created by victor on 10/9/18
 */
@RunWith(JUnit4::class)
class RepositoryListViewModelTest {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var githubRepositoryListViewModel: GithubRepositoryListViewModel

    var schedulers = TestSchedulers()

    @Mock
    lateinit var trendingRepositoriesUseCase: TrendingRepositoriesUseCase

    @Mock
    lateinit var entityViewMapper: EntityViewMapper

    //Used to observe the changes on our ViewResource
    @Mock lateinit var observer: Observer<ViewResource<List<RepositoryView>>>

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        githubRepositoryListViewModel = GithubRepositoryListViewModel(schedulers,trendingRepositoriesUseCase,entityViewMapper)
        githubRepositoryListViewModel.getLiveData().observeForever(observer)
    }

    @Test
    fun verifyUseCaseCall(){

        val returnList = DataFactory.makeRepositoryEntityList(5)
        whenever(trendingRepositoriesUseCase.execute())
                .thenReturn(Observable.just(returnList))

        githubRepositoryListViewModel.loadGithubRepositories()

        verify(trendingRepositoriesUseCase, times(1)).execute()

        verify(observer).onChanged(ViewResource(ViewState.LOADING, githubRepositoryListViewModel.getRepositoryList(), null))
        verify(observer).onChanged(ViewResource(ViewState.SUCCESS, githubRepositoryListViewModel.getRepositoryList(), null))

    }


    @Test
    fun verifyUseCaseDataReturn(){
        val count = 5
        val githubEntityList = DataFactory.makeRepositoryEntityList(count)
        val githubRepoViewList = DataFactory.makeRepositoryViewList(count)


        repeat(count){
            whenever(entityViewMapper.mapFromEntity(githubEntityList[it]))
                    .thenReturn(githubRepoViewList[it])
        }

        whenever(trendingRepositoriesUseCase.execute())
                .thenReturn(Observable.just(githubEntityList))

        githubRepositoryListViewModel.loadGithubRepositories()

        assertEquals(githubRepositoryListViewModel.getLiveData().value?.data,githubRepoViewList)

        verify(observer).onChanged(ViewResource(ViewState.LOADING, githubRepositoryListViewModel.getRepositoryList(), null))
        verify(observer).onChanged(ViewResource(ViewState.SUCCESS, githubRepositoryListViewModel.getRepositoryList(), null))
    }

    @Test
    fun viewModelUseCaseErrorReturn(){
        val error = Throwable()

        whenever(trendingRepositoriesUseCase.execute())
                .thenReturn(Observable.error(error))

        githubRepositoryListViewModel.loadGithubRepositories()

        verify(observer).onChanged(ViewResource(ViewState.LOADING, githubRepositoryListViewModel.getRepositoryList(), null))
        verify(observer).onChanged(ViewResource(ViewState.ERROR, null, error.localizedMessage))
    }
}