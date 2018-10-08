package com.victor.xapogithubtrending.model

/**
 * Created by victor on 10/6/18
 */
interface EntityMapper <in E, out V> {

    fun mapFromEntity(model: E): V

}