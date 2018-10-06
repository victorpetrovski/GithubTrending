package com.victor.data

import com.victor.data.model.GithubProjectModel
import com.victor.data.model.GithubResponseDataModel
import com.victor.domain.model.GithubRepositoryEntity
import java.util.*

/**
 * Created by victor on 10/6/18
 */
object DataFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomLong(): Long {
        return Math.random().toLong()
    }

    fun makeGithubRepoModel(): GithubProjectModel {
        return GithubProjectModel(randomString(), randomString(), randomLong(), randomString())
    }

    fun makeGithubRepoEntity(): GithubRepositoryEntity {
        return GithubRepositoryEntity(randomString(), randomString(), randomLong(), randomString())
    }

    fun makeGithubRepositoryModelList(count: Int): List<GithubProjectModel> {
        val projects = mutableListOf<GithubProjectModel>()

        repeat(count) {
            projects.add(makeGithubRepoModel())
        }

        return projects
    }

    fun makeGithubEntityList(count: Int): List<GithubRepositoryEntity> {
        val projects = mutableListOf<GithubRepositoryEntity>()

        repeat(count) {
            projects.add(makeGithubRepoEntity())
        }

        return projects
    }

    fun makeGithubResponseDataModel(count : Int) = GithubResponseDataModel(makeGithubRepositoryModelList(count))

}