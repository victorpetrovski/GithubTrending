package com.victor.data.remote

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import com.victor.data.DataFactory
import com.victor.data.mapper.MapGithubEntity
import com.victor.data.remote.api.GithubService
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by victor on 10/6/18
 */
class GithubRemoteRepoImplTest {

    @Mock
    lateinit var mapGithubEntity: MapGithubEntity

    @Mock
    lateinit var githubService: GithubService

    private lateinit var githubRemoteRepoImpl: GithubRemoteRepoImpl

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        githubRemoteRepoImpl = GithubRemoteRepoImpl(githubService,mapGithubEntity)
    }


    @Test
    fun githubRepositoryRemoteCompleteTest(){
        val responseData = DataFactory.makeGithubResponseDataModel(5)

        whenever(githubService.searchRepositories(any(), any(), any())).thenReturn(Observable.just(responseData))

        val test = githubRemoteRepoImpl.getTrendingAndroidProjects().test()

        test.assertComplete()
    }

    @Test
    fun githubRepositoryRemoteDataValueTest(){

        val count = 5
        val responseData = DataFactory.makeGithubResponseDataModel(count)
        val entityList = DataFactory.makeGithubEntityList(count)

        repeat(count){
            val model = responseData.list[it]
            whenever(mapGithubEntity.mapFromModel(model))
                    .thenReturn(entityList[it])
        }

        whenever(githubService.searchRepositories(any(), any(), any())).thenReturn(Observable.just(responseData))

        val test = githubRemoteRepoImpl.getTrendingAndroidProjects().test()

        test.assertValue(entityList)
    }
}