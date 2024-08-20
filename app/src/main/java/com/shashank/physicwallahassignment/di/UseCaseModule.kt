package com.shashank.physicwallahassignment.di

import com.shashank.data.repository.GetRickMortyRepositoryImpl
import com.shashank.domain.repository.GetRickMortyRepository
import com.shashank.domain.usecase.GetRickMortyCharacterListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {
//    @Provides
//    fun provideRickMortyCharacterListUseCase(getRickMortyCharacterListUseCase: GetRickMortyCharacterListUseCase): GetRickMortyCharacterListUseCase {
//        return getRickMortyCharacterListUseCase
//    }

    @Provides
    fun provideGetRickMortyCharacterRepository(getRickMortyRepositoryImpl: GetRickMortyRepositoryImpl): GetRickMortyRepository {
        return getRickMortyRepositoryImpl
    }
}