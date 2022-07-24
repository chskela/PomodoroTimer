package com.chskela.pomodorotimer.presentation.ui.components.button

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.chskela.pomodorotimer.R
import com.chskela.pomodorotimer.presentation.ui.theme.PomodoroTheme
import com.chskela.pomodorotimer.util.UiText

@Composable
fun UIButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    iconColor: Color = MaterialTheme.colorScheme.onPrimary,
    @DrawableRes icon: Int = R.drawable.play,
    description: UiText = UiText.StringResource(R.string.play),
    type: UIButtonType = UIButtonType.Medium,
    onClick: () -> Unit = {},
) {

    Button(
        onClick = onClick,
        modifier = modifier
            .height(type.height)
            .width(type.width),
        shape = RoundedCornerShape(type.radius),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = description.asString(),
            tint = iconColor
        )
    }
}


@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewUIButton() {
    PomodoroTheme {
        Surface {
            UIButton()
        }
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewUIButtonLarge() {
    PomodoroTheme {
        Surface {
            UIButton(type = UIButtonType.Large)
        }
    }
}

