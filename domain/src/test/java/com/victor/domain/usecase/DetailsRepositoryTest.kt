package com.victor.domain.usecase

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import com.victor.domain.data.DataFactory
import com.victor.domain.executor.Schedulers
import com.victor.domain.gateway.RepositoryGateway
import com.victor.domain.model.GithubRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by victor on 10/5/18
 */
class DetailsRepositoryTest {

    private lateinit var detailsRepositoryUseCase: DetailsRepositoryUseCase

    @Mock
    lateinit var repositoryGateway: RepositoryGateway

    @Mock
    lateinit var schedulers: Schedulers


    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        detailsRepositoryUseCase = DetailsRepositoryUseCase(schedulers, repositoryGateway)
    }

    @Test
    fun fetchRepositoryDetailsCompleteTest(){
        mockGatewayCall(any(),Observable.just(DataFactory.makeProject()))
        val testObservable = detailsRepositoryUseCase.buildUseCaseObservable(DetailsRepositoryUseCase.Params(any())).test()
        testObservable.assertComplete()
    }

    @Test
    fun fetchRepositoryDetailsDataValueTest(){
        val id = DataFactory.randomLong()
        val param = DetailsRepositoryUseCase.Params(id)
        val projects = DataFactory.makeProject()
        mockGatewayCall(id,Observable.just(projects))
        val testObservable = detailsRepositoryUseCase.buildUseCaseObservable(param).test()
        testObservable.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun fetchRepositoryDetailsExceptionTest(){
        mockGatewayCall(any(),Observable.just(DataFactory.makeProject()))
        val testObserver = detailsRepositoryUseCase.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    private fun mockGatewayCall(id : Long,observable : Observable<GithubRepository>) =
            whenever(repositoryGateway.getProjectById(id)).thenReturn(observable)


}