package com.example.androidstructure.domain.usecase

import android.util.Log
import com.example.androidstructure.data.model.response.Location
import com.example.androidstructure.data.repository.RepositoryImp
import com.example.androidstructure.data.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class Locations @Inject constructor(
    private val repositoryImp: RepositoryImp
) {
    fun getLocations(): Flow<Resource<ArrayList<Location>>> = flow {
        emit(Resource.Loading())
        try {
            val response = repositoryImp.getLocations()
            emit(response)
        } catch (e: HttpException) {
            Log.i("AuthUseCase", e.localizedMessage!!)
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.i("AuthUseCase", e.localizedMessage!!)
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}