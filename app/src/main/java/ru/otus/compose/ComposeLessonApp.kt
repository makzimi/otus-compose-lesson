package ru.otus.compose

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.otus.compose.features.comics.ComicDetailInfoScreen
import ru.otus.compose.features.comics.ComicsScreen
import ru.otus.compose.features.herodetails.HeroDetailScreen
import ru.otus.compose.features.heroes.HeroesListScreen
import ru.otus.compose.ui.SplashScreen
import ru.otus.compose.ui.theme.AppTheme
import ru.otus.compose.ui.theme.ComposeLessonTheme

typealias ThemeToggle = () -> Unit

@Composable
fun ComposeLessonApp(onToggleTheme: () -> Unit, darkTheme: Boolean) {

    ComposeLessonTheme(darkTheme = darkTheme) {
        Surface(color = AppTheme.colors.background) {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "splash") {
                composable("splash") {
                    SplashScreen(navHostController = navController)
                }
                composable("heroesList") {
                    HeroesListScreen(navHostController = navController, onToggleTheme = onToggleTheme)
                }
                composable(
                    "comicsInfo/{comicsId}",
                    arguments = listOf(navArgument("comicsId") { type = NavType.StringType })
                ) { backStackEntry ->
                    backStackEntry.arguments?.getString("comicsId")?.let {
                        ComicsScreen(
                            navHostController = navController,
                            comicsId = it
                        )
                    }
                }
                composable(
                    "hero/{heroId}",
                    arguments = listOf(navArgument("heroId") { type = NavType.LongType })
                ) { backStackEntry ->
                    backStackEntry.arguments?.getLong("heroId")?.let { id ->
                        HeroDetailScreen(
                            navHostController = navController,
                            heroId = id
                        )
                    }
                }
                composable(
                    "comicInfo/{comicInfoId}",
                    arguments = listOf(navArgument("comicInfoId") { type = NavType.StringType })
                ) { backStackEntry ->
                    backStackEntry.arguments?.getString("comicInfoId")?.let { id ->
                        ComicDetailInfoScreen(
                            navHostController = navController,
                            comicsId = id
                        )
                    }
                }
            }
        }
    }
}