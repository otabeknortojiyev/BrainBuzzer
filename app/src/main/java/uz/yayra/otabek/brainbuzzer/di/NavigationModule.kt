package uz.yayra.otabek.brainbuzzer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.yayra.otabek.brainbuzzer.ui.navigation.AppNavigationDispatcher
import uz.yayra.otabek.brainbuzzer.ui.navigation.AppNavigator
import uz.yayra.otabek.brainbuzzer.ui.navigation.NavigationHandler

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Binds
    fun bindAppNavigator(dispatcher: AppNavigationDispatcher): AppNavigator

    @Binds
    fun bindNavigationHandler(dispatcher: AppNavigationDispatcher): NavigationHandler
}