package com.utilities.money.livedatanavigation.navigation.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.utilities.money.livedatanavigation.navigation.common.Wizards
import com.utilities.money.livedatanavigation.navigation.event.EventsProvider
import java.lang.IllegalStateException

inline fun <reified T: Any> FragmentActivity.ownEvents(wizards: Wizards): T =
    EventsProvider.own(wizards, this).get(T::class.java)

inline fun <reified T: Any> Fragment.ownEvents(wizards: Wizards): T =
    EventsProvider.own(wizards, this).get(T::class.java)

inline fun <reified T: Any> Fragment.getObserver(wizards: Wizards): T {
    this.parentFragment?.let {
        return EventsProvider.of(wizards, it).get(T::class.java)
    }

    this.activity?.let {
        return EventsProvider.of(wizards, it).get(T::class.java)
    }

    throw IllegalStateException("Fragment has not Parent Fragment or Activity")
}