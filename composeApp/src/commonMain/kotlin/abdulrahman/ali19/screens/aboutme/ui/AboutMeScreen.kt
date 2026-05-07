package abdulrahman.ali19.screens.aboutme.ui

import abdulrahman.ali19.core.ui.LoadImage
import abdulrahman.ali19.core.ui.ResumeColors
import abdulrahman.ali19.screens.aboutme.ui.components.ContactUiItem
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.AboutViewModel
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.AboutEvents
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.AboutScreenState
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.AboutTechStackItemState
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.ContactsState
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.PersonalInformationState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.getKoin
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.summary

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AboutMeScreen(
    modifier: Modifier = Modifier,
    isMobile: Boolean
) {
    val koin = getKoin()
    val viewmodel = remember { koin.get<AboutViewModel>() }

    if (isMobile) AboutMeMobileContent(modifier = modifier, viewmodel = viewmodel)
    else AboutMeScreenContent(modifier = modifier, viewmodel = viewmodel)
}

@Composable
fun AboutMeScreenContent(
    viewmodel: AboutViewModel,
    modifier: Modifier = Modifier,
) {
    val state by viewmodel.state.collectAsState()

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            FirstSection(
                state = state,
                modifier = Modifier.weight(1f),
                onCopyClick = { viewmodel.sendEvent(AboutEvents.CopyClickEvent(it)) },
                onContactClick = { viewmodel.sendEvent(AboutEvents.ContactClickEvent(it)) }
            )

            PortraitSection(
                state = state.personalInformationState,
                modifier = Modifier.weight(.9f)
            )

            ThirdSection(
                state = state.personalInformationState,
                modifier = Modifier.weight(1.05f)
            )
        }

        ValuesStrip(
            state = state.personalInformationState,
            modifier = Modifier.widthIn(max = 980.dp)
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ThirdSection(
    modifier: Modifier = Modifier,
    state: PersonalInformationState
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        SectionTitle(text = stringResource(Res.string.summary))

        Text(
            text = state.summary,
            style = MaterialTheme.typography.body1.copy(
                color = ResumeColors.SecondaryText,
                fontWeight = FontWeight.W400
            )
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 250.dp)
                .neonPanel()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(22.dp)
            ) {
                SectionTitle(text = state.techStackTitle, compact = true)
                AboutTechGrid(
                    items = state.techStack,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
            }
        }
    }
}

@Composable
fun FirstSection(
    state: AboutScreenState,
    modifier: Modifier = Modifier,
    onCopyClick: (uri: String) -> Unit,
    onContactClick: (item: ContactsState) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = state.personalInformationState.greeting,
            style = MaterialTheme.typography.h6.copy(
                color = ResumeColors.AccentViolet,
                fontWeight = FontWeight.Bold
            )
        )

        val nameParts = state.personalInformationState.name.split(" ")
        val firstLine = nameParts.take(1).joinToString(" ")
        val secondLine = nameParts.drop(1).joinToString(" ")

        Text(
            text = firstLine,
            style = MaterialTheme.typography.h4.copy(
                color = ResumeColors.PrimaryText,
                fontWeight = FontWeight.Black
            )
        )

        Text(
            text = secondLine,
            style = MaterialTheme.typography.h4.gradientTextStyle(
                fontWeight = FontWeight.Black
            )
        )

        Text(
            text = state.personalInformationState.title,
            modifier = Modifier.padding(top = 14.dp),
            style = MaterialTheme.typography.h6.copy(
                color = ResumeColors.PrimaryText,
                fontWeight = FontWeight.Bold
            )
        )

        NeonDivider(modifier = Modifier.padding(top = 18.dp))

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 46.dp)
                .fillMaxWidth()
                .neonPanel(corner = 12)
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

@OptIn(ExperimentalResourceApi::class)
@Composable
fun PortraitSection(
    state: PersonalInformationState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(26.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(.9f)
                .aspectRatio(1f)
                .border(
                    width = 3.dp,
                    brush = Brush.linearGradient(listOf(ResumeColors.NeonPurple, ResumeColors.NeonBlue)),
                    shape = CircleShape
                )
                .padding(8.dp)
                .clip(CircleShape)
                .background(ResumeColors.PortraitBackground),
            contentAlignment = Alignment.Center
        ) {
            LoadImage(
                modifier = Modifier.fillMaxSize(),
                image = Res.getUri(state.image),
                contentDescription = state.name,
                success = {
                    Image(
                        painter = it.painter,
                        contentDescription = state.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "\"",
                style = MaterialTheme.typography.h2.copy(
                    color = ResumeColors.QuotePurple,
                    fontWeight = FontWeight.Black
                )
            )
            Text(
                text = state.quote,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6.copy(
                    color = ResumeColors.MutedText,
                    fontWeight = FontWeight.Light
                )
            )
            NeonDivider()
        }
    }
}

@Composable
fun ValuesStrip(
    state: PersonalInformationState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .neonPanel(corner = 16)
            .padding(horizontal = 30.dp, vertical = 26.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        state.valueItems.forEachIndexed { index, item ->
            ValueItem(
                icon = item.icon,
                title = item.title,
                body = item.description
            )
            if (index != state.valueItems.lastIndex) {
                VerticalRule()
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ValueItem(icon: String, title: String, body: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.widthIn(min = 220.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(ResumeColors.ValueIconBackground),
            contentAlignment = Alignment.Center
        ) {
            LoadImage(
                image = Res.getUri(icon),
                contentDescription = title,
                modifier = Modifier.size(34.dp),
                success = {
                    Image(
                        painter = it.painter,
                        contentDescription = title,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.body1.copy(
                    color = ResumeColors.PrimaryText,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = body,
                style = MaterialTheme.typography.body2.copy(color = ResumeColors.MutedText)
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TechItem(icon: String, label: String) {
    Column(
        modifier = Modifier.width(82.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LoadImage(
            image = Res.getUri(icon),
            contentDescription = label,
            modifier = Modifier.size(50.dp),
            success = {
                Image(
                    painter = it.painter,
                    contentDescription = label,
                    modifier = Modifier.fillMaxSize()
                )
            }
        )
        Text(
            text = label,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2.copy(
                color = ResumeColors.PrimaryText,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AboutTechGrid(
    items: List<AboutTechStackItemState>,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceEvenly
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        items.forEach { item ->
            TechItem(
                icon = item.icon,
                label = item.label
            )
        }
    }
}

@Composable
fun SectionTitle(text: String, compact: Boolean = false) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            text = text,
            style = if (compact) {
                MaterialTheme.typography.h5.copy(color = ResumeColors.PrimaryText, fontWeight = FontWeight.Bold)
            } else {
                MaterialTheme.typography.h3.copy(color = ResumeColors.PrimaryText, fontWeight = FontWeight.Bold)
            }
        )
        NeonDivider()
    }
}

@Composable
fun NeonDivider(modifier: Modifier = Modifier) {
    Spacer(
        modifier = modifier
            .width(54.dp)
            .height(3.dp)
            .background(Brush.horizontalGradient(listOf(ResumeColors.NeonPurple, ResumeColors.NeonBlue)))
    )
}

@Composable
private fun VerticalRule() {
    Box(
        modifier = Modifier
            .height(62.dp)
            .width(1.dp)
            .background(ResumeColors.Divider)
    )
}

fun Modifier.neonPanel(corner: Int = 18): Modifier =
    clip(RoundedCornerShape(corner.dp))
        .background(
            Brush.linearGradient(
                listOf(
                    ResumeColors.PanelGradientStart,
                    ResumeColors.PanelGradientCenter,
                    ResumeColors.PanelGradientEnd
                )
            )
        )
        .border(
            width = 1.dp,
            brush = Brush.linearGradient(listOf(ResumeColors.PanelBlueBorder, ResumeColors.PanelPinkBorder)),
            shape = RoundedCornerShape(corner.dp)
        )

fun TextStyle.gradientTextStyle(fontWeight: FontWeight): TextStyle =
    copy(
        brush = Brush.horizontalGradient(listOf(ResumeColors.NeonPink, ResumeColors.AccentBlue)),
        fontWeight = fontWeight
    )
