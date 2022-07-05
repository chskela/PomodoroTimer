package com.chskela.pomodorotimer.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.chskela.pomodorotimer.presentation.ui.components.button.UIButton
import com.chskela.pomodorotimer.presentation.ui.components.button.UIButtonType
import com.chskela.pomodorotimer.presentation.ui.components.chip.UIChip
import com.chskela.pomodorotimer.presentation.ui.components.spaser.HSpaser
import com.chskela.pomodorotimer.presentation.ui.components.spaser.WSpaser

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PomodoroTimerApp() {
    Scaffold(Modifier.fillMaxSize()) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
            HSpaser()
            UIChip()
            HSpaser()
            Text(text = "15", fontSize = 192.sp, textAlign = TextAlign.Center)
            Text(text = "00", fontSize = 192.sp, textAlign = TextAlign.Center)
            HSpaser()
            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                UIButton()
                WSpaser()
                UIButton(type = UIButtonType.Large)
                WSpaser()
                UIButton()
            }
        }
    }
}