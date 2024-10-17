package com.appifly.tvchannel.ui.common_component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.appifly.app_data_source.dto.CategoryDto
import com.appifly.tvchannel.R
import com.appifly.tvchannel.ui.theme.TvChannelTheme
import com.appifly.tvchannel.ui.theme.dimens
import com.appifly.tvchannel.ui.theme.gradientColor1
import com.appifly.tvchannel.utils.shimmerEffect

@Composable
fun CategoryItem(
    item: CategoryDto,
    selectedIndex: MutableState<Int>,
    index: Int = 0, onItemClick: () -> Unit
) {
    val context= LocalContext.current
    val showShimmer = remember { mutableStateOf(true) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
            .clickable {
                onItemClick()
            }
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = cardColors(containerColor = gradientColor1),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            border = if (selectedIndex.value == index) {
                BorderStroke(
                    1.dp,
                    gradientColor1
                )
            } else {
                BorderStroke(1.dp, MaterialTheme.colorScheme.secondaryContainer)
            }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .background(
                            shimmerEffect(
                                targetValue = 1300f,
                                showShimmer = showShimmer.value
                            )
                        )
                        .padding(12.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier.size(MaterialTheme.dimens.categorySize),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.imageUrl).diskCachePolicy(CachePolicy.ENABLED)
                            .build(),

                        contentScale = ContentScale.Crop,
                        contentDescription = context.getString(R.string.load_network_image),
                        onSuccess = {
                            showShimmer.value = false
                        }, onError = {
                            showShimmer.value = false

                        }
                    )
                }
                SpacerWidth(12.dp)
                TextView18W500(value = item.name ?: "N/A", color = MaterialTheme.colorScheme.onTertiary)

            }
        }

    }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewCategoryItem() {
    TvChannelTheme {
    }
}