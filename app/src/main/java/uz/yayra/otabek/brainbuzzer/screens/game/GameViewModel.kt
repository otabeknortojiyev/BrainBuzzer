package uz.yayra.otabek.brainbuzzer.screens.game

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.yayra.otabek.brainbuzzer.data.model.QuizData
import uz.yayra.otabek.brainbuzzer.repository.AppRepository
import uz.yayra.otabek.brainbuzzer.utils.GenreEnum
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val direction: GameContract.Direction,
    private val repository: AppRepository,
) : ViewModel(), GameContract.ViewModel {

    override fun onEventDispatcher(intent: GameContract.Intent) = intent {
        when (intent) {
            is GameContract.Intent.Init -> {
                reduce { state.copy(isLoading = true) }
                val money = repository.getMoney()
                val quizzes: List<QuizData> = when (intent.genreEnum) {
                    GenreEnum.SPORT -> {
                        repository.getSportQuizzes()
                    }

                    GenreEnum.SCIENCE -> {
                        repository.getScienceQuizzes()
                    }

                    GenreEnum.MATH -> {
                        repository.getMathQuizzes()
                    }

                    GenreEnum.ANIMAL -> {
                        repository.getAnimalQuizzes()
                    }

                    else -> {
                        repository.getCapitalQuizzes()
                    }
                }
                reduce { state.copy(isLoading = false, money = money, quizzes = quizzes, index = 0) }
            }

            GameContract.Intent.Back -> {
                direction.back()
            }

            is GameContract.Intent.Check -> {
                var index = state.index
                val answer: Boolean = if (intent.variant == 1 && state.quizzes[state.index].answer == 1) {
                    true
                } else if (intent.variant == 2 && state.quizzes[state.index].answer == 2) {
                    true
                } else if (intent.variant == 3 && state.quizzes[state.index].answer == 3) {
                    true
                } else {
                    false
                }
                reduce {
                    state.copy(
                        var1 = if (intent.variant == 1 && answer) {
                            0
                        } else if (intent.variant == 1 && !answer) {
                            -1
                        } else {
                            1
                        }, var2 = if (intent.variant == 2 && answer) {
                            0
                        } else if (intent.variant == 2 && !answer) {
                            -1
                        } else {
                            2
                        }, var3 = if (intent.variant == 3 && answer) {
                            0
                        } else if (intent.variant == 3 && !answer) {
                            -1
                        } else {
                            3
                        }
                    )
                }
                delay(500)
                if (answer) {
                    repository.saveMoney(10)
                }
                val money = state.money
                var correctCount = state.correct
                if (answer) {
                    correctCount++
                }
                if (index != state.quizzes.size - 1) {
                    index++
                    reduce {
                        state.copy(
                            index = index, var1 = 1, var2 = 2, var3 = 3, money = if (answer) {
                                (money.toInt() + 10).toString()
                            } else {
                                money
                            }, correct = correctCount
                        )
                    }
                } else if (index == state.quizzes.size - 1) {
                    reduce {
                        state.copy(
                            money = if (answer) {
                                (money.toInt() + 10).toString()
                            } else {
                                money
                            }, correct = correctCount
                        )
                    }
                }
            }
        }
    }

    override val container = container<GameContract.UiState, GameContract.SideEffect>(GameContract.UiState())
}