package com.chskela.pomodorotimer.presentation.ui.screens.main

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.chskela.pomodorotimer.R
import com.chskela.pomodorotimer.presentation.ui.components.button.UIButton
import com.chskela.pomodorotimer.presentation.ui.components.button.UIButtonType
import com.chskela.pomodorotimer.presentation.ui.components.chip.ChipUiState
import com.chskela.pomodorotimer.presentation.ui.components.chip.UIChip
import com.chskela.pomodorotimer.presentation.ui.components.spaser.WSpaser
import com.chskela.pomodorotimer.presentation.ui.theme.PomodoroColorScheme
import com.chskela.pomodorotimer.presentation.ui.theme.PomodoroTheme
import com.chskela.pomodorotimer.util.UiText

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(mainScreenViewModel: MainScreenViewModel) {
    val textFontWent: Int by animateIntAsState(
        targetValue = if (mainScreenViewModel.isRunnable.value) 400 else 700
    )
    val colorScheme = when (mainScreenViewModel.pomodoroState.value) {
        PomodoroState.Focus -> PomodoroColorScheme.RedColorScheme
        PomodoroState.LongBreak -> PomodoroColorScheme.BlueColorScheme
        PomodoroState.ShortBreak -> PomodoroColorScheme.GreenColorScheme
    }

    PomodoroTheme(
        darkColorScheme = colorScheme.darkColorScheme,
        lightColorScheme = colorScheme.lightColorScheme
    ) {
        Surface {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val (buttons, text, chip) = createRefs()

                AnimatedContent(modifier = Modifier.constrainAs(chip) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, targetState = mainScreenViewModel.pomodoroState.value) { pomodoroState ->

                    val chipUiState: ChipUiState = when (pomodoroState) {
                        PomodoroState.Focus -> ChipUiState()
                        PomodoroState.LongBreak -> ChipUiState(
                            title = UiText.StringResource(R.string.long_break),
                            icon = R.drawable.coffer
                        )
                        PomodoroState.ShortBreak -> ChipUiState(
                            title = UiText.StringResource(R.string.short_break),
                            icon = R.drawable.coffer
                        )
                    }

                    UIChip(content = chipUiState)
                }

                Text(
                    modifier = Modifier.constrainAs(text) {
                        top.linkTo(chip.bottom)
                        bottom.linkTo(buttons.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    text = "${mainScreenViewModel.minutes.value}\n${mainScreenViewModel.seconds.value}",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight(textFontWent)
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Row(
                    modifier = Modifier.constrainAs(buttons) {
                        bottom.linkTo(parent.bottom, margin = 24.dp)
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

                    if (mainScreenViewModel.isRunnable.value) {
                        UIButton(
                            type = UIButtonType.Large,
                            onClick = { mainScreenViewModel.onEvent(MainScreenEvent.OnStart) }
                        )
                    } else {
                        UIButton(
                            type = UIButtonType.Large,
                            icon = R.drawable.pause,
                            onClick = { mainScreenViewModel.onEvent(MainScreenEvent.OnPause) }
                        )
                    }

                    WSpaser()
                    UIButton(
                        icon = R.drawable.fast_forward,
                        backgroundColor = MaterialTheme.colorScheme.secondary,
                        description = UiText.StringResource(R.string.fast_forward)
                    )

                }
            }
        }
    }

}


@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMainScreen() {
    MainScreen(MainScreenViewModel())
}