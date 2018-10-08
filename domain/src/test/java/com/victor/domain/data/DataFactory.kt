package com.victor.domain.data

import com.victor.domain.model.GithubRepositoryEntity
import java.util.*

/**
 * Created by victor on 10/5/18
 */
object DataFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomBoolean(): Boolean {
        return Math.random() > 0.5
    }

    fun randomLong(): Long {
        return Math.random().toLong()
    }

    fun makeProject(): GithubRepositoryEntity {
        return GithubRepositoryEntity(randomString(), randomString(), randomLong(), randomString(), randomString(), randomLong(), randomString(), randomString())
    }

    fun makeProjectsList(count: Int): List<GithubRepositoryEntity> {
        val projects = mutableListOf<GithubRepositoryEntity>()

        repeat(count) {
            projects.add(makeProject())
        }

        return projects
    }

}