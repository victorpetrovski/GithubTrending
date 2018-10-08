package com.victor.data.remote.api

import com.google.gson.Gson
import com.victor.data.model.GithubProjectModel
import com.victor.data.model.GithubResponseDataModel
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by victor on 10/5/18
 */
class GithubRemoteApi @Inject constructor(private val baseUrl : String, private  val isDebug : Boolean) : GithubService {



    private lateinit var githubService: GithubService

    init {
        makeGithubService()
    }

    fun makeGithubService(): GithubService {
        val okHttpClient = makeOkHttpClient(
                makeLoggingInterceptor((isDebug)))
        githubService = makeTaxiService(okHttpClient, Gson())
        return githubService
    }

    private fun makeTaxiService(okHttpClient: OkHttpClient, gson: Gson): GithubService {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(GithubService::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    /**
     *
     * API Requests
     *
     * */


    override fun searchRepositories(query: String, sortBy: String, order: String) = githubService.searchRepositories(query,sortBy,order)

    override fun getRepositoryDetails(name: String, repo: String)
            = githubService.getRepositoryDetails(name,repo)

}