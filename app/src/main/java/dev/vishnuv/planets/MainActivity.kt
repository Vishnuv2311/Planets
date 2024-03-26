package dev.vishnuv.planets

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.vishnuv.planets.model.planets
import dev.vishnuv.planets.ui.screen.DetailsView
import dev.vishnuv.planets.ui.screen.HomePage
import dev.vishnuv.planets.ui.screen.Screen
import dev.vishnuv.planets.ui.theme.PlanetsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlanetsTheme {
                val navController = rememberNavController()
                var selectedPlanetInfo = remember { planets.first() }
                NavHost(navController = navController, startDestination = Screen.Home.route) {
                    composable(route = Screen.Home.route) {
                        HomePage { planetInfo ->
                            selectedPlanetInfo = planetInfo
                            navController.navigate(Screen.Details.route)
                        }
                    }

                    composable(
                        route = Screen.Details.route
                    ) {
                        DetailsView(planetInfo = selectedPlanetInfo) {
                            navController.popBackStack()
                        }
                    }
                }

            }
        }
    }
}
