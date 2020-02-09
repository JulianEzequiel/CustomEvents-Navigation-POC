package com.utilities.money.livedatanavigation.navigation.event

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

@Suppress("UNCHECKED_CAST")
class ScopedActions private constructor(
    val key: String,
    val actionsProvider: ActionsProvider,
    val storeScoped: ScopedActionsStore,
    lifecycleOwner: LifecycleOwner?
) : LifecycleObserver {

    init {
        lifecycleOwner?.lifecycle?.addObserver(this)
    }

    companion object {

        internal fun get(key: String, actionsProvider: ActionsProvider, lifecycleOwner: LifecycleOwner): ScopedActions {
            val store = ScopedActionsStore()
            return ScopedActions(key, actionsProvider, store, lifecycleOwner)
        }

    }

    fun <T: Any> get(clazz: Class<T>): T {
        val observer = this.storeScoped.get(clazz)
        if(observer != null) {
            return observer
        }
        return createScopedActions(clazz)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onLifecycleOwnerDestroyed() {
        this.storeScoped.clear()
        this.actionsProvider.removeScopedActions(this.key)
    }

    private fun <T: Any> createScopedActions(clazz: Class<T>): T {
        val observer = clazz.constructors.get(0).newInstance() as T
        this.storeScoped.put(clazz, observer)
        return observer
    }

}