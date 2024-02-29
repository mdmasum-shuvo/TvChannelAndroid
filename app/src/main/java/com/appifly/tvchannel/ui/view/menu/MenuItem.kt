package com.appifly.tvchannel.ui.view.menu

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appifly.tvchannel.R
import com.appifly.tvchannel.ui.common_component.ImageComponent
import com.appifly.tvchannel.ui.common_component.SpacerWidth
import com.appifly.tvchannel.ui.common_component.TextView10_W400
import com.appifly.tvchannel.ui.common_component.TextView14_W400
import com.appifly.tvchannel.ui.theme.TvChannelTheme

@Composable
fun MenuItem(drawableId: Int, title: String, subTitle: String) {
   Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
       Card(
           shape = MaterialTheme.shapes.large,
           colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
           elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
           modifier = Modifier.fillMaxWidth()
       ) {

           Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(horizontal = 16.dp, vertical = 12.dp),
               horizontalArrangement = Arrangement.SpaceBetween,
               verticalAlignment = Alignment.CenterVertically,


               ) {
               Row(
                   verticalAlignment = Alignment.CenterVertically,
               ) {
                   ImageComponent(drawableId = drawableId, modifier = Modifier.size(24.dp))
                   SpacerWidth(width = 8)
                   Column {
                       TextView14_W400(value = title)
                       TextView10_W400(value = subTitle)
                   }
               }

               Icon(Icons.AutoMirrored.Outlined.KeyboardArrowRight, contentDescription = "")
           }

       }
   }
}

@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewMenuItem() {
    TvChannelTheme {
        MenuItem(drawableId = R.drawable.theme, "App theme", "Action, Adventure, Thrill")
    }
}