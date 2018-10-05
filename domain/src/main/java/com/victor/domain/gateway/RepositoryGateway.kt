package com.victor.domain.gateway

import com.victor.domain.model.GithubRepository
import io.reactivex.Observable

/**
 * Created by victor on 10/5/18
 */
interface RepositoryGateway {

    fun getProjects(): Observable<List<GithubRepository>>

    fun getProjectById( id : Long) : Observable<GithubRepository>

}