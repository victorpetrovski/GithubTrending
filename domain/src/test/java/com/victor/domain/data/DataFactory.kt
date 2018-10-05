package com.victor.domain.data

import com.victor.domain.model.GithubRepository
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

    fun makeProject(): GithubRepository {
        return GithubRepository(randomString())
    }

    fun makeProjectsList(count: Int): List<GithubRepository> {
        val projects = mutableListOf<GithubRepository>()

        repeat(count) {
            projects.add(makeProject())
        }

        return projects
    }

}