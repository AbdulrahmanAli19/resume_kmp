package abdulrahman.ali19.screens.aboutme.ui.components

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
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

@Composable
private fun AsyncIcon(
    modifier: Modifier = Modifier,
    image: DrawableResource?,
    contentDescription: String
) {
    Box(
        modifier = modifier
            .size(50.dp)
            .padding(10.dp)
    ) {
        if (image != null) {
            Icon(
                painter = painterResource(image),
                contentDescription = contentDescription,
                modifier = Modifier.fillMaxSize(),
                tint = Color.Gray
            )
        } else {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = Icons.Filled.Warning,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}