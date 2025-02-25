package uz.yayra.otabek.brainbuzzer.repository

import uz.yayra.otabek.brainbuzzer.data.model.GenreData
import uz.yayra.otabek.brainbuzzer.data.model.QuizData

interface AppRepository {
    fun saveName(name: String)
    fun getName(): String
    fun getMoney(): String
    fun saveMoney(money: Int)
    fun getQuizGenres(): List<GenreData>
    fun getSportQuizzes(): List<QuizData>
    fun getScienceQuizzes(): List<QuizData>
    fun getMathQuizzes(): List<QuizData>
    fun getAnimalQuizzes(): List<QuizData>
    fun getCapitalQuizzes(): List<QuizData>
}