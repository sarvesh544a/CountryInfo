package com.kodeco.android.countryinfo.flow

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

object Flows {

    // MutableStateFlow for tapFlow
    private val _tapFlow = MutableStateFlow(0)
    val tapFlow: StateFlow<Int> = _tapFlow

    // MutableStateFlow for backFlow
    private val _backFlow = MutableStateFlow(0)
    val backFlow: StateFlow<Int> = _backFlow

    // MutableStateFlow for counterFlow
    private val _counterFlow = MutableStateFlow(0)
    val counterFlow: StateFlow<Int> = _counterFlow

    init {
        // Launch coroutine to auto-increment counterFlow every second
        GlobalScope.launch {
            var counter = 0
            while (true) {
                delay(1000)
                counter++
                _counterFlow.value = counter
            }
        }
    }

    // Public method to update tapFlow
    fun tap() {
        _tapFlow.value++
    }

    // Public method to update backFlow
    fun tapBack() {
        _backFlow.value++
    }
}
