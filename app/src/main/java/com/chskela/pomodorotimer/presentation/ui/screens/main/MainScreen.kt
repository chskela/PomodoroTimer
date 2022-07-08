package com.chskela.pomodorotimer.presentation.ui.screens.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.chskela.pomodorotimer.R
import com.chskela.pomodorotimer.presentation.ui.components.button.UIButton
import com.chskela.pomodorotimer.presentation.ui.components.button.UIButtonType
import com.chskela.pomodorotimer.presentation.ui.components.chip.UIChip
import com.chskela.pomodorotimer.presentation.ui.components.spaser.WSpaser
import com.chskela.pomodorotimer.presentation.ui.theme.PomodoroTimerTheme
import com.chskela.pomodorotimer.util.UiText

@Composable
fun MainScreen(mainScreenViewModel: MainScreenViewModel) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (buttons, text, chip) = createRefs()
        UIChip(modifier = Modifier.constrainAs(chip) {
            top.linkTo(parent.top, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        Text(
            modifier = Modifier.constrainAs(text) {
                top.linkTo(chip.bottom)
                bottom.linkTo(buttons.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            text = "${mainScreenViewModel.minutes.value}\n${mainScreenViewModel.seconds.value}",
            style = MaterialTheme.typography.titleLarge
        )

        Row(
            modifier = Modifier.constrainAs(buttons) {
                bottom.linkTo(parent.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            UIButton(
                icon = R.drawable.dots_three_outline,
                backgroundColor = MaterialTheme.colorScheme.secondary,
                description = UiText.StringResource(R.string.dots_three)
            )
            WSpaser()
            UIButton(
                type = UIButtonType.Large,
                onClick = { mainScreenViewModel.startWorkingPeriod() })
            WSpaser()
            UIButton(
                icon = R.drawable.fast_forward,
                backgroundColor = MaterialTheme.colorScheme.secondary,
                description = UiText.StringResource(R.string.fast_forward)
            )

        }
    }
}


@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMainScreen() {
    PomodoroTimerTheme {
        MainScreen(MainScreenViewModel())
    }
}