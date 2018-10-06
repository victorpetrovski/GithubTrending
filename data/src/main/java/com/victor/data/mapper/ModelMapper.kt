package com.victor.data.mapper

/**
 * Created by victor on 10/5/18
 */
interface ModelMapper<in M, out E> {

    fun mapFromModel(model: M): E

}