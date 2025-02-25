package uz.yayra.otabek.brainbuzzer.screens.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.yayra.otabek.brainbuzzer.repository.AppRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val direction: HomeContracts.Directions,
    private val repository: AppRepository,
) : HomeContracts.ViewModel, ViewModel() {

    override fun onEventDispatcher(intent: HomeContracts.Intent) = intent {
        when (intent) {
            HomeContracts.Intent.Init -> {
                val name = repository.getName()
                val money = repository.getMoney()
                val quizGenres = repository.getQuizGenres()
                reduce { state.copy(name = name, money = money, quizGenres = quizGenres) }
            }

            is HomeContracts.Intent.MoveToGame -> {
                direction.moveToGame(intent.genreEnum)
            }
        }
    }

    override val container = container<HomeContracts.UiState, HomeContracts.SideEffect>(HomeContracts.UiState())
}