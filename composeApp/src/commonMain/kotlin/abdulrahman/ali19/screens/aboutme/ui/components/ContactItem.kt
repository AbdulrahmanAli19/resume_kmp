package abdulrahman.ali19.screens.aboutme.ui.components

import abdulrahman.ali19.core.ui.LoadImage
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.ContactsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.github.aakira.napier.LogLevel
import io.github.aakira.napier.Napier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.content_copy

@Composable
fun ContactUiItem(
    item: ContactsState,
    onCopyClick: (uri: String) -> Unit,
    onContactClick: (item: ContactsState) -> Unit
) {
    TextButton(
        onClick = { onContactClick(item) },
        content = {
            ContactButton(
                item = item,
                modifier = Modifier.fillMaxWidth(),
                onCopyClick = onCopyClick
            )
        }
    )
}


@Composable
private fun ContactButton(
    item: ContactsState,
    onCopyClick: (uri: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {

            AsyncIcon(
                image = item.icon,
                contentDescription = item.value,
            )

            Text(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .weight(1f, fill = false),
                text = item.value,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body1.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            )
        }

        IconButton(
            enabled = item.isClickable,
            onClick = { onCopyClick(item.url) },
            content = {
                if (item.isCopyable)
                    Icon(
                        painter = painterResource(Res.drawable.content_copy),
                        contentDescription = item.value,
                        modifier = Modifier.size(30.dp),
                        tint = Color.Gray
                    )
            }
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun AsyncIcon(
    modifier: Modifier = Modifier,
    image: String,
    contentDescription: String
) {
    Box(
        modifier = modifier
            .size(50.dp)
            .padding(10.dp)
    ) {
        LoadImage(
            modifier = Modifier.fillMaxSize(),
            image = Res.getUri(image),
            contentDescription = contentDescription,
            success = {
                Napier.log(
                    tag = "WTF",
                    message = "image loaded",
                    priority = LogLevel.INFO
                )
                Icon(
                    painter = it.painter,
                    contentDescription = contentDescription,
                    tint = Color.Gray
                )
            }
        )
    }
}