package com.victor.xapogithubtrending.executor

import com.victor.domain.executor.Schedulers
import io.reactivex.Scheduler

/**
 * Created by victor on 10/9/18
 */
class TestSchedulers : Schedulers {

    override val computation: Scheduler
        get() = io.reactivex.schedulers.Schedulers.trampoline()

    override val subscribeOn: Scheduler
        get() = io.reactivex.schedulers.Schedulers.trampoline()

    override val observeOn: Scheduler
        get() = io.reactivex.schedulers.Schedulers.trampoline()
}