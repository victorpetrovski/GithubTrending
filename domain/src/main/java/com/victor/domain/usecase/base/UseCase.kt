package com.victor.domain.usecase.base

import com.victor.domain.executor.Schedulers
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

abstract class UseCase<T, in Params> constructor(
        private val schedulers: Schedulers) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(params: Params? = null): Observable<T>

    open fun execute(singleObserver: DisposableObserver<T>, params: Params? = null): Observable<T> {
        val observable = this.buildUseCaseObservable(params)
                .subscribeOn(schedulers.subscribeOn)
                .observeOn(schedulers.observeOn)
        addDisposable(observable.subscribeWith(singleObserver))
        return observable
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

}