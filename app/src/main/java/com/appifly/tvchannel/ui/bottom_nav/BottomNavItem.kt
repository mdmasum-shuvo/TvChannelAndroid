package com.appifly.tvchannel.ui.bottom_nav

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.compose.material.*
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.appifly.tvchannel.R
import com.appifly.tvchannel.routing.Routing
import com.appifly.tvchannel.ui.common_component.gradientColor
import com.appifly.tvchannel.ui.theme.gradientColor1

sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Search : BottomNavItem("search", Icons.Default.Search, "Search")
    object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")
}

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
    NavigationBar {
        items.forEachIndexed { index, navigationItem ->
            NavigationBarItem(
                selected = index == navigationSelectedItem,
                label = {
                    Text("Home")
                },
                icon = {
                    Icon(
                        navigationItem.drawable!!,
                        contentDescription = navigationItem.routeName,

                    )
                },
                onClick = {
                    navigationSelectedItem = index
                    navController.navigate(navigationItem.title!!) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },

                )
        }
    }
}