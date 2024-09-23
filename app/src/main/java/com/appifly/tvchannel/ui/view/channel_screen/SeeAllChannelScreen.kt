
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.appifly.app_data_source.viewmodel.CategoryViewModel
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.app_data_source.viewmodel.SeeAllChannelViewModel
import com.appifly.tvchannel.player.PlayVideo
import com.appifly.tvchannel.utils.Constants.PLAYER_CONTROLS_VISIBILITY
import kotlinx.coroutines.delay

@Composable
fun SeeAllChannelScreen(
    viewModel: CategoryViewModel,
    channelViewModel: ChannelViewModel,
    seeAllChannelViewModel: SeeAllChannelViewModel,
    onFullScreenToggle: (isFullScreen: Boolean) -> Unit,
    navigateBack: () -> Unit
) {

    var shouldShowControls by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = shouldShowControls) {
        if (shouldShowControls) {
            delay(PLAYER_CONTROLS_VISIBILITY)
            shouldShowControls = false
        }
    }
    LaunchedEffect(key1 = true, block = {
        viewModel.setCategoryName(seeAllChannelViewModel.dataListTitle)
    })

    PlayVideo(onFullScreenToggle = onFullScreenToggle, navigateBack = navigateBack, channelViewModel = channelViewModel)

}


