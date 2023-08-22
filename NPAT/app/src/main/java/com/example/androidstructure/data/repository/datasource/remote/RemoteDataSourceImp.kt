package com.example.androidstructure.data.repository.datasource.remote

import com.example.androidstructure.data.model.response.Location
import com.example.androidstructure.data.networking.Api
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImp  @Inject constructor(
    private val api: Api
) : RemoteDataSource{
    override suspend fun getlocations(): Response<ArrayList<Location>> {
        return api.getLocations()
    }
}