package co.joebirch.domain.interactor

import co.joebirch.domain.executor.PostExecutionThread
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber

abstract class FlowableUseCase<T, in Params> constructor(
        private val postExecutionThread: PostExecutionThread) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(params: Params? = null): Flowable<T>

    open fun execute(singleObserver: DisposableSubscriber<T>, params: Params? = null) {
        val single = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.scheduler)
        addDisposable(single.subscribeWith(singleObserver))
    }

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

}