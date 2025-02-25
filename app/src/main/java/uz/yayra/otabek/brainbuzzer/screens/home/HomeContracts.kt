package uz.yayra.otabek.brainbuzzer.screens.home

import org.orbitmvi.orbit.ContainerHost
import uz.yayra.otabek.brainbuzzer.data.model.GenreData
import uz.yayra.otabek.brainbuzzer.utils.GenreEnum

interface HomeContracts {

    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val isLoading: Boolean = false,
        val name: String = "",
        val money: String = "",
        val quizGenres: List<GenreData> = emptyList(),
    )

    sealed interface SideEffect {
        data class ResultMessage(val message: String) : SideEffect
    }

    interface Directions {
        suspend fun moveToGame(genreEnum: GenreEnum)
    }

    interface Intent {
        data object Init : Intent
        data class MoveToGame(val genreEnum: GenreEnum) : Intent
    }
}