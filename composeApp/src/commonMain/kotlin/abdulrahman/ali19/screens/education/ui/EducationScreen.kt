package abdulrahman.ali19.screens.education.ui

import abdulrahman.ali19.screens.education.ui.viewmodel.EducationViewmodel
import abdulrahman.ali19.screens.education.ui.viewmodel.model.ActivityState
import abdulrahman.ali19.screens.education.ui.viewmodel.model.EducationEvents
import abdulrahman.ali19.screens.education.ui.viewmodel.model.EducationItem
import abdulrahman.ali19.screens.education.ui.viewmodel.model.ProjectState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.getKoin
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.activities
import resume.composeapp.generated.resources.content_copy
import resume.composeapp.generated.resources.projects

@Composable
fun EducationScreen(
    modifier: Modifier = Modifier,
) {
    val koin = getKoin()
    val viewmodel = remember { koin.get<EducationViewmodel>() }
    val state by viewmodel.state.collectAsState()
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth(),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(state.education) {
            EducationItem(
                state = it,
                isMobile = false,
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
    isMobile: Boolean,
    onCopyClick: (uri: String) -> Unit,
    onContactClick: (item: ProjectState) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        EducationHeader(state = state, isMobile = isMobile)

        if (state.isActivesVisible) {
            Text(
                modifier = Modifier.padding(start = 12.dp, top = 8.dp),
                text = stringResource(Res.string.activities),
                style = MaterialTheme.typography.h6.copy(color = Color.White)
            )

            state.activities.forEach {
                ActivitySection(activityState = it)
            }
        }

        if (state.isProjectsVisible) {
            Text(
                modifier = Modifier.padding(start = 12.dp, top = 8.dp),
                text = stringResource(Res.string.projects),
                style = MaterialTheme.typography.h6.copy(color = Color.White)
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


@Composable
fun EducationProjectSection(
    modifier: Modifier = Modifier,
    projectState: ProjectState,
    onCopyClick: (uri: String) -> Unit,
    onContactClick: (item: ProjectState) -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        TextButton(
            onClick = { onContactClick(projectState) },
            content = {
                Column {
                    ProjectHeader(onCopyClick = onCopyClick, projectState = projectState)
                    Text(
                        modifier = Modifier.padding(start = 12.dp),
                        text = projectState.description,
                        style = MaterialTheme.typography.body1.copy(color = Color.White)
                    )
                    projectState.technologies.forEach {
                        Text(
                            modifier = Modifier.padding(start = 12.dp),
                            text = it,
                            style = MaterialTheme.typography.body1.copy(color = Color.White)
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun ProjectHeader(
    modifier: Modifier = Modifier,
    onCopyClick: (uri: String) -> Unit,
    projectState: ProjectState
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = projectState.name,
                style =  MaterialTheme.typography.body1.copy(color = Color.White)
            )
        }

        IconButton(
            onClick = { onCopyClick(projectState.url) },
            content = {
                Icon(
                    painter = painterResource(Res.drawable.content_copy),
                    contentDescription = projectState.name,
                    modifier = Modifier.size(30.dp),
                    tint = Color.Gray
                )
            }
        )
    }
}

@Composable
internal fun ActivitySection(
    activityState: ActivityState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = activityState.title,
            style = MaterialTheme.typography.body1.copy(color = Color.White)
        )

        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = activityState.name,
            style = MaterialTheme.typography.body1.copy(color = Color.White)
        )

        Row(
            modifier = Modifier.padding(start = 12.dp),
        ) {
            Text(
                text = activityState.startDate,
                style = MaterialTheme.typography.body1.copy(color = Color.White)
            )

            Text(
                text = activityState.endDate,
                style = MaterialTheme.typography.body1.copy(color = Color.White)
            )
        }
    }
}

@Composable
internal fun EducationHeader(
    state: EducationItem,
    isMobile: Boolean = false
) {
    Column {
        Text(
            text = state.department,
            style = (if (!isMobile) MaterialTheme.typography.h4 else MaterialTheme.typography.h5)
                .copy(color = Color.White)
        )

        Text(
            text = state.name,
            style = (if (!isMobile) MaterialTheme.typography.h5 else MaterialTheme.typography.h6)
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

        Text(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 6.dp),
            text = state.info,
            style = MaterialTheme.typography.body1.copy(color = Color.White)
        )

    }
}
