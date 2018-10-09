package com.victor.data.remote

import com.victor.data.mapper.MapGithubEntity
import com.victor.data.mapper.MapGithubUserEntity
import com.victor.data.model.GithubProjectModel
import com.victor.data.model.GithubUser
import com.victor.data.remote.api.GithubService
import com.victor.data.repository.gateway.GithubRemoteRepo
import com.victor.domain.model.GithubRepositoryEntity
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * Created by victor on 10/5/18
 */
open class GithubRemoteRepoImpl @Inject constructor(
        private val githubService: GithubService,
        private val mapGithubEntity: MapGithubEntity,
        private val mapGithubUserEntity: MapGithubUserEntity
) : GithubRemoteRepo {

    override fun getSingleProjectRepository(ownerName: String, name: String): Observable<GithubRepositoryEntity> {

        val repositoryDetails =  githubService.getRepositoryDetails(ownerName,name)
        val repositoryContributors = githubService.getRepositoryContributors(ownerName,name)

        return Observable.zip(repositoryDetails,repositoryContributors,
                BiFunction<GithubProjectModel, List<GithubUser>, Pair<GithubProjectModel,List<GithubUser>>> {
                    githubProjectModel, contributorsList -> Pair(githubProjectModel,contributorsList)
                 })
                .map {
                    val githubProjectEntity = mapGithubEntity.mapFromModel(it.first)
                    githubProjectEntity.repoContributors = it.second.map { mapGithubUserEntity.mapFromModel(it) }
                    return@map githubProjectEntity
                }

        }


    override fun getTrendingAndroidProjects(): Observable<List<GithubRepositoryEntity>> {
        return githubService.searchRepositories("android","stars","desc").map {
            it.list.map { mapGithubEntity.mapFromModel(it) }
        }
    }

}