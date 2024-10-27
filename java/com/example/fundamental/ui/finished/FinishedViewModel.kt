package com.example.fundamental.ui.finished

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fundamental.data.response.EventResponse
import com.example.fundamental.data.response.ListEventsItem
import com.example.fundamental.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Response

class FinishedViewModel : ViewModel() {
    private val _finishedEvents = MutableLiveData<List<ListEventsItem>>()
    val finishedEvents: LiveData<List<ListEventsItem>> = _finishedEvents
    private val _isloading = MutableLiveData<Boolean>()
    val isloading: LiveData<Boolean> = _isloading


    init {
        fetchFinishedEvents()
    }

    private fun fetchFinishedEvents() {
        _isloading.value = true
        viewModelScope.launch {
            try {
                val response: Response<EventResponse> = ApiConfig.getApiService().getEvents(active = 0)
                if(response.isSuccessful){
                    _finishedEvents.value = response.body()?.listEvents ?: listOf()
                }else{
                    _finishedEvents.value = listOf()
                }
                _isloading.value = false
            }catch (e: Exception){
                _finishedEvents.value = listOf()
            }
        }
    }
}