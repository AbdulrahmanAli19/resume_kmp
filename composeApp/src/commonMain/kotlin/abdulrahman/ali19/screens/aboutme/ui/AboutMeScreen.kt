package abdulrahman.ali19.screens.aboutme.ui

import abdulrahman.ali19.core.ui.LoadImage
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.AboutViewModel
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.AboutEvents
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.AboutScreenState
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.ContactsState
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.PersonalInformationState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
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
                .weight(1f),
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
                .weight(1f),
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
                .weight(1f),
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
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 42.sp
            )
        )

        Text(
            text = state.summary,
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontWeight = FontWeight.Thin,
                fontSize = 22.sp
            )
        )

        Box(
            modifier = Modifier
                .height(270.dp)
                .padding(top = 20.dp)
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
fun AnimatedImage(
    modifier: Modifier,
    drawableImage: DrawableResource,
    contentDescription: StringResource
) {
    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Image(
        modifier = modifier
            .graphicsLayer(scaleX = scale, scaleY = scale),
        painter = painterResource(drawableImage),
        contentDescription = stringResource(contentDescription),
    )
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
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 42.sp
            )
        )

        Text(
            text = state.personalInformationState.title,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 32.sp
            )
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
