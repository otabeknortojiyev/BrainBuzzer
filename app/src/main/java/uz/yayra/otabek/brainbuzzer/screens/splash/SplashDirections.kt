package uz.yayra.otabek.brainbuzzer.screens.splash

import uz.yayra.otabek.brainbuzzer.screens.home.HomeScreen
import uz.yayra.otabek.brainbuzzer.ui.navigation.AppNavigator
import javax.inject.Inject

class SplashDirections @Inject constructor(private val navigator: AppNavigator) : SplashContract.Direction {
    override suspend fun moveToHomeTab() {
        navigator.replaceAll(HomeScreen)
    }
}