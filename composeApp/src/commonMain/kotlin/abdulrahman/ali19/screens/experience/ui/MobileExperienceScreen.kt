package abdulrahman.ali19.screens.experience.ui

import abdulrahman.ali19.screens.experience.ui.viewmodel.ExperienceViewModel
import abdulrahman.ali19.screens.experience.ui.viewmodel.model.ExperienceEvents
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExperienceMobileContent(
    viewmodel: ExperienceViewModel,
    modifier: Modifier = Modifier
) {
    val state by viewmodel.state.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        item {
            ExperienceScreenHeader(state = state, compact = true)
        }

        items(state.list) {
            ExperienceCard(
                state = it,
                compact = true,
                onProjectClick = { project ->
                    viewmodel.sendEvent(ExperienceEvents.OnProjectClick(project))
                }
            )
        }

    }
}
