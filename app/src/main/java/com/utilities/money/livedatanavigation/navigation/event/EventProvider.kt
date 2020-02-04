package com.utilities.money.livedatanavigation.navigation.event

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

@Suppress("UNCHECKED_CAST")
class EventProvider private constructor(
    val store: EventsStore,
    lifecycleOwner: LifecycleOwner?
) : LifecycleObserver {

    init {
        lifecycleOwner?.lifecycle?.addObserver(this)
    }

    companion object {

        internal fun get(lifecycleOwner: LifecycleOwner): EventProvider {
            val store = EventsStore()
            return EventProvider(store, lifecycleOwner)
        }

    }

    fun <T: Any> get(clazz: Class<T>): T {
        val observer = this.store.get(clazz)
        if(observer != null) {
            return observer
        }
        return createObserver(clazz)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onLifecycleOwnerDestroyed() {
        this.store.clear()
    }

    private fun <T: Any> createObserver(clazz: Class<T>): T {
        val observer = clazz.constructors.get(0).newInstance() as T
        this.store.put(clazz, observer)
        return observer
    }

}