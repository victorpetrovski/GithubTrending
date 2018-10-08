package com.victor.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by victor on 10/6/18
 */
data class GithubResponseDataModel (@SerializedName("items") val list : List<GithubProjectModel>)