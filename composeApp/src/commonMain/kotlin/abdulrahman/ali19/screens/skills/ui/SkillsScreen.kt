package abdulrahman.ali19.screens.skills.ui

import abdulrahman.ali19.screens.skills.ui.viewmodel.SkillsViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.koin.compose.getKoin

@Composable
fun SkillsScreen(
    modifier: Modifier = Modifier
) {
    val koin = getKoin()
    val viewmodel = remember { koin.get<SkillsViewModel>() }
    val state by viewmodel.state.collectAsState()

}