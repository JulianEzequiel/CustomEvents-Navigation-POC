package com.utilities.money.livedatanavigation.navigation.event

import androidx.lifecycle.LifecycleOwner
import com.utilities.money.livedatanavigation.navigation.common.Wizards
import com.utilities.money.livedatanavigation.navigation.common.key

object EventsProvider {

    private val observerProviders = HashMap<String, EventProvider>()

    fun own(wizard: Wizards, lifecycleOwner: LifecycleOwner): EventProvider {
        val key = wizard.key(lifecycleOwner)
        if (observerProviders.containsKey(key)) {
            return observerProviders[key]!!
        }
        return createObserverProvider(key, lifecycleOwner)
    }

    fun of(wizard: Wizards, lifecycleOwner: LifecycleOwner): EventProvider {
        val key = wizard.key(lifecycleOwner)
        if (observerProviders.containsKey(key)) {
            return observerProviders[key]!!
        } else {
            throw IllegalAccessException("You have to OWN the Events First before you can ask for them")
        }
    }

    private fun createObserverProvider(
        key: String,
        lifecycleOwner: LifecycleOwner
    ): EventProvider {
        val observerProvider = EventProvider.get(lifecycleOwner)
        observerProviders.put(key, observerProvider)
        return observerProvider
    }

}