package uz.yayra.otabek.brainbuzzer.screens.game

import org.orbitmvi.orbit.ContainerHost
import uz.yayra.otabek.brainbuzzer.data.model.QuizData
import uz.yayra.otabek.brainbuzzer.utils.GenreEnum

interface GameContract {

    interface ViewModel : ContainerHost<UiState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val isLoading: Boolean = false,
        val money: String = "",
        val index: Int = 0,
        val quizzes: List<QuizData> = emptyList(),
        val correct: Int = 0,
        val var1: Int = 1,
        val var2: Int = 2,
        val var3: Int = 3,
        val dialog: Boolean = false,
    )

    sealed interface SideEffect {
        data class ResultMessage(val message: String) : SideEffect
    }

    interface Direction {
        suspend fun back()
    }


    interface Intent {
        data class Init(val genreEnum: GenreEnum) : Intent
        data class Check(val variant: Int) : Intent
        data object Back : Intent
    }
}