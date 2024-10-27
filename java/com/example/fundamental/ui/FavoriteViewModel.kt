package com.example.fundamental.ui
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.fundamental.data.database.Favorite
import com.example.fundamental.data.database.FavoriteRepository
import com.example.fundamental.data.response.EventResponse
import com.example.fundamental.data.response.ListEventsItem
import com.example.fundamental.data.retrofit.ApiConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class FavoriteViewModel(private val repository: FavoriteRepository): ViewModel()  {

    fun getFavorite(): LiveData<List<Favorite>> = repository.getAllFavorite()

}
