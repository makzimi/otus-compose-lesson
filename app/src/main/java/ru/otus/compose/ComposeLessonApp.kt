package ru.otus.compose

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.otus.compose.features.comicdetails.ComicDetailsScreen
import ru.otus.compose.features.comics.ComicsScreen
import ru.otus.compose.features.characterdetails.CharacterDetailScreen
import ru.otus.compose.features.characters.CharactersScreen
import ru.otus.compose.ui.SplashScreen
import ru.otus.compose.ui.theme.AppTheme
import ru.otus.compose.ui.theme.ComposeLessonTheme

typealias OnThemeToggle = () -> Unit

@Composable
fun ComposeLessonApp(
    onToggleTheme: () -> Unit,
    darkTheme: Boolean,
) {
    ComposeLessonTheme(darkTheme = darkTheme) {
        Surface(color = AppTheme.colors.background) {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "splash") {
                composable("splash") {
                    SplashScreen(navHostController = navController)
                }
                composable("characters") {
                    CharactersScreen(
                        navHostController = navController,
                        onToggleTheme = onToggleTheme
                    )
                }
                composable(
                    "comicsCollection/{comicsCollectionId}",
                    arguments = listOf(
                        navArgument("comicsCollectionId") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    backStackEntry.arguments?.getString("comicsCollectionId")?.let {
                        ComicsScreen(
                            navHostController = navController,
                            characterId = it
                        )
                    }
                }
                composable(
                    "character/{characterId}",
                    arguments = listOf(navArgument("characterId") { type = NavType.LongType })
                ) { backStackEntry ->
                    backStackEntry.arguments?.getLong("characterId")?.let { id ->
                        CharacterDetailScreen(
                            navHostController = navController,
                            characterId = id
                        )
                    }
                }
                composable(
                    "comicInfo/{comicInfoId}",
                    arguments = listOf(navArgument("comicInfoId") { type = NavType.StringType })
                ) { backStackEntry ->
                    backStackEntry.arguments?.getString("comicInfoId")?.let { id ->
                        ComicDetailsScreen(
                            navHostController = navController,
                            comicsId = id
                        )
                    }
                }
            }
        }
    }
}