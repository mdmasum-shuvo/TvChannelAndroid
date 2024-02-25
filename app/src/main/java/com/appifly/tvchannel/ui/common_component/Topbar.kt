package com.appifly.tvchannel.ui.common_component

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.appifly.tvchannel.R
import com.appifly.tvchannel.ui.theme.TvChannelTheme

@Composable
fun MainTopBar() {

    Box(

        modifier = Modifier.height(60.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageComponent(
                drawableId = R.drawable.app_logo,
                modifier = Modifier
                    .height(40.dp)
                    .width(51.dp)
            )
        }

        Box(modifier = Modifier
            .padding(end = 16.dp)
            .align(Alignment.CenterEnd)) {
            ImageComponent(
                drawableId = R.drawable.search,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}


@PreviewScreenSizes
@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewTopBar() {
    TvChannelTheme {
        MainTopBar()
    }
}