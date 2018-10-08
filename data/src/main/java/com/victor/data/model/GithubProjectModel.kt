package com.victor.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by victor on 10/5/18
 */
data class GithubProjectModel (
        val name : String, @SerializedName("url") val  link : String,
        val id : Long, val description : String,
        val owner: GithubRepoOwner, @SerializedName("stargazers_count") val stars : Long,
        @SerializedName("created_at") val createdAt : String)