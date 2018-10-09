package com.victor.data.mapper

import com.victor.data.DataFactory
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by victor on 10/9/18
 */
class MapGithubUserEntityTest {

    private lateinit var mapGithubUserEntity: MapGithubUserEntity

    @Before
    fun setup(){
        mapGithubUserEntity = MapGithubUserEntity()
    }

    @Test
    fun modelToEntityMapperTest(){
        val model = DataFactory.makeGithubUsers()
        val entity = mapGithubUserEntity.mapFromModel(model)


        Assert.assertEquals(model.avatarUrl, entity.avatarUrl)
        Assert.assertEquals(model.contributions, entity.contributions)
        Assert.assertEquals(model.profileUrl, entity.profileUrl)
        Assert.assertEquals(model.name, entity.name)

    }
}