package com.example.androidstructure.di

import com.example.androidstructure.data.repository.Repository
import com.example.androidstructure.data.repository.RepositoryImp
import com.example.androidstructure.domain.usecase.Locations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

	@Provides
	@Singleton
	fun providesAuthUseCase(repository: RepositoryImp): Locations {
		return Locations(repositoryImp = repository)
	}
}