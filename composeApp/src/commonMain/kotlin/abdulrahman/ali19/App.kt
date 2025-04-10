package abdulrahman.ali19

import abdulrahman.ali19.core.ui.AppTheme
import abdulrahman.ali19.screens.aboutme.ui.AboutMeMobileScreen
import abdulrahman.ali19.screens.aboutme.ui.AboutMeScreen
import abdulrahman.ali19.screens.education.ui.EducationMobileScreen
import abdulrahman.ali19.screens.education.ui.EducationScreen
import abdulrahman.ali19.screens.experience.ui.ExperienceMobileScreen
import abdulrahman.ali19.screens.experience.ui.ExperienceScreen
import abdulrahman.ali19.screens.skills.ui.SkillsScreen
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
            DynamicGradientBackground()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                MyTabRow(
                    selectedTabIndex = state.selectedTabIndex,
                    isMobile = state.isMobile,
                    onRowClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(it)
                        }
                    }
                )

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalAlignment = Alignment.Top
                ) {page->
                    when (Tabs.entries[page]) {
                        Tabs.AboutMe -> if (state.isMobile) AboutMeMobileScreen(
                            modifier = Modifier.padding(
                                horizontal = state.horizontalPadding.dp,
                                vertical = state.verticalPadding.dp
                            )
                        ) else AboutMeScreen(
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

                        Tabs.Experience -> if (state.isMobile) ExperienceMobileScreen(
                            modifier = Modifier.padding(
                                horizontal = state.horizontalPadding.dp,
                                vertical = state.verticalPadding.dp
                            )
                        ) else ExperienceScreen(
                            modifier = Modifier.padding(
                                horizontal = state.horizontalPadding.dp,
                                vertical = state.verticalPadding.dp
                            )
                        )

                        Tabs.Education -> if (state.isMobile) EducationMobileScreen(
                            modifier = Modifier.padding(
                                horizontal = state.horizontalPadding.dp,
                                vertical = state.verticalPadding.dp
                            )
                        ) else EducationScreen(
                            modifier = Modifier.padding(
                                horizontal = state.horizontalPadding.dp,
                                vertical = state.verticalPadding.dp
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MyTabRow(
    selectedTabIndex: Int,
    onRowClick: (Int) -> Unit,
    isMobile: Boolean
) {
    if (isMobile)
        ScrollableTabRow(
            backgroundColor = Color.Transparent,
            selectedTabIndex = selectedTabIndex,
            divider = {},
            edgePadding = 12.dp
        ) {
            Tabs.entries.forEachIndexed { index, currentTab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { onRowClick(currentTab.ordinal) },
                    text = { Text(text = currentTab.text) }
                )
            }
        }
    else
        TabRow(
            modifier = Modifier.width(500.dp),
            backgroundColor = Color.Transparent,
            selectedTabIndex = selectedTabIndex,
            divider = {},
        ) {
            Tabs.entries.forEachIndexed { index, currentTab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { onRowClick(currentTab.ordinal) },
                    text = { Text(text = currentTab.text) }
                )
            }
        }
}

@Composable
private fun DynamicGradientBackground(
    modifier: Modifier = Modifier
) {

    val gradientColors = listOf(
        Color(0xFF8E44AD),
        Color(0xFF3498DB),
        Color(0xFF2C3E50),
        Color.Black
    )

    val infiniteTransition = rememberInfiniteTransition(label = "GradientTransition")

    val animatedColor by infiniteTransition.animateColor(
        initialValue = gradientColors[0],
        targetValue = gradientColors.last(),
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 9000
                0f to gradientColors[0]
                1000f to gradientColors[1]
                2000f to gradientColors[2]
                3000f to gradientColors[0]
            },
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(
                Brush.verticalGradient(colors = listOf(animatedColor, gradientColors.last()))
            )
    )
}
