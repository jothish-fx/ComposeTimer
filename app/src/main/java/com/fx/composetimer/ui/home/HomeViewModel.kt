package com.fx.composetimer.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val viewModelState = MutableStateFlow(HomeViewModelState())

    // UI state exposed to the UI
    val uiState = viewModelState.map { it.toUiState() }.stateIn(
        viewModelScope, SharingStarted.Eagerly, viewModelState.value.toUiState()
    )

    init {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                updateTime()
                delay(1 * 1000L)
            }
        }
    }


    private fun updateTime() {
        viewModelState.update {
            it.copy(localDateTime = LocalDateTime.now())
        }
    }


    /**Notify that an error was displayed on the screen*/
    fun errorShown(errorMessage: String) {
        viewModelState.update { currentUiState ->
            val errorMessages = currentUiState.errorMessages.filterNot { it == errorMessage }
            currentUiState.copy(errorMessages = errorMessages)
        }
    }
}

/**
 * UI state for the Home.
 *
 * This is derived from [HomeViewModelState], but split into two possible subclasses to more
 * precisely represent the state available to render the UI.
 */
sealed interface HomeUiState {
    val isLoading: Boolean
    val errorMessages: List<String>
    val formattedDate: String
    val isDay: Boolean

    data class HasDate(
        override val isLoading: Boolean,
        override val errorMessages: List<String>,
        override val formattedDate: String,
        override val isDay: Boolean,
    ) : HomeUiState
}

/**
 * An internal representation of the [HomeUiState], in a raw form
 */
data class HomeViewModelState(
    val isLoading: Boolean = false,
    val errorMessages: List<String> = listOf(),
    val localDateTime: LocalDateTime = LocalDateTime.now(),
) {
    /**
     * Converts this [HomeViewModelState] into a more strongly typed [HomeUiState] for driving
     * the ui.
     */

    var formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd")
    fun toUiState(): HomeUiState =

        HomeUiState.HasDate(
            isLoading = isLoading,
            errorMessages = errorMessages,
            formattedDate = localDateTime.format(formatter),
            isDay = localDateTime.hour in 6..17,
        )
}
