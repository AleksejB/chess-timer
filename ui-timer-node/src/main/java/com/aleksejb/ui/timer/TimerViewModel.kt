package com.aleksejb.ui.timer

import com.aleksejb.ui.core.viewmodel.MVIViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(

): MVIViewModel<TimerState, TimerEvent, TimerEffect>() {

    override val _state = MutableStateFlow<TimerState>(TimerState.Initial)

    override fun handleEvent(event: TimerEvent) {
        when (event) {
            else -> {}
        }
    }

}