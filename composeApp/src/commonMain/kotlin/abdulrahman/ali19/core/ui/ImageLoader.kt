package abdulrahman.ali19.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import io.github.aakira.napier.LogLevel
import io.github.aakira.napier.Napier

@Composable
fun LoadImage(
    modifier: Modifier = Modifier,
    image: Any,
    contentDescription: String,
    loading: @Composable () -> Unit = { DefaultLoading() },
    error: @Composable (AsyncImagePainter.State.Error) -> Unit = { DefaultError(it) },
    success: @Composable (AsyncImagePainter.State.Success) -> Unit = { result ->
        DefaultSuccess(result = result, contentDescription = contentDescription)
    }
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = image,
        loading = {
            loading()
        },
        error = {
            error(it)
            Napier.log(
                tag = "coil failure",
                throwable = it.result.throwable,
                message = "Fail to fetch",
                priority = LogLevel.ERROR
            )
        },
        success = {
            success(it)
        },
        contentDescription = contentDescription
    )
}

@Composable
private fun DefaultLoading() {
    CircularProgressIndicator(modifier = Modifier.size(10.dp))
}

@Composable
private fun DefaultError(error: AsyncImagePainter.State.Error) {
    Text(error.result.throwable.message ?: "")
}

@Composable
private fun DefaultSuccess(result: AsyncImagePainter.State.Success, contentDescription: String) {
    Image(
        painter = result.painter,
        contentDescription = contentDescription,
    )
}
