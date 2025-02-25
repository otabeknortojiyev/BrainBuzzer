package uz.yayra.otabek.brainbuzzer.data.model

data class QuizData(
    val image: Int,
    val question: String,
    val variant1: String,
    val variant2: String,
    val variant3: String,
    val answer: Int
)
