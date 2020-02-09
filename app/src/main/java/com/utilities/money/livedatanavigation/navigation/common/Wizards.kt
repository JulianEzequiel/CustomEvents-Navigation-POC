package com.utilities.money.livedatanavigation.navigation.common

import androidx.lifecycle.LifecycleOwner

enum class Wizards {

    APPLICATION,
    WIZARD_1,
    WIZARD_2

}

fun Wizards.key(lifecycleOwner: LifecycleOwner): String {
    return "${this.javaClass.canonicalName} - $lifecycleOwner"
}