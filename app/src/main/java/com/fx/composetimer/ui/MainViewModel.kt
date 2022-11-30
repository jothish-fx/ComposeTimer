package com.fx.composetimer.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fx.composetimer.data.datastore.SessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    sessionManager: SessionManager
) : ViewModel() {

    var isDarkTheme by mutableStateOf(false)

    init {
        viewModelScope.launch {
            sessionManager.isDarkMode.distinctUntilChanged().collect {
                isDarkTheme = it
            }
        }
    }
}