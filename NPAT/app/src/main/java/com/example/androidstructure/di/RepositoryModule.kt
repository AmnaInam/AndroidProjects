package com.example.androidstructure.di

import com.example.androidstructure.data.repository.Repository
import com.example.androidstructure.data.repository.RepositoryImp
import com.example.androidstructure.data.repository.datasource.remote.RemoteDataSource
import com.example.androidstructure.data.repository.datasource.remote.RemoteDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesRepository(
        remoteDataSource: RemoteDataSourceImp,
       // flyBuyLocalDataSource: FlyBuyLocalDataSource
    ): Repository {
        return RepositoryImp(
            remoteDataSourceImp = remoteDataSource,
            //flyBuyLocalDataSource = flyBuyLocalDataSource
        )
    }
}