package com.example.androidstructure.data.repository

import com.example.androidstructure.data.model.response.Location
import com.example.androidstructure.data.repository.datasource.remote.RemoteDataSourceImp
import com.example.androidstructure.data.utils.Resource
import retrofit2.Response
import javax.inject.Inject

class RepositoryImp @Inject constructor(
        private val remoteDataSourceImp: RemoteDataSourceImp,
       // private val localDataSourceImp: RemoteDataSourceImp
)  : Repository {

    //-- Responses ToBe
    private fun locationResponse(response: Response<ArrayList<Location>>) : Resource<ArrayList<Location>>{
        if (response.isSuccessful){
            response.body()?.let { result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(message = "${response.errorBody()?.string()}")
    }
    //-----------------

    override suspend fun getLocations(): Resource<ArrayList<Location>> {
        return locationResponse(remoteDataSourceImp.getlocations())
    }
}