package com.victor.domain.gateway

import com.victor.domain.model.GithubRepositoryEntity
import io.reactivex.Observable

/**
 * Created by victor on 10/5/18
 */
interface GithubRepositoryGateway {

    fun getProjects(): Observable<List<GithubRepositoryEntity>>

    fun getProjectByName(owner : String,name : String) : Observable<GithubRepositoryEntity>

}