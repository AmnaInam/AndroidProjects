package com.example.androidstructure.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

	@Singleton
	@Provides
	fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
		return context.getSharedPreferences("fly_buy", Context.MODE_PRIVATE)
	}

}