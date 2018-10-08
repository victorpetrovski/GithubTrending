package com.victor.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by victor on 10/7/18
 */
data class GithubRepoOwner(@SerializedName("login") var name : String,
                           @SerializedName("avatar_url") var avatar : String,
                           var id : Long)