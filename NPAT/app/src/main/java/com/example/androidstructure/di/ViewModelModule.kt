package com.example.androidstructure.di

import com.example.androidstructure.domain.usecase.Locations
import com.example.androidstructure.presentation.modules.splash.SplashViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {

	@Singleton
	@Provides
	fun providesSplashViewModel(locations: Locations) : SplashViewModel {
		return SplashViewModel(location = locations)
	}

}