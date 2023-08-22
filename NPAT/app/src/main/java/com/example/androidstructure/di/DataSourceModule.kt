package com.example.androidstructure.di

import com.example.androidstructure.data.networking.Api
import com.example.androidstructure.data.repository.datasource.remote.RemoteDataSource
import com.example.androidstructure.data.repository.datasource.remote.RemoteDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

//	@Singleton
//	@Provides
//	fun providesFlyBuyLocalDataSource(flyBuyDAO: FlyBuyDAO) : FlyBuyLocalDataSource {
//		return FlyBuyLocalDataSourceImpl(flyBuyDAO = flyBuyDAO)
//	}

	@Provides
	@Singleton
	fun provideRemoteDataSource(apiService: Api): RemoteDataSource {
		return RemoteDataSourceImp(api = apiService)
	}
}