package com.appifly.tvchannel.ui.bottom_nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.appifly.tvchannel.R
import com.appifly.tvchannel.routing.Routing
import com.appifly.tvchannel.ui.common_component.TextView14_W400
import com.appifly.tvchannel.ui.common_component.TextView14_W400_Gradient
import com.appifly.tvchannel.ui.common_component.gradientColor
import com.appifly.tvchannel.ui.theme.darkBackground
import com.appifly.tvchannel.ui.theme.gradientColor1
import com.appifly.tvchannel.ui.theme.gradientColor2


@Composable
fun BottomNavigation(navController: NavController) {

    var navigationSelectedItem by remember {
        mutableStateOf(0)
    }
    val items = listOf(
        Routing.HomeScreen,
        Routing.ChannelScreen,
        Routing.FavoriteScreen,
        Routing.MenuScreen,
    )
    Card(elevation = CardDefaults.elevatedCardElevation(4.dp)) {
        NavigationBar {

            items.forEachIndexed { index, navigationItem ->
                NavigationBarItem(
                    selected = index == navigationSelectedItem,
                    label = {
                        if (index == navigationSelectedItem)
                            TextView14_W400_Gradient(value = navigationItem.title!!)
                        else TextView14_W400(
                            value = navigationItem.title!!
                        )
                    },
                    icon = {

                        Icon(
                            painterResource(id = navigationItem.drawable!!),
                            contentDescription = navigationItem.routeName,
                            tint = if (index == navigationSelectedItem) gradientColor1 else MaterialTheme.colorScheme.secondary
                        )

                    },
                    onClick = {
                        navigationSelectedItem = index
                        navController.navigate(navigationItem.routeName!!) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            //restoreState = true
                        }
                    },

                    )
            }
        }

    }
}