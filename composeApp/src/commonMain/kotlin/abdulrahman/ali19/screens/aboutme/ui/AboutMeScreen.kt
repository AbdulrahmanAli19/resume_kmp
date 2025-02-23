package abdulrahman.ali19.screens.aboutme.ui

import abdulrahman.ali19.core.ui.LoadImage
import abdulrahman.ali19.screens.aboutme.ui.components.AnimatedImage
import abdulrahman.ali19.screens.aboutme.ui.components.ContactUiItem
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.AboutViewModel
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.AboutEvents
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.AboutScreenState
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.ContactsState
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.PersonalInformationState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.getKoin
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.android
import resume.composeapp.generated.resources.bug
import resume.composeapp.generated.resources.database
import resume.composeapp.generated.resources.firebase
import resume.composeapp.generated.resources.kotlin
import resume.composeapp.generated.resources.maintenance
import resume.composeapp.generated.resources.mobile
import resume.composeapp.generated.resources.summary

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AboutMeScreen(
    modifier: Modifier = Modifier
) {
    val koin = getKoin()
    val viewmodel = remember { koin.get<AboutViewModel>() }
    val state by viewmodel.state.collectAsState()

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top
    ) {

        Box(
            modifier = Modifier
                .weight(.8f),
            contentAlignment = Alignment.Center
        ) {
            FirstSection(
                state = state,
                onCopyClick = { viewmodel.sendEvent(AboutEvents.CopyClickEvent(it)) },
                onContactClick = { viewmodel.sendEvent(AboutEvents.ContactClickEvent(it)) }
            )
        }

        Box(
            modifier = Modifier
                .weight(.5f),
            contentAlignment = Alignment.Center
        ) {
            LoadImage(
                modifier = Modifier
                    .size(500.dp)
                    .padding(bottom = 50.dp),
                image = Res.getUri(state.personalInformationState.image),
                contentDescription = state.personalInformationState.name,
            )
        }

        Box(
            modifier = Modifier
                .weight(.8f),
            contentAlignment = Alignment.Center
        ) {
            ThirdSection(state = state.personalInformationState)
        }

    }
}

@Composable
fun ThirdSection(
    modifier: Modifier = Modifier,
    state: PersonalInformationState
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = stringResource(Res.string.summary),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.h2.copy(color = Color.White)
        )

        Text(
            text = state.summary,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.h6.copy(
                color = Color.White,
                fontWeight = FontWeight.Light
            )
        )

        Box(
            modifier = Modifier
                .height(270.dp)
                .padding(top = 50.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(end = 10.dp)
                    ) {
                        AnimatedImage(
                            modifier = Modifier
                                .size(50.dp),
                            drawableImage = Res.drawable.maintenance,
                            contentDescription = Res.string.summary
                        )
                        AnimatedImage(
                            modifier = Modifier
                                .padding(end = 15.dp)
                                .size(50.dp),
                            drawableImage = Res.drawable.firebase,
                            contentDescription = Res.string.summary
                        )
                        AnimatedImage(
                            modifier = Modifier
                                .size(40.dp),
                            drawableImage = Res.drawable.database,
                            contentDescription = Res.string.summary
                        )
                    }

                    Image(
                        modifier = Modifier
                            .size(150.dp),
                        painter = painterResource(Res.drawable.android),
                        contentDescription = stringResource(Res.string.summary),
                    )

                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(end = 10.dp)
                    ) {
                        AnimatedImage(
                            modifier = Modifier
                                .size(50.dp),
                            drawableImage = Res.drawable.mobile,
                            contentDescription = Res.string.summary
                        )
                        AnimatedImage(
                            modifier = Modifier
                                .padding(start = 15.dp)
                                .size(50.dp),
                            drawableImage = Res.drawable.bug,
                            contentDescription = Res.string.summary
                        )
                        AnimatedImage(
                            modifier = Modifier
                                .size(40.dp),
                            drawableImage = Res.drawable.kotlin,
                            contentDescription = Res.string.summary
                        )
                    }
                }

            }
        }
    }
}


@Composable
fun FirstSection(
    state: AboutScreenState,
    onCopyClick: (uri: String) -> Unit,
    onContactClick: (item: ContactsState) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = state.personalInformationState.name,
            style = MaterialTheme.typography.h2.copy(color = Color.White)
        )

        Text(
            text = state.personalInformationState.title,
            style = MaterialTheme.typography.h4.copy(color = Color.White)
        )

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(top = 30.dp)
        ) {

            state.contacts.forEach {
                ContactUiItem(
                    item = it,
                    onCopyClick = onCopyClick,
                    onContactClick = onContactClick
                )
            }
        }
    }
}

