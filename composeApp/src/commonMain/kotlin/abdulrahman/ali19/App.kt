package abdulrahman.ali19

import abdulrahman.ali19.core.ui.AppTheme
import abdulrahman.ali19.screens.aboutme.ui.AboutMeMobileScreen
import abdulrahman.ali19.screens.aboutme.ui.AboutMeScreen
import abdulrahman.ali19.screens.experience.ui.ExperienceScreen
import abdulrahman.ali19.screens.skills.ui.SkillsScreen
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
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

    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { Tabs.entries.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }
    val state by viewmodel.state.collectAsState()

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

                TabRow(
                    modifier = Modifier.width(500.dp),
                    backgroundColor = Color.Transparent,
                    selectedTabIndex = selectedTabIndex.value,
                    divider = {},
                ) {
                    Tabs.entries.forEachIndexed { index, currentTab ->
                        Tab(
                            selected = selectedTabIndex.value == index,
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(currentTab.ordinal)
                                }
                            },
                            text = { Text(text = currentTab.text) }
                        )
                    }
                }

                HorizontalPager(
                    state = pagerState,
                    userScrollEnabled = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .weight(1f)
                        .padding(
                            horizontal = state.horizontalPadding.dp,
                            vertical = state.verticalPadding.dp
                        ),
                    verticalAlignment = Alignment.Top
                ) {
                    when (Tabs.entries[selectedTabIndex.value]) {
                        Tabs.AboutMe -> if (state.isMobile) AboutMeMobileScreen() else AboutMeScreen()
                        Tabs.Skills -> SkillsScreen()
                        Tabs.Experience -> ExperienceScreen()
                    }
                }
            }
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
