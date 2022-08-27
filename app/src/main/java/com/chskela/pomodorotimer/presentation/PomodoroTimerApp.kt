package com.chskela.pomodorotimer.presentation

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chskela.pomodorotimer.presentation.ui.screens.main.MainScreen
import com.chskela.pomodorotimer.presentation.ui.screens.main.MainScreenViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PomodoroTimerApp() {
    Scaffold(Modifier.fillMaxSize()) { _ ->
        val mainScreenViewModel: MainScreenViewModel = viewModel()
        MainScreen(
            mainScreenUiState = mainScreenViewModel.mainScreenUiState.value,
            onEvent = mainScreenViewModel::onEvent
        )
    }
}