package com.utilities.money.livedatanavigation.navigation.event

import java.util.*

@Suppress("UNCHECKED_CAST")
class ScopedActionsStore {

    private val mMap = HashMap<String, Any>()

    fun <T> put(clazz: Class<T>, events: Any) {
        val keyName = clazz.canonicalName
        mMap.put(keyName!!, events)
    }

    fun <T : Any> get(clazz: Class<T>): T? {
        val keyName = clazz.canonicalName
        return if(mMap.containsKey(keyName)) {
            return mMap[clazz.canonicalName] as T
        } else {
            null
        }
    }

    fun clear() {
        this.mMap.clear()
    }

}