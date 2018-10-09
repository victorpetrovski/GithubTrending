package com.victor.xapogithubtrending.features.state

/**
 * Created by victor on 10/7/18
 */
data class ViewResource<out T> constructor(val status: ViewState,
                                      val data: T?,
                                      val message: String?)