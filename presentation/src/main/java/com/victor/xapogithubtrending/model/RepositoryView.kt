package com.victor.xapogithubtrending.model

/**
 * Created by victor on 10/6/18
 */
data class RepositoryView (val id: String, val name: String, val repoDescription: String,
                           val starCount: Long, val dateCreated: String,
                           val ownerName: String, val ownerAvatar: String,
                           val forkCount : Int, val watchCount : Int,
                           val contributorsList : List<GithubUserView>?)