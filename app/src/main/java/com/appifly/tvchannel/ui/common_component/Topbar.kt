package com.appifly.tvchannel.ui.common_component

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.appifly.tvchannel.R
import com.appifly.tvchannel.ui.theme.TvChannelTheme

@Composable
fun MainTopBar(
    isBackEnable: Boolean = false, navigateBack: (() -> Unit)? = null, onSearchIconClick: (() -> Unit) = {},
) {

    val context= LocalContext.current
    Box(

        modifier = Modifier.height(60.dp)
    ) {
        if (isBackEnable) {
            Box(
                modifier = Modifier.clickable {
                    if (navigateBack != null) {
                        navigateBack()
                    }
                }
                    .padding(start = 16.dp)
                    .align(Alignment.CenterStart)
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack, tint = MaterialTheme.colorScheme.secondary,
                    contentDescription = "back icon",
                    modifier = Modifier.size(24.dp)
                )
            }
        }


        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
        ) {
            ImageComponent(
                drawableId = R.drawable.app_logo,
                Modifier.height(100.dp).width(120.dp)

            )
        }

        Box(
            modifier = Modifier.clickable { onSearchIconClick() }
                .padding(end = 16.dp)
                .align(Alignment.CenterEnd)
        ) {
            Icon(
                Icons.Filled.Search, tint = MaterialTheme.colorScheme.secondary,
                contentDescription =context.getString(R.string.search_icon_description) ,
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