package com.victor.data.repository

import com.victor.data.repository.gateway.GithubRemoteRepo
import com.victor.domain.gateway.GithubRepositoryGateway
import com.victor.domain.model.GithubRepositoryEntity
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by victor on 10/5/18
 */
class GithubRepositoryImpl @Inject constructor(
        private val githubRemoteRepo: GithubRemoteRepo
) : GithubRepositoryGateway {

    override fun getProjects(): Observable<List<GithubRepositoryEntity>> = githubRemoteRepo.getTrendingAndroidProjects()


    override fun getProjectByName(ownerName : String,name : String): Observable<GithubRepositoryEntity>
            = githubRemoteRepo.getSingleProjectRepository(ownerName,name)
}