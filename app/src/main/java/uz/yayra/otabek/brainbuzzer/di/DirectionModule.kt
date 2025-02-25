package uz.yayra.otabek.brainbuzzer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import uz.yayra.otabek.brainbuzzer.screens.game.GameContract
import uz.yayra.otabek.brainbuzzer.screens.game.GameDirections
import uz.yayra.otabek.brainbuzzer.screens.home.HomeContracts
import uz.yayra.otabek.brainbuzzer.screens.home.HomeDirections
import uz.yayra.otabek.brainbuzzer.screens.splash.SplashContract
import uz.yayra.otabek.brainbuzzer.screens.splash.SplashDirections

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @[Binds ViewModelScoped]
    fun bindSignUpDirection(impl: SplashDirections): SplashContract.Direction

    @[Binds ViewModelScoped]
    fun bindHomeDirection(impl: HomeDirections): HomeContracts.Directions

    @[Binds ViewModelScoped]
    fun bindGameDirection(impl: GameDirections): GameContract.Direction
}