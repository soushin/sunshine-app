package me.soushin.sunshine.ui.base.error

import io.reactivex.subjects.PublishSubject

class ErrorDispatcher {
    val errors = PublishSubject.create<Err>().toSerialized()

    fun onError(err: Err) = errors.onNext(err)
}
