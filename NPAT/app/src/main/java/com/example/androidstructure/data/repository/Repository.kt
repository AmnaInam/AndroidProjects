package com.example.androidstructure.data.repository

import com.example.androidstructure.data.model.response.Location
import com.example.androidstructure.data.utils.Resource

interface Repository {
    suspend fun getLocations () : Resource<ArrayList<Location>>
}