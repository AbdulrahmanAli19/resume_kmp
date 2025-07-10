package abdulrahman.ali19.screens.experience.ui

import abdulrahman.ali19.core.ui.LoadImage
import abdulrahman.ali19.screens.experience.ui.viewmodel.ExperienceViewModel
import abdulrahman.ali19.screens.experience.ui.viewmodel.model.ExperienceEvents
import abdulrahman.ali19.screens.experience.ui.viewmodel.model.ExperienceItemState
import abdulrahman.ali19.screens.experience.ui.viewmodel.model.ProjectState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.getKoin
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.projects
import resume.composeapp.generated.resources.responsibilities

@Composable
fun ExperienceScreen(
    modifier: Modifier = Modifier
) {
    val koin = getKoin()
    val viewmodel = remember { koin.get<ExperienceViewModel>() }
    val state by viewmodel.state.collectAsState()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier.fillMaxWidth(),
        verticalItemSpacing = 12.dp,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
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

        ExperienceHeader(state = state)

        ResponsibilitySection(state = state)

        Text(
            modifier = Modifier.padding(start = 12.dp, top = 18.dp),
            text = stringResource(Res.string.projects),
            style = MaterialTheme.typography.h5.copy(color = Color.White)
        )

        LazyRow {
            items(state.projects) {
                ProjectSection(project = it, onProjectClick = onProjectClick)
            }
        }

    }

}

@Composable
internal fun ResponsibilitySection(state: ExperienceItemState) {
    Column {

        Text(
            modifier = Modifier.padding(vertical = 6.dp),
            text = stringResource(Res.string.responsibilities),
            style = MaterialTheme.typography.h6.copy(color = Color.White)
        )

        state.responsibilities.forEach {
            Text(
                modifier = Modifier.padding(start = 22.dp),
                text = it,
                style = MaterialTheme.typography.body1.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Light
                )
            )
        }
    }
}

@Composable
internal fun ExperienceHeader(
    state: ExperienceItemState,
    isMobile: Boolean = false
) {
    Column {
        Text(
            text = state.title,
            style = (if (!isMobile) MaterialTheme.typography.h3 else MaterialTheme.typography.h5)
                .copy(color = Color.White)
        )

        Text(
            text = state.company,
            style = (if (!isMobile) MaterialTheme.typography.h4 else MaterialTheme.typography.h6)
                .copy(color = Color.White)
        )

        Row {
            Text(
                text = state.startDate,
                style = MaterialTheme.typography.body1.copy(color = Color.White)
            )

            Text(
                text = state.endDate,
                style = MaterialTheme.typography.body1.copy(color = Color.White)
            )
        }

        if (state.isInfoVisible)
            Text(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 6.dp),
                text = state.info,
                style = MaterialTheme.typography.body1.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Light
                )
            )

    }
}

@Composable
internal fun ProjectSection(
    project: ProjectState,
    onProjectClick: (project: ProjectState) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(enabled = project.isClickable) {
                onProjectClick(project)
            }
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        ProjectImage(project = project)

        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = project.name,
            style = MaterialTheme.typography.body1.copy(color = Color.White)
        )
    }
}

@Composable
private fun ProjectImage(project: ProjectState) {
    LoadImage(
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(12.dp)),
        image = project.iconUrl,
        contentDescription = project.name,
        loading = {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White),
                content = {
                    CircularProgressIndicator(modifier = Modifier.padding(5.dp))
                }
            )
        },
        error = {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.White),
                content = {
                    Icon(
                        modifier = Modifier.align(Alignment.Center),
                        imageVector = Icons.Filled.Warning,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            )
        },
        success = {
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp)),
                painter = it.painter,
                contentDescription = project.name
            )
        }
    )
}
