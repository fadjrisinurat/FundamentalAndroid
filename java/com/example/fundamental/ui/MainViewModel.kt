package com.example.fundamental.ui

import android.app.Application
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fundamental.data.database.Favorite
import com.example.fundamental.data.database.FavoriteRepository
import com.example.fundamental.data.response.EventResponse
import com.example.fundamental.data.response.ListEventsItem
import com.example.fundamental.data.retrofit.ApiConfig
import com.example.fundamental.setting.SettingPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainViewModel(private val pref: SettingPreferences) : ViewModel() {


    private val _listEvents = MutableLiveData<List<ListEventsItem>>()
    val listEvents: LiveData<List<ListEventsItem>> = _listEvents


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    companion object {
        private const val TAG = "MainViewModel"
    }

    init {
        findEvents()
    }

    fun findEvents(active: Int = 0) {
        _isLoading.value = true


        viewModelScope.launch(Dispatchers.IO) {

            delay(2000)
            try {
                val response: Response<EventResponse> = ApiConfig.getApiService().getEvents(active)
                withContext(Dispatchers.Main) {
                    _isLoading.value = false

                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _isLoading.value = false

                }
            }
        }
    } fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }
}
