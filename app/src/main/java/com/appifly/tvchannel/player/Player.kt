package com.appifly.tvchannel.player

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.compose.BackHandler
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Player.STATE_ENDED
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.appifly.app_data_source.dto.ChannelDto
import com.appifly.app_data_source.viewmodel.CategoryViewModel
import com.appifly.app_data_source.viewmodel.ChannelViewModel
import com.appifly.app_data_source.viewmodel.HomeViewModel
import com.appifly.tvchannel.ui.admob.AdmobBanner
import com.appifly.tvchannel.ui.common_component.GradientFavIcon
import com.appifly.tvchannel.ui.common_component.RegularChannelItem
import com.appifly.tvchannel.ui.common_component.SpacerHeight
import com.appifly.tvchannel.ui.common_component.SpacerWidth
import com.appifly.tvchannel.ui.common_component.TextView12W400
import com.appifly.tvchannel.ui.common_component.TextView14W500
import com.appifly.tvchannel.ui.theme.dimens
import com.appifly.tvchannel.ui.view.home.home_component.HeaderText
import com.appifly.tvchannel.utils.setPortrait
import kotlinx.coroutines.delay


@Composable
fun LandscapeView(
    playerWrapper: PlayerWrapper,
    onFullScreenToggle: (isFullScreen: Boolean) -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {
        PlayerView(
            modifier = Modifier.fillMaxSize(),
            playerWrapper = playerWrapper,
            isFullScreen = true,
            onFullScreenToggle = onFullScreenToggle
        )
    }
}

fun getGridSize(size: Int): Int {
    return if (size % 3 == 0) 3 else if (size <= 2) 1 else size - 2
}

@Composable
fun PortraitView(
    playerWrapper: PlayerWrapper,
    onFullScreenToggle: (isFullScreen: Boolean) -> Unit,
    navigateBack: () -> Unit,
    channelViewModel: ChannelViewModel,
    isSeeAll: Boolean = false,
    viewModel: CategoryViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {


    LaunchedEffect(key1 = true, block = {
        if (!isSeeAll) {
            channelViewModel.selectedChannel.value?.catId?.let {
                channelViewModel.catId = channelViewModel.selectedChannel.value?.catId!!
                channelViewModel.callChannelDataByCatId()
                channelViewModel.checkFavorite(channelViewModel.selectedChannel.value?.id!!)
                viewModel.getCategoryNameById(channelViewModel.catId)
            }
        } else {
            viewModel.setCategoryName(channelViewModel.dataListTitle)
        }

    })
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(MaterialTheme.dimens.mediumWeightTv, fill = true)
        ) {

            PlayerView(
                playerWrapper = playerWrapper,
                isFullScreen = false,
                onFullScreenToggle = onFullScreenToggle,
                navigateBack = navigateBack
            )
        }
        SpacerHeight(MaterialTheme.dimens.stdDimen12)
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                RegularChannelItem(
                    modifier = Modifier.size(MaterialTheme.dimens.channelExtraSmall),
                    item = ChannelDto(iconUrl = channelViewModel.selectedChannel.observeAsState().value?.iconUrl)
                )
                SpacerWidth(MaterialTheme.dimens.stdDimen10)

                Column {
                    TextView14W500(
                        value = channelViewModel.selectedChannel.observeAsState().value?.name
                            ?: "N/A"
                    )
                    TextView12W400(
                        value = "${viewModel.channelCategoryName.observeAsState().value} Channel",
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                }
            }
            channelViewModel.isFavoriteChannel.observeAsState().value?.let {
                GradientFavIcon(
                    size = 24.dp,
                    isFavorite = it
                ) { isFav ->
                    if (isFav) {
                        channelViewModel.removeFavoriteChannel(channelViewModel.selectedChannel.value?.id!!)
                    } else {
                        channelViewModel.setFavoriteChannel(channelViewModel.selectedChannel.value!!)
                    }
                }
            }
        }
        SpacerHeight(MaterialTheme.dimens.stdDimen12)

        AdmobBanner(adLiveData = homeViewModel.adIdData)
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(2f, fill = true)
        ) {
            channelViewModel.channelData.observeAsState().value?.let {
                HeaderText(viewModel.channelCategoryName.observeAsState().value)

                LazyVerticalGrid(
                    modifier = Modifier.height(
                        (((MaterialTheme.dimens.gridItemHeight + 24) * it.size) /if (it.size < 3) 1 else 3).dp
                    ),
                    columns = GridCells.Fixed(MaterialTheme.dimens.gridCellsChannel),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    userScrollEnabled = false,
                    contentPadding = PaddingValues(
                        start = 12.dp,
                        top = 10.dp,
                        end = 12.dp,
                        bottom = 16.dp
                    )
                ) {
                    items(items = it, key = { item -> item.id!! }) { item ->
                        RegularChannelItem(
                            item = item,
                            modifier = Modifier.height(MaterialTheme.dimens.channelMedium),
                            onItemClick = { clickedItem ->
                                channelViewModel.setSelectedChannel(clickedItem)
                                channelViewModel.checkFavorite(channelViewModel.selectedChannel.value?.id!!)
                                channelViewModel.addTOFrequentChannel(clickedItem.id!!)
                            },
                        )
                    }

                }
            }

        }


    }


}


@Composable
fun PlayerView(
    modifier: Modifier = Modifier,
    playerWrapper: PlayerWrapper,
    isFullScreen: Boolean,
    onTrailerChange: ((Int) -> Unit)? = null,
    onFullScreenToggle: (isFullScreen: Boolean) -> Unit,
    navigateBack: (() -> Unit)? = null
) {
    val context = LocalContext.current

    BackHandler {
        if (isFullScreen) {
            context.setPortrait()
            onFullScreenToggle.invoke(false)
        } else {
            navigateBack?.invoke()
        }
    }

    Box(modifier = modifier) {

        var shouldShowControls by remember { mutableStateOf(false) }

        var isPlaying by remember { mutableStateOf(playerWrapper.exoPlayer.isPlaying) }

        var playbackState by remember { mutableIntStateOf(playerWrapper.exoPlayer.playbackState) }

        var title by remember {
            mutableStateOf(playerWrapper.exoPlayer.currentMediaItem?.mediaMetadata?.displayTitle.toString())
        }

        var videoTimer by remember { mutableLongStateOf(0L) }

        var totalDuration by remember { mutableLongStateOf(0L) }

        var bufferedPercentage by remember { mutableIntStateOf(0) }

        LaunchedEffect(key1 = shouldShowControls) {
            if (shouldShowControls) {
                delay(4000)
                shouldShowControls = false
            }
        }



        DisposableEffect(key1 = true) {
            val listener = object : Player.Listener {
                override fun onEvents(player: Player, events: Player.Events) {
                    super.onEvents(player, events)
                    isPlaying = player.isPlaying
                    totalDuration = player.duration
                    videoTimer = player.contentPosition
                    bufferedPercentage = player.bufferedPercentage
                    playbackState = player.playbackState
                }

                override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                    super.onMediaItemTransition(mediaItem, reason)
                    onTrailerChange?.invoke(playerWrapper.exoPlayer.currentPeriodIndex)
                    title = mediaItem?.mediaMetadata?.displayTitle.toString()
                }
            }

            playerWrapper.exoPlayer.addListener(listener)

            onDispose {
                playerWrapper.exoPlayer.removeListener(listener)
            }
        }

        VideoPlayer(
            modifier = Modifier.fillMaxSize(),
            playerWrapper = playerWrapper,
            onPlayerClick = {
                shouldShowControls = shouldShowControls.not()
            }
        )

        PlayerControls(
            modifier = Modifier.fillMaxSize(),
            isVisible = { shouldShowControls },
            isPlaying = { isPlaying },
            playbackState = { playbackState },
            getTitle = { title },
            isFullScreen = isFullScreen,
            onPrevious = { playerWrapper.exoPlayer.seekToPrevious() },
            onNext = { playerWrapper.exoPlayer.seekToNext() },
            onReplay = { playerWrapper.exoPlayer.seekBack() },
            onForward = { playerWrapper.exoPlayer.seekForward() },
            onPauseToggle = {
                when {
                    playerWrapper.exoPlayer.isPlaying -> {
                        playerWrapper.exoPlayer.pause()
                    }

                    playerWrapper.exoPlayer.isPlaying.not() && playbackState == STATE_ENDED -> {
                        playerWrapper.exoPlayer.seekTo(0)
                        playerWrapper.exoPlayer.playWhenReady = true
                    }

                    else -> {
                        playerWrapper.exoPlayer.play()
                    }
                }
                isPlaying = isPlaying.not()
            },
            onFullScreenToggle = onFullScreenToggle
        )
    }

}

@OptIn(UnstableApi::class)
@Composable
private fun VideoPlayer(
    modifier: Modifier = Modifier,
    playerWrapper: PlayerWrapper,
    onPlayerClick: () -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .clickable {
                onPlayerClick.invoke()
            }
            .background(MaterialTheme.colorScheme.background)
            .testTag("VideoPlayerParent")

    ) {
        AndroidView(
            modifier = modifier
                .testTag("VideoPlayer"),
            factory = {
                PlayerView(context).apply {
                    player = playerWrapper.exoPlayer
                    useController = false
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    keepScreenOn = true
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                }
            })
    }
}