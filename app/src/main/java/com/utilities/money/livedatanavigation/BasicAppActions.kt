package com.utilities.money.livedatanavigation

import androidx.lifecycle.LiveData
import com.utilities.money.livedatanavigation.navigation.common.SingleLiveEvent

class BasicAppActions {

    private val _actionBarTitle =
        SingleLiveEvent<String>()
    val actionBarTitle : LiveData<String> get() = _actionBarTitle


    fun changeActionBarTitle(message: String) {
        _actionBarTitle.value = message
    }

}