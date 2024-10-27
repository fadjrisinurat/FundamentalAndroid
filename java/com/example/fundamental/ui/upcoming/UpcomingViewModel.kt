package com.example.fundamental.ui.upcoming

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundamental.data.response.EventResponse
import com.example.fundamental.data.response.ListEventsItem
import com.example.fundamental.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Response

class UpcomingViewModel : ViewModel() {
    private val _upcomingEvents = MutableLiveData<List<ListEventsItem>>()
    val upcomingEvents: LiveData<List<ListEventsItem>> = _upcomingEvents
    private val _isloading = MutableLiveData<Boolean>()
    val isloading: LiveData<Boolean> = _isloading
    init {
        fetchUpcomingEvents()
    }

   fun fetchUpcomingEvents() {
        _isloading.value = true
        viewModelScope.launch {
            try {
                val response: Response<EventResponse> = ApiConfig.getApiService().getEvents(active = 1)
                if(response.isSuccessful){
                    _upcomingEvents.value = response.body()?.listEvents ?: listOf()
                }else{
                    _upcomingEvents.value = listOf()
                }
                _isloading.value = false
            }catch (e: Exception){
                Log.e("UpcomingViewModel", "Error fetching upcoming events: ${e.message}")
                _upcomingEvents.value = listOf()
            }
        }
    }
}