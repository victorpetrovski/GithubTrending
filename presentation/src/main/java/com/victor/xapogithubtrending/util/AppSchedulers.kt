package com.victor.xapogithubtrending.util

import com.victor.domain.executor.Schedulers
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * Created by victor on 10/6/18
 */
class AppSchedulers @Inject constructor() : Schedulers {

    override val computation: Scheduler
        get() =  io.reactivex.schedulers.Schedulers.computation()


    override val subscribeOn: Scheduler
        get() = io.reactivex.schedulers.Schedulers.io()

    override val observeOn: Scheduler
        get() = io.reactivex.android.schedulers.AndroidSchedulers.mainThread()

}