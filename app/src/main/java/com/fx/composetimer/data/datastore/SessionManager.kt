package com.fx.composetimer.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.fx.composetimer.data.utils.isDarkTheme
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val DATA_STORE_NAME = "CTSessionManager"
val Context.sessionManDataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

class SessionManager @Inject constructor(@ApplicationContext private val context: Context) {

    private val dataStore = context.sessionManDataStore

    // Create some keys we will use them to store and retrieve the data
    companion object {
        val IS_DARK_MODE = booleanPreferencesKey("isDarkMode")
    }

    val isDarkMode: Flow<Boolean> = dataStore.data.map { it[IS_DARK_MODE] ?: context.isDarkTheme }
    suspend fun setIsDarkMode(isDarkMode: Boolean) {
        dataStore.edit {
            it[IS_DARK_MODE] = isDarkMode
        }
    }

    suspend fun endSession() {
        dataStore.edit {
            it.clear()
        }
    }


}