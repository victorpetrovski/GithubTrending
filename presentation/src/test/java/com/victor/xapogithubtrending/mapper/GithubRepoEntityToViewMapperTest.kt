package com.victor.xapogithubtrending.mapper

import com.nhaarman.mockito_kotlin.whenever
import com.victor.xapogithubtrending.DataFactory
import com.victor.xapogithubtrending.model.EntityUserMapper
import com.victor.xapogithubtrending.model.EntityViewMapper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by victor on 10/9/18
 */
class GithubRepoEntityToViewMapperTest {

    lateinit var entityViewMapper: EntityViewMapper

    @Mock
    lateinit var entityUserMapper: EntityUserMapper

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        entityViewMapper = EntityViewMapper(entityUserMapper)
    }

    @Test
    fun testGithubEntityViewMapper(){
        val entity = DataFactory.makeRepositoryEntity()
        val entityUserList = DataFactory.makeGithubUserEntity()
        val githubUserView = DataFactory.makeGithubUserView()
        whenever(entityUserMapper.mapFromEntity(entityUserList)).thenReturn(githubUserView)

        val repoView = entityViewMapper.mapFromEntity(entity)


        assertEquals(entity.id.toString(),repoView.id)
        assertEquals(entity.repositoryName,repoView.name)
        assertEquals(entity.description,repoView.repoDescription)
        assertEquals(entity.stars,repoView.starCount)
        assertEquals(entity.createdAt,repoView.dateCreated)
        assertEquals(entity.ownerName,repoView.ownerName)
        assertEquals(entity.forkCount,repoView.forkCount)
        assertEquals(entity.watchCount,repoView.watchCount)
        assertEquals(entity.repoContributors,repoView.contributorsList)

    }
}