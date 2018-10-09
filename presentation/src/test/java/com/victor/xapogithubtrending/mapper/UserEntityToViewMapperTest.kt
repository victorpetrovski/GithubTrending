package com.victor.xapogithubtrending.mapper

import com.victor.xapogithubtrending.DataFactory
import com.victor.xapogithubtrending.model.EntityUserMapper
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

/**
 * Created by victor on 10/9/18
 */
class UserEntityToViewMapperTest {

    lateinit var entityUserMapper: EntityUserMapper

    @Before
    fun setup(){
        entityUserMapper = EntityUserMapper()
    }

    @Test
    fun testUserEntityToViewMapper(){
        val entityUser = DataFactory.makeGithubUserEntity()

        val viewUser = entityUserMapper.mapFromEntity(entityUser)

        assertEquals(entityUser.avatarUrl,viewUser.avatarUrl)
        assertEquals(entityUser.contributions,viewUser.contributions)
        assertEquals(entityUser.name,viewUser.name)
        assertEquals(entityUser.profileUrl,viewUser.profileUrl)

    }
}