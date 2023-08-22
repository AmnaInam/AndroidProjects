package com.example.androidstructure.presentation.modules.splash

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidstructure.data.utils.Resource
import com.example.androidstructure.domain.usecase.Locations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val location: Locations,
) : ViewModel() {

    val successful: MutableLiveData<Boolean?> = MutableLiveData()
    val error: MutableLiveData<String?> = MutableLiveData()

    fun getLocations() {
        location.getLocations().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    Log.i("LoginViewModel", "I dey here, Loading")
                }
                is Resource.Error -> {
                    error.postValue("${result.message}")
                    successful.postValue(false)
                    Log.i("LoginViewModel", "I dey here, Error ${result.message}")

                }
                is Resource.Success -> {
                    successful.postValue(true)
                    Log.i("LoginViewModel", "I dey here, Success ${result.data}")
                }
            }
        }.launchIn(viewModelScope)

    }

}