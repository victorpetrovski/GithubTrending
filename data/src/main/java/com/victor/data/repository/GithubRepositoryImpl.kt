package com.victor.data.repository

import com.victor.data.repository.gateway.GithubRemoteRepo
import com.victor.domain.gateway.RepositoryGateway
import com.victor.domain.model.GithubRepositoryEntity
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by victor on 10/5/18
 */
class GithubRepositoryImpl @Inject constructor(
        private val githubRemoteRepo: GithubRemoteRepo
) : RepositoryGateway {

    override fun getProjects(): Observable<List<GithubRepositoryEntity>> = githubRemoteRepo.getTrendingAndroidProjects()


    override fun getProjectById(id: Long): Observable<GithubRepositoryEntity> = githubRemoteRepo.getProjectById(id)
}