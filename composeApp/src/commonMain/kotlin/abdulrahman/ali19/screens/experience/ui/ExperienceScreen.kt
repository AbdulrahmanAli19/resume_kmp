package abdulrahman.ali19.screens.experience.ui

import abdulrahman.ali19.core.ui.LoadImage
import abdulrahman.ali19.core.ui.ResumeColors
import abdulrahman.ali19.screens.experience.ui.viewmodel.ExperienceViewModel
import abdulrahman.ali19.screens.experience.ui.viewmodel.model.ExperienceEvents
import abdulrahman.ali19.screens.experience.ui.viewmodel.model.ExperienceItemState
import abdulrahman.ali19.screens.experience.ui.viewmodel.model.ProjectState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.getKoin
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.projects
import resume.composeapp.generated.resources.responsibilities

@Composable
fun ExperienceScreen(
    isMobile: Boolean,
    modifier: Modifier = Modifier
) {
    val koin = getKoin()
    val viewmodel = remember { koin.get<ExperienceViewModel>() }

    if (isMobile) ExperienceMobileContent(viewmodel = viewmodel, modifier = modifier)
    else ExperienceContent(viewmodel = viewmodel, modifier = modifier)
}

@Composable
fun ExperienceContent(
    viewmodel: ExperienceViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewmodel.state.collectAsState()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = modifier.fillMaxWidth(),
        verticalItemSpacing = 12.dp,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item(span = StaggeredGridItemSpan.FullLine) {
            ExperienceScreenHeader(
                title = state.screenTitle,
                screenSubtitle = state.screenSubtitle,
            )
        }

        items(state.list) {
            ExperienceCard(
                state = it,
                onProjectClick = { project ->
                    viewmodel.sendEvent(ExperienceEvents.OnProjectClick(project))
                }
            )
        }
    }
}

@Composable
internal fun ExperienceScreenHeader(
    title: String,
    screenSubtitle: String,
    modifier: Modifier = Modifier,
    compact: Boolean = false
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = (if (compact) MaterialTheme.typography.h3 else MaterialTheme.typography.h2)
                .experienceGradientTextStyle(fontWeight = FontWeight.Black)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = screenSubtitle,
                style = MaterialTheme.typography.body1.copy(color = ResumeColors.PrimaryText)
            )
            Spacer(modifier = Modifier.width(10.dp))
            ExperienceNeonDivider()
        }
    }
}

@Composable
internal fun ExperienceCard(
    modifier: Modifier = Modifier,
    state: ExperienceItemState,
    compact: Boolean = false,
    onProjectClick: (project: ProjectState) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .experiencePanel()
            .padding(if (compact) 18.dp else 26.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        ExperienceHeader(state = state, isMobile = compact)
        ResponsibilitySection(state = state)
        ProjectListSection(state = state, onProjectClick = onProjectClick)
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun ExperienceHeader(
    state: ExperienceItemState,
    isMobile: Boolean = false
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.Top
        ) {
            ExperienceRoleIcon(
                icon = state.icon,
                contentDescription = state.title,
                size = if (isMobile) 66 else 76
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = state.title,
                    style = MaterialTheme.typography.h6.copy(
                        color = ResumeColors.PrimaryText,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = state.company,
                    style = MaterialTheme.typography.body1.copy(
                        color = ResumeColors.NeonPink,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "${state.startDate} - ${state.endDate}",
                    style = MaterialTheme.typography.body1.copy(color = ResumeColors.AccentBlue)
                )
                if (isMobile && state.isCurrent) {
                    CurrentBadge(text = state.endDate)
                }
            }
        }


        if (!isMobile && state.isCurrent) {
            CurrentBadge(
                text = state.endDate,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun ExperienceRoleIcon(
    icon: String,
    contentDescription: String,
    size: Int
) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(ResumeColors.PanelGradientStart)
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    listOf(
                        ResumeColors.NeonPurple,
                        ResumeColors.NeonBlue
                    )
                ),
                shape = RoundedCornerShape(14.dp)
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        LoadImage(
            image = Res.getUri(icon),
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            success = {
                Image(
                    painter = it.painter,
                    contentDescription = contentDescription,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        )
    }
}

@Composable
private fun CurrentBadge(
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(18.dp))
            .background(ResumeColors.PanelGradientCenter)
            .border(
                width = 1.dp,
                color = ResumeColors.ContactDivider,
                shape = RoundedCornerShape(18.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(ResumeColors.SuccessGreen)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.body2.copy(
                color = ResumeColors.PrimaryText,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
internal fun ResponsibilitySection(state: ExperienceItemState) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        SectionLabel(text = stringResource(Res.string.responsibilities))

        state.responsibilities.forEach {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(ResumeColors.NeonPurple)
                )
                Text(
                    text = it,
                    style = MaterialTheme.typography.body2.copy(
                        color = ResumeColors.SecondaryText,
                        fontWeight = FontWeight.Light
                    )
                )
            }
        }
    }
}

@Composable
private fun ProjectListSection(
    state: ExperienceItemState,
    onProjectClick: (project: ProjectState) -> Unit
) {
    if (state.projects.isEmpty()) return

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(ResumeColors.Divider)
        )

        Text(
            text = stringResource(Res.string.projects),
            style = MaterialTheme.typography.body1.copy(
                color = ResumeColors.PrimaryText,
                fontWeight = FontWeight.Bold
            )
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            userScrollEnabled = true
        ) {
            items(state.projects) {
                ProjectSection(project = it, onProjectClick = onProjectClick)
            }
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ExperienceNeonDivider(modifier = Modifier.width(4.dp).height(14.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.body2.copy(
                color = ResumeColors.PrimaryText,
                fontWeight = FontWeight.Bold
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
            .clip(RoundedCornerShape(8.dp))
            .clickable(enabled = project.isClickable) {
                onProjectClick(project)
            }
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProjectImage(project = project)

        Text(
            modifier = Modifier.padding(start = 12.dp),
            text = project.name,
            style = MaterialTheme.typography.body2.copy(color = ResumeColors.PrimaryText)
        )
    }
}

@Composable
private fun ProjectImage(project: ProjectState) {
    LoadImage(
        modifier = Modifier
            .size(42.dp)
            .clip(RoundedCornerShape(8.dp)),
        image = project.iconUrl,
        contentDescription = project.name,
        loading = {
            ProjectImageFallback {
                CircularProgressIndicator(modifier = Modifier.padding(7.dp))
            }
        },
        error = {
            ProjectImageFallback {
                Icon(
                    modifier = Modifier.align(Alignment.Center),
                    imageVector = Icons.Filled.Warning,
                    contentDescription = null,
                    tint = ResumeColors.ProjectFallbackIcon
                )
            }
        },
        success = {
            Image(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(8.dp)),
                painter = it.painter,
                contentDescription = project.name,
                contentScale = ContentScale.Crop
            )
        }
    )
}

@Composable
private fun ProjectImageFallback(content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = Modifier
            .size(42.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(ResumeColors.ProjectTileBackground),
        content = content
    )
}

@Composable
internal fun ExperienceNeonDivider(modifier: Modifier = Modifier) {
    Spacer(
        modifier = modifier
            .width(44.dp)
            .height(3.dp)
            .background(
                Brush.horizontalGradient(
                    listOf(
                        ResumeColors.NeonPurple,
                        ResumeColors.NeonBlue
                    )
                )
            )
    )
}

fun Modifier.experiencePanel(corner: Int = 16): Modifier =
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
            brush = Brush.linearGradient(
                listOf(
                    ResumeColors.PanelPinkBorder,
                    ResumeColors.PanelBlueBorder
                )
            ),
            shape = RoundedCornerShape(corner.dp)
        )

private fun TextStyle.experienceGradientTextStyle(fontWeight: FontWeight): TextStyle =
    copy(
        brush = Brush.horizontalGradient(listOf(ResumeColors.NeonPink, ResumeColors.AccentBlue)),
        fontWeight = fontWeight
    )
