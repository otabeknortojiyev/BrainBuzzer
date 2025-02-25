package uz.yayra.otabek.brainbuzzer.data.model

import uz.yayra.otabek.brainbuzzer.utils.GenreEnum

data class GenreData(
    val name: String,
    val questionCount: Int,
    val image: Int,
    val genreEnum: GenreEnum,
)
