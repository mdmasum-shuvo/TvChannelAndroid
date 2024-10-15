package com.appifly.tvchannel.ui.bottom_nav

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.appifly.app_data_source.viewmodel.HomeViewModel
import com.appifly.tvchannel.routing.Routing
import com.appifly.tvchannel.ui.common_component.TextView14W400
import com.appifly.tvchannel.ui.common_component.TextView14W400Gradient
import com.appifly.tvchannel.ui.theme.gradientColor1


@Composable
fun BottomNavigation(navController: NavController,homeViewModel: HomeViewModel) {

    var navigationSelectedItem by remember {
        mutableIntStateOf(homeViewModel.selectedIndex)
    }
    val items = listOf(
        Routing.HomeScreen,
        Routing.ChannelScreen,
    )
    Card(elevation = CardDefaults.elevatedCardElevation(4.dp),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomEnd = 0.dp, bottomStart = 0.dp) ){
        NavigationBar {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEachIndexed { index, navigationItem ->
                NavigationBarItem(
                    selected = currentRoute == navigationItem.routeName,
                    label = {
                        if (currentRoute == navigationItem.routeName)
                            TextView14W400Gradient(value = navigationItem.title!!)
                        else TextView14W400(
                            value = navigationItem.title!!
                        )
                    },
                    icon = {
                        Icon(
                            painterResource(id = navigationItem.drawable),
                            contentDescription = navigationItem.routeName,
                            tint = if (currentRoute == navigationItem.routeName) gradientColor1 else MaterialTheme.colorScheme.secondary
                        )

                    },
                    onClick = {
                        navigationSelectedItem = index
                        homeViewModel.selectedIndex=index
                        navController.navigate(navigationItem.routeName) {
                            popUpTo(navController.graph.startDestinationId) {
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
}