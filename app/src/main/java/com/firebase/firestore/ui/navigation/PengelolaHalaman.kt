package com.firebase.firestore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.firebase.firestore.ui.view.HomeView
import com.firebase.firestore.ui.view.InsertView
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.firebase.firestore.ui.view.DetailView


@Composable
fun PengelolaHalaman(
    modifier: Modifier,
    navController: NavHostController = rememberNavController()
){

    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ) {
        composable(DestinasiHome.route) {
            HomeView(
                navigateToItemEntry = {
                    navController.navigate(DestinasiInsert.route)
                },
                onDetailClick = {
                    navController.navigate(DestinasiDetail.route)
                }

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

        // Detail Screen
        composable(
            route = "${DestinasiDetail.route}/{nim}",
            arguments = listOf(
                navArgument("nim") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val nim = backStackEntry.arguments?.getString("nim") // Ambil argumen nim
            nim?.let {
                DetailView(
                    nim = it,
                    navigateBack = { navController.popBackStack() },
                )
            }
        }

        //Detail 2

    }
}

