package com.victor.domain.executor

import io.reactivex.Scheduler

interface Schedulers {

    val subscribeOn: Scheduler

    val observeOn: Scheduler

    val computation: Scheduler

}