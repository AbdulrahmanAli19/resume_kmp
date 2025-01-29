package abdulrahman.ali19.screens.experience.ui

import abdulrahman.ali19.screens.experience.ui.viewmodel.ExperienceViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.koin.compose.getKoin

@Composable
fun ExperienceScreen(
    modifier: Modifier = Modifier
) {
    val koin = getKoin()
    val viewmodel = remember { koin.get<ExperienceViewModel>() }
    val state by viewmodel.state.collectAsState()

}