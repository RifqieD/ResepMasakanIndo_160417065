package com.example.resepmasakanindo_160417065.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

fun <T> MutableLiveData<T>.observeOnce(owner: LifecycleOwner, observer: (T) -> Unit) {
    observe(owner, object: Observer<T> {
        override fun onChanged(value: T) {
            if (owner.lifecycle.currentState == Lifecycle.State.DESTROYED) {
                removeObserver(this)
            }
            observer(value)
        }
    })
}