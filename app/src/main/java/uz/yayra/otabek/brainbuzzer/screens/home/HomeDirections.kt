package uz.yayra.otabek.brainbuzzer.screens.home

import uz.yayra.otabek.brainbuzzer.screens.game.GameScreen
import uz.yayra.otabek.brainbuzzer.ui.navigation.AppNavigator
import uz.yayra.otabek.brainbuzzer.utils.GenreEnum
import javax.inject.Inject

class HomeDirections @Inject constructor(private val navigator: AppNavigator) : HomeContracts.Directions {
    override suspend fun moveToGame(genreEnum: GenreEnum) {
        navigator.push(GameScreen(genreEnum))
    }
}