package com.example.fundamental.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundamental.data.database.Favorite
import com.example.fundamental.data.database.FavoriteRepository
import com.example.fundamental.data.response.DetailEventResponse
import com.example.fundamental.data.response.EventResponse
import com.example.fundamental.data.response.ListEventsItem
import com.example.fundamental.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailViewModel(private val repository: FavoriteRepository): ViewModel() {
    private val apiService = ApiConfig.getApiService()
    private val _eventDetail =MutableLiveData<DetailEventResponse>()
    val eventDetail: LiveData<DetailEventResponse> get() =_eventDetail
    private val _event = MutableLiveData<List<ListEventsItem>>()
    private val _isloading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isloading

    fun insertFavorite(event: Favorite){
        viewModelScope.launch {
            repository.insert(event)
        }
    }
    fun isItemExists(event: Int): LiveData<Boolean> {
        return repository.isItemExists(event)
    }

    fun deleteFavorite(event: Favorite){
        viewModelScope.launch {
            repository.delete(event)
        }
    }


    fun fetchDetailEvent(eventId: String){
        viewModelScope.launch {
            try {
                _isloading.value = true
                val response: Response<DetailEventResponse> = apiService.getDetail(eventId)
                if (response.isSuccessful){
                    _isloading.value = false
                    _eventDetail.value = response.body()
                }else{
                    _isloading.value = false
                    Log.e("DetailViewModel", "Error fetching event detail: ${response.message()}")
                }
            }catch (e: Exception){
                _isloading.value = false
                Log.e("DetailViewModel", "Exception: ${e.message}")
            }
        }
    }

    }
