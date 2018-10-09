package com.victor.domain.model

/**
 * Created by victor on 10/5/18
 */

data class GithubRepositoryEntity(
        val repositoryName : String,
        val  link : String, val  id : Long,
        val ownerAvatar : String,
        val ownerName : String,
        val stars : Long,
        val description : String,
        val createdAt : String,
        val forkCount : Int,
        val watchCount : Int){

    var repoContributors : List<GithubUserEntity>? = null
}