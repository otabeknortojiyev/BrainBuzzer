package uz.yayra.otabek.brainbuzzer.screens.splash

import org.orbitmvi.orbit.ContainerHost

interface SplashContract {

    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(val isLoading: Boolean = false, var error: Boolean = false)

    sealed interface SideEffect {
        data class ResultMessage(val message: String) : SideEffect
    }

    interface Direction {
        suspend fun moveToHomeTab()
    }


    interface Intent {
        data class SaveName(val name: String) : Intent
        data class IsError(val error: Boolean) : Intent
    }
}