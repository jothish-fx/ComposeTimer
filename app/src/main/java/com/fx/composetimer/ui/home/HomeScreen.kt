package com.fx.composetimer.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fx.composetimer.ui.theme.ComposeTimerTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true) // sets this as the start destination of the default nav graph
@Destination
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    HomeView(state) {
        viewModel.toggleTheme()
    }
}

@Composable
fun HomeView(uiState: HomeUiState, onToggleTheme: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = uiState.formattedDate,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (uiState.isDay) "Today" else "Tonight",
                style = MaterialTheme.typography.displaySmall
            )

            Switch(checked = uiState.isDarkTheme, onCheckedChange = { onToggleTheme.invoke() })

        }


    }

}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    ComposeTimerTheme {
        HomeView(
            uiState = HomeUiState.HasDate(
                isLoading = false,
                errorMessages = listOf(),
                formattedDate = "Wednesday, November 30",
                isDay = false,
                isDarkTheme = false
            )
        ) {}
    }
}