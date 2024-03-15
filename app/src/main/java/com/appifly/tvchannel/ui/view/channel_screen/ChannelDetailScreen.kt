package com.appifly.tvchannel.ui.view.channel_screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appifly.tvchannel.player.ExoPlayerScreen
import com.appifly.tvchannel.ui.common_component.MainTopBar
import com.appifly.tvchannel.ui.theme.TvChannelTheme

@Composable
fun ChannelDetailScreen() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MainTopBar(isBackEnable = true)

        ExoPlayerScreen(
            modifier = Modifier
                .fillMaxWidth().height(220.dp)
            ,
            url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
        )


    }
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewChannelDetailScreen() {
    TvChannelTheme {
        ChannelDetailScreen()
    }
}