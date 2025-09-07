package abdulrahman.ali19

import abdulrahman.ali19.core.ui.AppTheme
import abdulrahman.ali19.core.ui.tabrow.ResumeTabRow
import abdulrahman.ali19.screens.aboutme.ui.AboutMeScreen
import abdulrahman.ali19.screens.education.ui.EducationScreen
import abdulrahman.ali19.screens.experience.ui.ExperienceScreen
import abdulrahman.ali19.screens.skills.ui.SkillsScreen
import abdulrahman.ali19.screens.welcome.ui.WelcomeScreen
import abdulrahman.ali19.uicomponents.DynamicGradientBackground
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import kotlinx.coroutines.launch
import org.koin.compose.getKoin

@Composable
internal fun App() {
    Napier.base(DebugAntilog("napier js"))

    setupViewport()
    injectStyles()

    val koin = getKoin()
    val viewmodel = remember { koin.get<AppViewModel>() }

    adjustLayout(
        mobileLayout = { viewmodel.sendEvent(AppEvents.UpdateViewPort(isMobile = true)) },
        pcLayout = { viewmodel.sendEvent(AppEvents.UpdateViewPort(isMobile = false)) }
    )

    onResizeLayoutChanged(
        mobileLayout = { viewmodel.sendEvent(AppEvents.UpdateViewPort(isMobile = true)) },
        pcLayout = { viewmodel.sendEvent(AppEvents.UpdateViewPort(isMobile = false)) }
    )

    val state by viewmodel.state.collectAsState()

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = state.selectedTabIndex,
        pageCount = { Tabs.entries.size }
    )

    LaunchedEffect(pagerState.currentPage) {
        viewmodel.sendEvent(AppEvents.SelectTab(pagerState.currentPage))
    }

    AppTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                ResumeTabRow(
                    selectedTabIndex = state.selectedTabIndex,
                    onRowClick = { page ->
                        scope.launch { pagerState.animateScrollToPage(page) }
                    }
                )

                VerticalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth(),
                ) { page ->
                    when (Tabs.entries[page]) {
                        Tabs.AboutMe -> AboutMeScreen(
                            isMobile = state.isMobile,
                            modifier = Modifier.padding(
                                horizontal = state.horizontalPadding.dp,
                                vertical = state.verticalPadding.dp
                            )
                        )

                        Tabs.Skills -> SkillsScreen(
                            isMobile = state.isMobile, modifier = Modifier.padding(
                                horizontal = state.horizontalPadding.dp,
                                vertical = state.verticalPadding.dp
                            )
                        )

                        Tabs.Experience -> ExperienceScreen(
                            isMobile = state.isMobile,
                            modifier = Modifier.padding(
                                horizontal = state.horizontalPadding.dp,
                                vertical = state.verticalPadding.dp
                            )
                        )

                        Tabs.Education -> EducationScreen(
                            isMobile = state.isMobile,
                            modifier = Modifier.padding(
                                horizontal = state.horizontalPadding.dp,
                                vertical = state.verticalPadding.dp
                            )
                        )

                        Tabs.Welcome -> WelcomeScreen()
                    }
                }
            }
            DynamicGradientBackground()
        }
    }
}
