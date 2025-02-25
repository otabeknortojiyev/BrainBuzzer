package uz.yayra.otabek.brainbuzzer.screens.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.yayra.otabek.brainbuzzer.repository.AppRepository
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val direction: SplashContract.Direction,
    private val repository: AppRepository,
) : ViewModel(), SplashContract.ViewModel {

    override fun onEventDispatcher(intent: SplashContract.Intent) = intent {
        when (intent) {
            is SplashContract.Intent.SaveName -> {
                reduce { state.copy(isLoading = true) }
                repository.saveName(intent.name)
                reduce { state.copy(isLoading = false) }
                direction.moveToHomeTab()
            }

            is SplashContract.Intent.IsError -> {
                reduce { state.copy(error = intent.error) }
            }
        }
    }

    override val container = container<SplashContract.UiState, SplashContract.SideEffect>(SplashContract.UiState())
}