package uz.yayra.otabek.brainbuzzer.screens.game

import uz.yayra.otabek.brainbuzzer.ui.navigation.AppNavigator
import javax.inject.Inject

class GameDirections @Inject constructor(private val navigator: AppNavigator) : GameContract.Direction {
    override suspend fun back() {
        navigator.back()
    }
}