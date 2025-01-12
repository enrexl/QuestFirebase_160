package com.firebase.firestore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.firebase.firestore.ui.view.HomeView
import com.firebase.firestore.ui.view.InsertView
import java.lang.reflect.Modifier

@Composable
fun PengelolaHalaman(
    modifier: Modifier,
    navController: NavHostController = rememberNavController()
){

    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
    ) {
        composable(DestinasiHome.route) {
            HomeView(
                navigateToItemEntry = {
                    navController.navigate(DestinasiInsert.route)
                },
            )
        }
        composable(DestinasiInsert.route) {
            InsertView(
                onBack = { navController.popBackStack() },
                onNavigate = {
                    navController.navigate(DestinasiHome.route)
                }
            )
        }
    }
}

