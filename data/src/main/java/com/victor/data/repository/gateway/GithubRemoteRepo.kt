package com.victor.data.repository.gateway

import com.victor.domain.model.GithubRepositoryEntity
import io.reactivex.Observable

/**
 * Created by victor on 10/5/18
 */
interface GithubRemoteRepo {

    fun getTrendingAndroidProjects(): Observable<List<GithubRepositoryEntity>>

    fun getSingleProjectRepository(ownerName : String, name : String) : Observable<GithubRepositoryEntity>

}