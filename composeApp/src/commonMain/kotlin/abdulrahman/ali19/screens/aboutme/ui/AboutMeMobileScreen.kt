package abdulrahman.ali19.screens.aboutme.ui

import abdulrahman.ali19.core.ui.LoadImage
import abdulrahman.ali19.screens.aboutme.ui.components.ContactUiItem
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.AboutViewModel
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.AboutEvents
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.getKoin
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.contact
import resume.composeapp.generated.resources.summary


@OptIn(ExperimentalResourceApi::class)
@Composable
fun AboutMeMobileScreen(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val koin = getKoin()
    val viewmodel = remember { koin.get<AboutViewModel>() }
    val state by viewmodel.state.collectAsState()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        LoadImage(
            modifier = Modifier
                .size(400.dp)
                .padding(bottom = 50.dp, top = 16.dp),
            image = Res.getUri(state.personalInformationState.image),
            contentDescription = stringResource(Res.string.summary),
        )

        Text(
            text = state.personalInformationState.name,
            style = MaterialTheme.typography.h4.copy(
                color = Color.White
            )
        )

        Text(
            text = state.personalInformationState.title,
            style = MaterialTheme.typography.h5.copy(
                color = Color.White
            )
        )

        Text(
            text = stringResource(Res.string.summary),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.h5.copy(
                color = Color.White
            )
        )

        Text(
            text = state.personalInformationState.summary,
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Light,
                color = Color.White
            )
        )


        Text(
            text = stringResource(Res.string.contact),
            modifier = Modifier
                .padding(top = 14.dp)
                .fillMaxWidth(),
            style = MaterialTheme.typography.h5.copy(
                color = Color.White
            )
        )

        state.contacts.forEach {
            ContactUiItem(
                item = it,
                onCopyClick = { uri -> viewmodel.sendEvent(AboutEvents.CopyClickEvent(uri)) },
                onContactClick = { item -> viewmodel.sendEvent(AboutEvents.ContactClickEvent(item)) }
            )
        }

    }
}

