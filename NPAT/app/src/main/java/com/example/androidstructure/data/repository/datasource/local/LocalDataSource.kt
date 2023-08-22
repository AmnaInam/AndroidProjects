package com.example.androidstructure.data.repository.datasource.local

import com.example.androidstructure.data.model.response.Location
import kotlinx.coroutines.flow.Flow


interface LocalDataSource {
    fun getPoints () : Flow<List<Location>>
}