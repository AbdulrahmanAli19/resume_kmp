package abdulrahman.ali19.screens.education.ui

import abdulrahman.ali19.screens.education.ui.viewmodel.EducationViewmodel
import abdulrahman.ali19.screens.education.ui.viewmodel.model.EducationEvents
import abdulrahman.ali19.screens.education.ui.viewmodel.model.EducationItem
import abdulrahman.ali19.screens.education.ui.viewmodel.model.ProjectState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.getKoin
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.activities
import resume.composeapp.generated.resources.projects

@Composable
fun EducationMobileScreen(
    modifier: Modifier = Modifier
) {
    val koin = getKoin()
    val viewmodel = remember { koin.get<EducationViewmodel>() }
    val state by viewmodel.state.collectAsState()
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(state.education) {
            EducationItem(
                state = it,
                onContactClick = { projectState ->
                    viewmodel.sendEvent(EducationEvents.OpenNewWindow(projectState.url))
                },
                onCopyClick = { link ->
                    viewmodel.sendEvent(EducationEvents.CopyLink(link))
                }
            )
        }
    }
}

@Composable
private fun EducationItem(
    modifier: Modifier = Modifier,
    state: EducationItem,
    onCopyClick: (uri: String) -> Unit,
    onContactClick: (item: ProjectState) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        EducationHeader(state = state, isMobile = true)

        if (state.isActivesVisible) {
            Text(
                modifier = Modifier.padding(start = 12.dp, top = 18.dp),
                text = stringResource(Res.string.activities),
                style = MaterialTheme.typography.h5.copy(color = Color.White)
            )

            state.activities.forEach {
                ActivitySection(activityState = it)
            }
        }

        if (state.isProjectsVisible) {
            Text(
                modifier = Modifier.padding(start = 12.dp, top = 18.dp),
                text = stringResource(Res.string.projects),
                style = MaterialTheme.typography.h5.copy(color = Color.White)
            )

            state.projects.forEach {
                EducationProjectSection(
                    projectState = it,
                    onCopyClick = onCopyClick,
                    onContactClick = onContactClick
                )
            }
        }
    }
}