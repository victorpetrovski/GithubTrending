package com.victor.domain.executor

import io.reactivex.Scheduler

/**
 * Created by victor on 10/5/18
 */
interface Schedulers {

    val subscribeOn: Scheduler

    val observeOn: Scheduler

    val computation: Scheduler

}