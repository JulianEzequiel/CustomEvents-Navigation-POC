package com.utilities.money.livedatanavigation.navigation.event

import androidx.lifecycle.LifecycleOwner
import com.utilities.money.livedatanavigation.navigation.common.Wizards
import com.utilities.money.livedatanavigation.navigation.common.key

object ActionsProvider {

    private val scopedActionsMap = HashMap<String, ScopedActions>()

    fun own(wizard: Wizards, lifecycleOwner: LifecycleOwner): ScopedActions {
        val key = wizard.key(lifecycleOwner)
        if (scopedActionsMap.containsKey(key)) {
            return scopedActionsMap[key]!!
        }
        return createScopedActions(key, lifecycleOwner)
    }

    fun of(wizard: Wizards, lifecycleOwner: LifecycleOwner): ScopedActions {
        val key = wizard.key(lifecycleOwner)
        if (scopedActionsMap.containsKey(key)) {
            return scopedActionsMap[key]!!
        } else {
            throw IllegalAccessException("You have to OWN the Actions Scope before you can get it")
        }
    }

    private fun createScopedActions(
        key: String,
        lifecycleOwner: LifecycleOwner
    ): ScopedActions {
        val observerProvider = ScopedActions.get(lifecycleOwner)
        scopedActionsMap.put(key, observerProvider)
        return observerProvider
    }

}