package ru.otus.compose

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ru.otus.compose.features.comicdetails.ComicDetailsScreen
import ru.otus.compose.features.comics.ComicsScreen
import ru.otus.compose.features.characterdetails.CharacterDetailScreen
import ru.otus.compose.features.characterdetails.SharedCharacterInfo
import ru.otus.compose.features.characters.CharactersScreen
import ru.otus.compose.ui.SplashScreen
import ru.otus.compose.ui.theme.AppTheme
import ru.otus.compose.ui.theme.ComposeLessonTheme

typealias OnThemeToggle = () -> Unit

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun ComposeLessonApp(
    onToggleTheme: () -> Unit,
    darkTheme: Boolean,
) {
    ComposeLessonTheme(darkTheme = darkTheme) {
        Surface(color = AppTheme.colors.background) {
            SharedTransitionLayout {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Splash,
                ) {
                    composable<Splash> {
                        SplashScreen(navHostController = navController)
                    }

                    composable<Characters> {
                        CharactersScreen(
                            navHostController = navController,
                            onToggleTheme = onToggleTheme,
                            sharedTransitionScope = this@SharedTransitionLayout,
                            animatedContentScope = this@composable,
                        )
                    }
                    composable<ComicsCollection> { backStackEntry ->
                        val comicsCollection: ComicsCollection = backStackEntry.toRoute()
                        ComicsScreen(
                            navHostController = navController,
                            characterId = comicsCollection.comicsCollectionId
                        )
                    }
                    composable<Character> { backStackEntry ->
                        val character: Character = backStackEntry.toRoute()
                        CharacterDetailScreen(
                            navHostController = navController,
                            sharedCharacterInfo = SharedCharacterInfo(
                                characterId = character.characterId,
                                imageUrl = character.imageUrl,
                            ),
                            sharedTransitionScope = this@SharedTransitionLayout,
                            animatedContentScope = this@composable,
                        )
                    }
                    composable<ComicInfo> { backStackEntry ->
                        val comicInfo: ComicInfo = backStackEntry.toRoute()
                        ComicDetailsScreen(
                            navHostController = navController,
                            comicsId = comicInfo.comicInfoId,
                        )
                    }
                }
            }
        }
    }
}