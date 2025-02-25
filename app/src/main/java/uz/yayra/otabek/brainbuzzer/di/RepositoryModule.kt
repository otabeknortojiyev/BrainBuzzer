package uz.yayra.otabek.brainbuzzer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.yayra.otabek.brainbuzzer.repository.AppRepository
import uz.yayra.otabek.brainbuzzer.repository.AppRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindRepository(impl: AppRepositoryImpl): AppRepository
}