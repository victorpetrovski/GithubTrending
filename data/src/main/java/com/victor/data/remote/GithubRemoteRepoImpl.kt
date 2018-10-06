package com.victor.data.remote

import com.victor.data.mapper.MapGithubEntity
import com.victor.data.remote.api.GithubService
import com.victor.data.repository.gateway.GithubRemoteRepo
import com.victor.domain.model.GithubRepositoryEntity
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by victor on 10/5/18
 */
open class GithubRemoteRepoImpl @Inject constructor(
        private val githubService: GithubService,
        private val mapGithubEntity: MapGithubEntity
) : GithubRemoteRepo {

    override fun getTrendingAndroidProjects(): Observable<List<GithubRepositoryEntity>> {
        return githubService.searchRepositories("android","stars","desc").map {
            it.list.map { mapGithubEntity.mapFromModel(it) }
        }
    }

    override fun getProjectById(id: Long): Observable<GithubRepositoryEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}