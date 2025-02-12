package abdulrahman.ali19.screens.experience.ui

import abdulrahman.ali19.screens.experience.ui.viewmodel.ExperienceViewModel
import abdulrahman.ali19.screens.experience.ui.viewmodel.model.ExperienceEvents
import abdulrahman.ali19.screens.experience.ui.viewmodel.model.ExperienceItemState
import abdulrahman.ali19.screens.experience.ui.viewmodel.model.ProjectState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import resume.composeapp.generated.resources.projects

@Composable
fun ExperienceMobileScreen(
    modifier: Modifier = Modifier
) {
    val koin = getKoin()
    val viewmodel = remember { koin.get<ExperienceViewModel>() }
    val state by viewmodel.state.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(state.list) {
            ExperienceItem(
                state = it,
                onProjectClick = { project ->
                    viewmodel.sendEvent(ExperienceEvents.OnProjectClick(project))
                }
            )
        }
    }
}

@Composable
private fun ExperienceItem(
    modifier: Modifier = Modifier,
    state: ExperienceItemState,
    onProjectClick: (project: ProjectState) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        ExperienceHeader(state = state, isMobile = true)

        ResponsibilitySection(state = state)

        Text(
            modifier = Modifier.padding(start = 12.dp, top = 18.dp),
            text = stringResource(Res.string.projects),
            style = MaterialTheme.typography.h5.copy(color = Color.White)
        )

        LazyRow(
            userScrollEnabled = true
        ) {
            items(state.projects) {
                ProjectSection(project = it, onProjectClick = onProjectClick)
            }
        }

    }
}