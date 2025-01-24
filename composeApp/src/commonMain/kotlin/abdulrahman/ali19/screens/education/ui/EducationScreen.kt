package abdulrahman.ali19.screens.education.ui

import abdulrahman.ali19.screens.education.ui.viewmodel.EducationViewmodel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.koin.compose.getKoin

@Composable
fun EducationScreen(
    modifier: Modifier = Modifier
) {
    val koin = getKoin()
    val viewmodel = remember { koin.get<EducationViewmodel>() }
    val state by viewmodel.state.collectAsState()
}