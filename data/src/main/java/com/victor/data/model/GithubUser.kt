package com.victor.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by victor on 10/8/18
 */
data class GithubUser( @SerializedName("login") val name : String,
                       @SerializedName("avatar_url") val avatarUrl : String,
                       val contributions : Int,
                       @SerializedName("html_url") val profileUrl : String)