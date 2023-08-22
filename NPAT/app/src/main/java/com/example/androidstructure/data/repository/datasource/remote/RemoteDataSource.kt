package com.example.androidstructure.data.repository.datasource.remote

import com.example.androidstructure.data.model.response.Location
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getlocations () : Response<ArrayList<Location>>
}