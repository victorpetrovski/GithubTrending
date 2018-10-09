package com.victor.domain.usecase

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import com.victor.domain.data.DataFactory
import com.victor.domain.executor.Schedulers
import com.victor.domain.executor.TestSchedulers
import com.victor.domain.gateway.GithubRepositoryGateway
import com.victor.domain.model.GithubRepositoryEntity
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import javax.xml.crypto.Data

/**
 * Created by victor on 10/5/18
 */
class DetailsRepositoryTest {

    private lateinit var detailsRepositoryUseCase: DetailsRepositoryUseCase

    @Mock
    lateinit var repositoryGateway: GithubRepositoryGateway

    val schedulers = TestSchedulers()

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        detailsRepositoryUseCase = DetailsRepositoryUseCase(schedulers, repositoryGateway)
    }

    @Test
    fun fetchRepositoryDetailsCompleteTest(){
        val repoName = DataFactory.randomString()
        val ownerName = DataFactory.randomString()
        mockGatewayCall(ownerName,repoName,Observable.just(DataFactory.makeProject()))
        val testObservable = detailsRepositoryUseCase.execute(DetailsRepositoryUseCase.Params(ownerName,repoName)).test()
        testObservable.assertComplete()
    }

    @Test
    fun fetchRepositoryDetailsDataValueTest(){
        val id = DataFactory.randomString()
        val ownerName = DataFactory.randomString()
        val param = DetailsRepositoryUseCase.Params(ownerName,id)
        val projects = DataFactory.makeProject()
        mockGatewayCall(ownerName,id,Observable.just(projects))
        val testObservable = detailsRepositoryUseCase.execute(param).test()
        testObservable.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun fetchRepositoryDetailsExceptionTest(){
        mockGatewayCall(any(), any(),Observable.just(DataFactory.makeProject()))
        val testObserver = detailsRepositoryUseCase.execute().test()
        testObserver.assertComplete()
    }

    private fun mockGatewayCall(ownerName : String, name : String,observable : Observable<GithubRepositoryEntity>) =
            whenever(repositoryGateway.getProjectByName(ownerName,name)).thenReturn(observable)


}