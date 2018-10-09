package com.victor.data

import com.victor.data.model.GithubProjectModel
import com.victor.data.model.GithubResponseDataModel
import com.victor.data.model.GithubUser
import com.victor.domain.model.GithubRepositoryEntity
import com.victor.domain.model.GithubUserEntity
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

    fun randomInt() : Int{
        return Math.random().toInt()
    }

    fun makeGithubRepoModel(): GithubProjectModel {
        return GithubProjectModel(randomString(), randomString(), randomLong(), randomString(), makeGithubUsers(), randomLong(), randomString(), randomInt(), randomInt())
    }

    fun makeGithubUsers(): GithubUser {
        return GithubUser(randomString(), randomString(), randomInt(), randomString())
    }

    fun makeGithubUserEntity() : GithubUserEntity{
        return GithubUserEntity(randomString(), randomString(), randomInt(), randomString())
    }

    fun makeGithubRepoEntity(): GithubRepositoryEntity {
        return GithubRepositoryEntity(randomString(), randomString(), randomLong(), randomString(), randomString(), randomLong(), randomString(), randomString(), randomInt(), randomInt())
    }


    fun makeGithubUsersList(count: Int): List<GithubUser> {
        val projects = mutableListOf<GithubUser>()

        repeat(count) {
            projects.add(makeGithubUsers())
        }

        return projects
    }

    fun makeGithubUsersEntityList(count: Int): List<GithubUserEntity> {
        val projects = mutableListOf<GithubUserEntity>()

        repeat(count) {
            projects.add(makeGithubUserEntity())
        }

        return projects
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