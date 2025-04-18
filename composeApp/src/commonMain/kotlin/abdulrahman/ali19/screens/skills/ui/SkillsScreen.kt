package abdulrahman.ali19.screens.skills.ui

import abdulrahman.ali19.screens.skills.ui.viewmodel.SkillsItemState
import abdulrahman.ali19.screens.skills.ui.viewmodel.SkillsViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.getKoin
import resume.composeapp.generated.resources.Res
import resume.composeapp.generated.resources.soft_skills
import resume.composeapp.generated.resources.software_engineering
import resume.composeapp.generated.resources.technical_skills

private val BACKGROUND_COLOR = Color(0xFF411A1A1A)

@Composable
fun SkillsScreen(
    modifier: Modifier = Modifier,
    isMobile: Boolean
) {
    val koin = getKoin()
    val viewmodel = remember { koin.get<SkillsViewModel>() }
    val state by viewmodel.state.collectAsState()

    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth(),
        columns = GridCells.Adaptive(minSize = if (isMobile) 150.dp else 200.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = stringResource(Res.string.technical_skills),
                style = MaterialTheme.typography.h4.copy(Color.White)
            )
        }

        items(state.technicalSkills) {
            TechnicalSkillItem(it)
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = stringResource(Res.string.software_engineering),
                style = MaterialTheme.typography.h4.copy(Color.White)
            )
        }

        items(state.softwareEngineering) {
            SkillTextItem(text = it)
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Text(
                text = stringResource(Res.string.soft_skills),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp
                )
            )
        }

        items(state.softSkills) {
            SkillTextItem(text = it)
        }
    }


}

@Composable
fun TechnicalSkillItem(it: SkillsItemState) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(BACKGROUND_COLOR)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncIcon(
                image = it.icon,
                contentDescription = it.name,
            )
            Text(
                modifier = Modifier
                    .padding(vertical = 5.dp),
                text = it.name,
                style = MaterialTheme.typography.body1.copy(Color.White),
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {


            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
            ) {

                it.skills.forEach { skill ->
                    Text(
                        modifier = Modifier.padding(top = 5.dp),
                        text = skill,
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun SkillTextItem(
    modifier: Modifier = Modifier,
    text: String
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(BACKGROUND_COLOR)
            .padding(20.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(5.dp)
                .align(Alignment.Center),
            text = text,
            style = TextStyle(
                color = Color.White,
                textAlign = TextAlign.Center
            )
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun AsyncIcon(
    modifier: Modifier = Modifier,
    image: DrawableResource?,
    contentDescription: String
) {

    Box(
        modifier = modifier
            .size(50.dp)
            .padding(10.dp)
    ) {
        if (image != null) {
            Image(
                painter = painterResource(image),
                contentDescription = contentDescription,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = Icons.Filled.Warning,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}