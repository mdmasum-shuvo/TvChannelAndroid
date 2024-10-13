
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.tvchannel.utils.Constants.PLAYER_CONTROLS_VISIBILITY
import kotlinx.coroutines.delay

@Composable
fun SeeAllChannelScreen(
    channelViewModel: ChannelViewModel,
    onFullScreenToggle: (isFullScreen: Boolean) -> Unit,
    navigateBack: () -> Unit,
    navController: NavController
) {

    var shouldShowControls by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = shouldShowControls) {
        if (shouldShowControls) {
            delay(PLAYER_CONTROLS_VISIBILITY)
            shouldShowControls = false
        }
    }


}


