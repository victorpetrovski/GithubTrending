package com.victor.domain.usecase

import com.nhaarman.mockito_kotlin.whenever
import com.victor.domain.data.DataFactory
import com.victor.domain.executor.Schedulers
import com.victor.domain.gateway.RepositoryGateway
import com.victor.domain.model.GithubRepositoryEntity
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by victor on 10/5/18
 */
class TrendingRepositoriesTest {


    private lateinit var trendingRepositoriesUseCase: TrendingRepositoriesUseCase


    @Mock
    lateinit var repositoryGateway: RepositoryGateway

    @Mock
    lateinit var schedulers: Schedulers


    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        trendingRepositoriesUseCase = TrendingRepositoriesUseCase(schedulers, repositoryGateway)
    }

    @Test
    fun fetchGithubRepositoriesCallTest(){
        mockGatewayCall(Observable.just(DataFactory.makeProjectsList(5)))
        val testObservable = trendingRepositoriesUseCase.buildUseCaseObservable().test()
        testObservable.assertComplete()
    }

    @Test
    fun githubRepositoriesDataValueTest(){
        val githubRepositories = DataFactory.makeProjectsList(5)
        mockGatewayCall(Observable.just(githubRepositories))
        val testObservable = trendingRepositoriesUseCase.buildUseCaseObservable().test()
        testObservable.assertValue(githubRepositories)
    }

    private fun mockGatewayCall(observable : Observable<List<GithubRepositoryEntity>>) =
        whenever(repositoryGateway.getProjects()).thenReturn(observable)


}