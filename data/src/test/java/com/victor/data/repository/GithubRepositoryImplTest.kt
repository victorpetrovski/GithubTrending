package com.victor.data.repository

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import com.victor.data.DataFactory
import com.victor.data.remote.GithubRemoteRepoImpl
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by victor on 10/6/18
 */
class GithubRepositoryImplTest {

    private lateinit var githubRepositoryImpl: GithubRepositoryImpl

    @Mock
    lateinit var githubRemoteRepoImpl: GithubRemoteRepoImpl

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        githubRepositoryImpl = GithubRepositoryImpl(githubRemoteRepoImpl)
    }

    @Test
    fun githubRepositoryTrendingDataCompleteTest(){

        whenever(githubRemoteRepoImpl.getTrendingAndroidProjects()).thenReturn(Observable.just(DataFactory.makeGithubEntityList(5)))

        val test = githubRepositoryImpl.getProjects().test()

        test.assertComplete()

    }

    @Test
    fun githubRepositoryTrendingDataValueTest(){

        val listData = DataFactory.makeGithubEntityList(5)
        whenever(githubRemoteRepoImpl.getTrendingAndroidProjects())
                .thenReturn(Observable.just(listData))

        val test = githubRepositoryImpl.getProjects().test()

        test.assertValue(listData)
    }

    @Test
    fun githubRepositoryDetailsValueTest(){
        val repoEntity = DataFactory.makeGithubRepoEntity()

        val owner = DataFactory.randomString()
        val repoName = DataFactory.randomString()

        whenever(githubRemoteRepoImpl.getSingleProjectRepository(owner,repoName))
                .thenReturn(Observable.just(repoEntity))

        val test = githubRepositoryImpl.getProjectByName(owner,repoName).test()

        test.assertValue(repoEntity)

    }


}