package com.victor.data.mapper

import com.victor.data.DataFactory
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by victor on 10/6/18
 */
class MapGithubEntityTest {

    private lateinit var mapGithubEntity: MapGithubEntity

    @Before
    fun setup(){
        mapGithubEntity = MapGithubEntity()
    }

    @Test
    fun modelToEntityMapperTest(){
        val model = DataFactory.makeGithubRepoModel()
        val entity = mapGithubEntity.mapFromModel(model)


        assertEquals(model.id,entity.id)
        assertEquals(model.description,entity.description)
        assertEquals(model.link,entity.link)
        assertEquals(model.name,entity.name)

    }


}