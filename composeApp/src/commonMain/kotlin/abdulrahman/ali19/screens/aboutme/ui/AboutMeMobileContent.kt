package abdulrahman.ali19.screens.aboutme.ui

import abdulrahman.ali19.core.ui.ResumeColors
import abdulrahman.ali19.screens.aboutme.ui.components.ContactUiItem
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.AboutViewModel
import abdulrahman.ali19.screens.aboutme.ui.viewmodel.data.AboutEvents
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.summary

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AboutMeMobileContent(
    viewmodel: AboutViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewmodel.state.collectAsState()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(26.dp)
    ) {
        PortraitSection(
            state = state.personalInformationState,
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 420.dp)
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = state.personalInformationState.greeting,
                style = MaterialTheme.typography.body1.copy(
                    color = ResumeColors.AccentViolet,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = state.personalInformationState.name,
                style = MaterialTheme.typography.h4.gradientTextStyle(
                    fontWeight = FontWeight.Black
                )
            )
            Text(
                text = state.personalInformationState.title,
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.h5.copy(
                    color = ResumeColors.PrimaryText,
                    fontWeight = FontWeight.Bold
                )
            )
            NeonDivider(modifier = Modifier.padding(top = 14.dp))
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            SectionTitle(text = stringResource(Res.string.summary), compact = true)
            Text(
                text = state.personalInformationState.summary,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Light,
                    color = ResumeColors.SecondaryText
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .neonPanel(corner = 16)
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            SectionTitle(text = state.personalInformationState.techStackTitle, compact = true)
            AboutTechGrid(
                items = state.personalInformationState.techStack,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .neonPanel(corner = 12),
            verticalArrangement = Arrangement.Center
        ) {
            state.contacts.forEach {
                ContactUiItem(
                    item = it,
                    onCopyClick = { uri -> viewmodel.sendEvent(AboutEvents.CopyClickEvent(uri)) },
                    onContactClick = { item ->
                        viewmodel.sendEvent(AboutEvents.ContactClickEvent(item))
                    }
                )
            }
        }
    }
}
