package com.victor.data.remote

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import com.victor.data.DataFactory
import com.victor.data.mapper.MapGithubEntity
import com.victor.data.mapper.MapGithubUserEntity
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
    lateinit var mapGithubUserEntity: MapGithubUserEntity

    @Mock
    lateinit var githubService: GithubService

    private lateinit var githubRemoteRepoImpl: GithubRemoteRepoImpl

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        githubRemoteRepoImpl = GithubRemoteRepoImpl(githubService,mapGithubEntity,mapGithubUserEntity)
    }


    @Test
    fun githubRepositoryTrendingRemoteCompleteTest(){
        val responseData = DataFactory.makeGithubResponseDataModel(5)

        whenever(githubService.searchRepositories(any(), any(), any())).thenReturn(Observable.just(responseData))

        val test = githubRemoteRepoImpl.getTrendingAndroidProjects().test()

        test.assertComplete()
    }

    @Test
    fun githubRepositoryTrendingRemoteDataValueTest(){

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

    @Test
    fun githubRepositoryDetailsRemoteDataValueTest(){
        val responseData = DataFactory.makeGithubRepoModel()
        val githubEntity = DataFactory.makeGithubRepoEntity()
        val count = 5
        val githubUsers = DataFactory.makeGithubUsersList(count)
        val githubUsersEntity = DataFactory.makeGithubUsersEntityList(count)

        val ownerName = DataFactory.randomString()
        val repoName = DataFactory.randomString()

        whenever(mapGithubEntity.mapFromModel(responseData)).thenReturn(githubEntity)

        repeat(count){
            val model = githubUsers[it]
            whenever(mapGithubUserEntity.mapFromModel(model))
                    .thenReturn(githubUsersEntity[it])
        }

        whenever(githubService.getRepositoryDetails(ownerName, repoName)).thenReturn(Observable.just(responseData))
        whenever(githubService.getRepositoryContributors(ownerName,repoName)).thenReturn(Observable.just(githubUsers))


        val test = githubRemoteRepoImpl.getSingleProjectRepository(ownerName, repoName).test()

        githubEntity.repoContributors = githubUsersEntity
        test.assertValue(githubEntity)
    }

    @Test
    fun githubRepositoryDetailsRemoteCompleteTest(){
        val responseData = DataFactory.makeGithubRepoModel()
        val githubEntity = DataFactory.makeGithubRepoEntity()
        val count = 5
        val githubUsers = DataFactory.makeGithubUsersList(count)
        val githubUserEntity = DataFactory.makeGithubUsersEntityList(count)

        val ownerName = DataFactory.randomString()
        val repoName = DataFactory.randomString()

        whenever(mapGithubEntity.mapFromModel(responseData)).thenReturn(githubEntity)

        repeat(count){
            val model = githubUsers[it]
            whenever(mapGithubUserEntity.mapFromModel(model))
                    .thenReturn(githubUserEntity[it])
        }

        whenever(githubService.getRepositoryDetails(ownerName, repoName)).thenReturn(Observable.just(responseData))
        whenever(githubService.getRepositoryContributors(ownerName,repoName)).thenReturn(Observable.just(githubUsers))


        val test = githubRemoteRepoImpl.getSingleProjectRepository(ownerName, repoName).test()

        test.assertComplete()
    }
}