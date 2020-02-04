package com.utilities.money.livedatanavigation.navigation.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.utilities.money.livedatanavigation.navigation.common.Wizards
import com.utilities.money.livedatanavigation.navigation.event.ActionsProvider
import java.lang.IllegalStateException

inline fun <reified T: Any> FragmentActivity.ownActionsScope(wizards: Wizards): T =
    ActionsProvider.own(wizards, this).get(T::class.java)

inline fun <reified T: Any> Fragment.ownActionsScope(wizards: Wizards): T =
    ActionsProvider.own(wizards, this).get(T::class.java)

inline fun <reified T: Any> Fragment.getScopedActions(wizards: Wizards): T {
    this.parentFragment?.let {
        return ActionsProvider.of(wizards, it).get(T::class.java)
    }

    this.activity?.let {
        return ActionsProvider.of(wizards, it).get(T::class.java)
    }

    throw IllegalStateException("Fragment has not Parent Fragment or Activity")
}