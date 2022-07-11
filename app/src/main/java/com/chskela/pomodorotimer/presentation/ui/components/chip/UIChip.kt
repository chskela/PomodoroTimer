package com.chskela.pomodorotimer.presentation.ui.components.chip

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.pomodorotimer.R
import com.chskela.pomodorotimer.presentation.ui.theme.PomodoroTimerTheme
import com.chskela.pomodorotimer.util.UiText

@Composable
fun UIChip(
    modifier: Modifier = Modifier,
    content: ChipUiState = ChipUiState(),
    description: UiText = UiText.StringResource(R.string.play),
    contentColor: Color = MaterialTheme.colorScheme.onSecondary,
    backgroundColor: Color = MaterialTheme.colorScheme.secondary,
    borderColor: Color = MaterialTheme.colorScheme.outline,
) {
    Row(
        modifier = modifier
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(50)
            )
            .background(color = backgroundColor, shape = RoundedCornerShape(50))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = content.icon),
            contentDescription = description.asString(),
            tint = contentColor
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = content.title.asString(),
            color = contentColor,
            style = MaterialTheme.typography.titleMedium
        )
    }
}


@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewUIChip() {
    PomodoroTimerTheme {
        Surface {
            UIChip()
        }
    }
}


