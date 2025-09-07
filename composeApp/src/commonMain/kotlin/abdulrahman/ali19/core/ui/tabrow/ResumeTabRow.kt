package abdulrahman.ali19.core.ui.tabrow

import abdulrahman.ali19.Tabs
import abdulrahman.ali19.core.ui.joyPixelsFontFamily
import abdulrahman.ali19.core.ui.tekoFontFamily
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ResumeTabRow(
    selectedTabIndex: Int,
    onRowClick: (Int) -> Unit,
) {
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
                text = {
                    Text(
                        text = currentTab.text,
                        fontFamily = if (index == 0) joyPixelsFontFamily() else tekoFontFamily()
                    )
                }
            )
        }
    }

}