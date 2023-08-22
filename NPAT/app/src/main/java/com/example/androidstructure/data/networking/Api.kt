package com.example.androidstructure.data.networking

import com.example.androidstructure.data.model.response.Location
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("edcc3f21-5bd8-4972-8352-081a4f547467")
    suspend fun getLocations(): Response<ArrayList<Location>>
}