package com.victor.xapogithubtrending

import com.victor.domain.model.GithubRepositoryEntity
import com.victor.domain.model.GithubUserEntity
import com.victor.xapogithubtrending.model.GithubUserView
import com.victor.xapogithubtrending.model.RepositoryView
import java.util.*

/**
 * Created by victor on 10/9/18
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

    fun makeGithubUserView() : GithubUserView{
        return  GithubUserView(randomString(), randomString(), randomInt(), randomString())
    }

    fun makeGithubUserEntity() : GithubUserEntity{
        return  GithubUserEntity(randomString(), randomString(), randomInt(), randomString())
    }

    fun makeRepositoryView(): RepositoryView{
        return RepositoryView(randomString(), randomString(), randomString(), randomLong(), randomString(), randomString(), randomString(), randomInt(), randomInt(), makeContributorsList(randomInt()))
    }

    fun makeRepositoryEntity(): GithubRepositoryEntity{
        return GithubRepositoryEntity(DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomLong(), DataFactory.randomString(),
                DataFactory.randomString(), DataFactory.randomLong(), DataFactory.randomString(), DataFactory.randomString(), DataFactory.randomInt(), DataFactory.randomInt())
    }

    fun makeContributorsList(count : Int) : List<GithubUserView>{
            val projects = mutableListOf<GithubUserView>()

            repeat(count) {
                projects.add(makeGithubUserView())
            }

            return projects

    }

    fun makeRepositoryViewList(count : Int) : List<RepositoryView>{
        val projects = mutableListOf<RepositoryView>()

        repeat(count) {
            projects.add(makeRepositoryView())
        }

        return projects

    }

    fun makeRepositoryEntityList(count : Int) : List<GithubRepositoryEntity>{
        val projects = mutableListOf<GithubRepositoryEntity>()

        repeat(count) {
            projects.add(makeRepositoryEntity())
        }

        return projects

    }
}