package uz.yayra.otabek.brainbuzzer.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.yayra.otabek.brainbuzzer.data.sharedpreference.LocalStorage
import uz.yayra.otabek.brainbuzzer.screens.home.HomeScreen
import uz.yayra.otabek.brainbuzzer.screens.splash.SplashScreen
import uz.yayra.otabek.brainbuzzer.ui.navigation.NavigationHandler
import uz.yayra.otabek.brainbuzzer.ui.theme.BrainBuzzerTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationHandler: NavigationHandler

    @Inject
    lateinit var storage: LocalStorage
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState)
        setContent {
            BrainBuzzerTheme {
                Navigator(screen = if (storage.name.isNotEmpty()) {
                    HomeScreen
                } else {
                    SplashScreen
                }, onBackPressed = {
                    true
                }) { navigator ->
                    LaunchedEffect(key1 = navigator) {
                        navigationHandler.screenState.onEach {
                            it.invoke(navigator)
                        }.launchIn(lifecycleScope)
                    }
                    SlideTransition(navigator = navigator)
                }
            }
        }
    }
}

