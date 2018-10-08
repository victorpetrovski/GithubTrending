package com.victor.xapogithubtrending.features.state

/**
 * Created by victor on 10/7/18
 */
class ViewResource<out T> constructor(val status: ViewState,
                                      val data: T?,
                                      val message: String?) {

    fun <T> success(data: T): ViewResource<T> {
        return ViewResource(ViewState.SUCCESS, data, null)
    }

    fun <T> error(message: String?): ViewResource<T> {
        return ViewResource(ViewState.ERROR, null, message)
    }

    fun <T> loading(): ViewResource<T> {
        return ViewResource(ViewState.LOADING, null, null)
    }

}