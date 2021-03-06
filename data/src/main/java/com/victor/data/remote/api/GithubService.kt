package com.victor.data.remote.api

import com.victor.data.model.GithubProjectModel
import com.victor.data.model.GithubResponseDataModel
import com.victor.data.model.GithubUser
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by victor on 10/5/18
 */
interface GithubService {

    @GET("search/repositories")
    fun searchRepositories(@Query("q") query: String,
                           @Query("sort") sortBy: String,
                           @Query("order") order: String)
            : Observable<GithubResponseDataModel>

    @GET("repos/{owner}/{repo}")
    fun getRepositoryDetails(@Path("owner") name: String,@Path("repo") repo: String) : Observable<GithubProjectModel>

    @GET("repos/{owner}/{repo}/contributors")
    fun getRepositoryContributors(@Path("owner") name: String,@Path("repo") repo: String) : Observable<List<GithubUser>>


}